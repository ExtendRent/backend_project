package src.services.paperwork.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.paperwork.requests.Rental.CreateRentalRequest;
import src.core.exception.PaymentException;
import src.data.models.paperwork_entities.paymentEntities.CreditCardInformation;
import src.data.models.paperwork_entities.paymentEntities.PaymentDetailsEntity;
import src.data.models.paperwork_entities.paymentEntities.PaymentTypeEntity;
import src.data.models.paperwork_entities.rentalEntities.RentalEntity;
import src.services.paperwork.payment_type.PaymentTypeEntityService;
import src.services.system.SysPaymentDetailsServiceImpl;

import java.time.LocalDateTime;

import static src.core.exception.exception_types.PaymentExceptionType.*;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PayWithCreditCard payWithCreditCard;
    private final PaymentRules rules;
    private final PaymentTypeEntityService paymentTypeService;
    private final SysPaymentDetailsServiceImpl sysPaymentDetailsServiceImpl;

    public PaymentDetailsEntity pay(CreateRentalRequest createRentalRequest, RentalEntity rentalEntity) {
        PaymentTypeEntity paymentTypeEntity = paymentTypeService.getById(createRentalRequest.getPaymentTypeId());

        if (paymentTypeEntity.isActive()) {
            switch (paymentTypeEntity.getPaymentType()) {
                case CREDIT_CARD:
                    return payWithCreditCard(createRentalRequest, rentalEntity);
                case CASH:
                case BANK_MONEY_TRANSFER:
                    break;
            }
            throw new PaymentException(NOT_SUPPORTED_PAYMENT_TYPE);
        } else {
            throw new PaymentException(PAYMENT_TYPE_IS_NOT_ACTIVE);
        }
    }

    @Override
    public PaymentDetailsEntity payWithCreditCard(CreateRentalRequest createRentalRequest, RentalEntity rentalEntity) {
        CreditCardInformation creditCardInformation =
                rules.fixCreditCardInformation(createRentalRequest.getCreditCardInformation());
        rules.checkCreditCard(createRentalRequest.getCreditCardInformation());

        if (payWithCreditCard.pay(createRentalRequest.getAmount(), creditCardInformation)
        ) {
            return createPaymentDetailsEntity(createRentalRequest, rentalEntity, false);
        } else {
            createPaymentDetailsEntity(createRentalRequest, rentalEntity, true);
            throw new PaymentException(PAYMENT_REJECTED);
        }
    }

    private PaymentDetailsEntity createPaymentDetailsEntity(CreateRentalRequest createRentalRequest,
                                                            RentalEntity rentalEntity, boolean isRejected) {
        PaymentDetailsEntity paymentDetails = new PaymentDetailsEntity(
                createRentalRequest.getAmount(),
                paymentTypeService.getById(createRentalRequest.getPaymentTypeId())
        );
        paymentDetails.setRentalEntity(rentalEntity);
        if (isRejected) {
            paymentDetails.setIsDeleted(true);
            paymentDetails.setDeletedAt(LocalDateTime.now());
        }
        return sysPaymentDetailsServiceImpl.create(paymentDetails);
    }

}

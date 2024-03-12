package source_files.services.paperWorkServices.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentTypeEntity;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.requests.paperworkRequests.RentalRequests.CreateRentalRequest;
import source_files.exception.PaymentException;
import source_files.services.BusinessRules.paperWork.PaymentBusinessRules;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.PaymentTypeEntityService;
import source_files.services.paperWorkServices.abstracts.PaymentService;
import source_files.services.systemServices.SysPaymentDetailsServiceImpl;

import java.time.LocalDateTime;

import static source_files.exception.exceptionTypes.PaymentExceptionType.*;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PayWithCreditCard payWithCreditCard;
    private final PaymentBusinessRules rules;
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

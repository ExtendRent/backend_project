package src.service.paperwork.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.paperwork.rental.requests.CreateRentalRequest;
import src.core.exception.PaymentException;
import src.repository.paperwork.payment.CreditCardInformation;
import src.repository.paperwork.payment.detail.PaymentDetailsEntity;
import src.repository.paperwork.payment.detail.PaymentDetailsEntityServiceImpl;
import src.repository.paperwork.payment.type.PaymentTypeEntity;
import src.repository.paperwork.payment.type.PaymentTypeEntityService;
import src.repository.paperwork.rental.RentalEntity;

import java.time.LocalDateTime;

import static src.core.exception.type.PaymentExceptionType.*;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PayWithCreditCard payWithCreditCard;
    private final PaymentRules rules;
    private final PaymentTypeEntityService paymentTypeService;
    private final PaymentDetailsEntityServiceImpl paymentDetailsEntityServiceImpl;

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
        return paymentDetailsEntityServiceImpl.create(paymentDetails);
    }

}

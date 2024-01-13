package source_files.services.paperWorkServices.payment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.paperWorkDTOs.PaymentDTO;
import source_files.data.DTO.paperWorkDTOs.PaymentDetailsDTO;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentTypeEntity;
import source_files.exception.PaymentException;
import source_files.services.BusinessRules.paperWork.PaymentBusinessRules;
import source_files.services.paperWorkServices.abstracts.PaymentService;

import static source_files.exception.exceptionTypes.PaymentExceptionType.PAYMENT_REJECTED;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {

    private final PayWithCreditCard payWithCreditCard;

    private final PaymentBusinessRules paymentBusinessRules;

    private final ModelMapperService modelMapperService;

    public PaymentDTO pay(PaymentTypeEntity paymentTypeEntity
            , double amount, CreditCardInformation creditCardInformation) {

        if (this.payWithCreditCard(amount, creditCardInformation)) {
            return new PaymentDTO(this.modelMapperService.forResponse().map(
                    new PaymentDetailsEntity(amount, paymentTypeEntity), PaymentDetailsDTO.class
            ), true);
        } else {
            throw new PaymentException(PAYMENT_REJECTED, "Ödeme Banka Tarafından Reddedildi.");
        }
    }

    @Override
    public boolean payWithCreditCard(double amount,
                                     CreditCardInformation creditCardInformation) {

        return this.payWithCreditCard.pay(
                amount
                , this.paymentBusinessRules
                        .checkCreditCard(
                                this.paymentBusinessRules.fixCreditCardInformation(creditCardInformation)
                        )
        );
    }


}

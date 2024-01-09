package source_files.services.paperWorkServices.payment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.services.BusinessRules.PaymentBusinessRules;
import source_files.services.paperWorkServices.abstracts.PaymentService;
import source_files.services.systemServices.SysPaymentDetailsService;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {

    private final PayWithCreditCard payWithCreditCard;
    private final SysPaymentDetailsService sysPaymentDetailsService;

    private final PaymentBusinessRules paymentBusinessRules;

    @Override
    public boolean payWithCreditCard(int paymentDetailsId,
                                     CreditCardInformation creditCardInformation) {

        PaymentDetailsEntity paymentDetailsEntity =
                this.sysPaymentDetailsService.getById(paymentDetailsId);

        return payWithCreditCard.pay(
                paymentDetailsEntity
                , this.paymentBusinessRules
                        .checkCreditCard(
                                this.paymentBusinessRules.fixCreditCardInformation(creditCardInformation)
                        )
        );
    }


}

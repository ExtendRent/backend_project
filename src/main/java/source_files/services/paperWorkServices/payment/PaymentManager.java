package source_files.services.paperWorkServices.payment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.requests.itemRequests.paymentRequests.PayWithCreditCardRequest;
import source_files.services.paperWorkServices.abstracts.PaymentService;
import source_files.services.systemServices.SysPaymentDetailsService;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {

    private final PayWithCreditCard payWithCreditCard;
    private final SysPaymentDetailsService sysPaymentDetailsService;


    @Override
    public boolean payWithCreditCard(PayWithCreditCardRequest payWithCreditCardRequest) {

        PaymentDetailsEntity paymentDetailsEntity =
                this.sysPaymentDetailsService.getById(payWithCreditCardRequest.getPaymentDetailsId());

        return payWithCreditCard.pay(
                paymentDetailsEntity
                , payWithCreditCardRequest.getCreditCardInformation()
                , paymentDetailsEntity.getAmount()
        );
    }


}

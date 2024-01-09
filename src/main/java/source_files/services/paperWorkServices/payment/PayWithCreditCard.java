package source_files.services.paperWorkServices.payment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;

@Service
@AllArgsConstructor
public class PayWithCreditCard {

    public boolean pay(PaymentDetailsEntity paymentDetailsEntity, CreditCardInformation creditCardInformation) {

        //ÖDEME İŞLEMLERİ...
        return true;
    }
}

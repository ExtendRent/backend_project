package source_files.services.paperWorkServices.payment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;

@Service
@AllArgsConstructor
public class PayWithCreditCard implements PayService {

    public boolean pay(double amount, CreditCardInformation creditCardInformation) {

        //ÖDEME İŞLEMLERİ...
        return true;
    }
}

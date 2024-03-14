package source_files.services.paperWork.payment;

import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;
import source_files.services.paperWork.abstracts.PayService;

@Service
public class PayWithCreditCard implements PayService {

    public boolean pay(double amount, CreditCardInformation creditCardInformation) {

        //PAYMENT METHODS...
        return true;
    }
}

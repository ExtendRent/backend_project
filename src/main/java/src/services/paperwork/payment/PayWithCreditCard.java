package src.services.paperwork.payment;

import org.springframework.stereotype.Service;
import src.data.models.paperwork_entities.paymentEntities.CreditCardInformation;

@Service
public class PayWithCreditCard implements PayService {

    public boolean pay(double amount, CreditCardInformation creditCardInformation) {

        //PAYMENT METHODS...
        return true;
    }
}

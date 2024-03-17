package src.service.paperwork.payment;

import org.springframework.stereotype.Service;
import src.repository.paperwork.payment.CreditCardInformation;

@Service
public class PayWithCreditCard implements PayService {

    public boolean pay(double amount, CreditCardInformation creditCardInformation) {

        //PAYMENT METHODS...
        return true;
    }
}

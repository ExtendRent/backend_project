package src.service.payment;

import org.springframework.stereotype.Service;
import src.controller.payment.CreditCardInformation;

@Service
public class PayWithCreditCard implements PayService {

    public boolean pay(double amount, CreditCardInformation creditCardInformation) {

        //PAYMENT METHODS...
        return true;
    }
}

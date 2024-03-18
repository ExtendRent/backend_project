package src.service.payment;

import src.repository.payment.CreditCardInformation;

public interface PayService {

    boolean pay(double amount, CreditCardInformation creditCardInformation);
}

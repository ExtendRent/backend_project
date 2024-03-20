package src.service.payment;

import src.controller.payment.CreditCardInformation;

public interface PayService {

    boolean pay(double amount, CreditCardInformation creditCardInformation);
}

package src.service.paperwork.payment;

import src.repository.paperwork.payment.CreditCardInformation;

public interface PayService {

    boolean pay(double amount, CreditCardInformation creditCardInformation);
}

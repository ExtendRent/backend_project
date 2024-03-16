package src.services.paperwork.payment;

import src.data.models.paperwork_entities.paymentEntities.CreditCardInformation;

public interface PayService {

    boolean pay(double amount, CreditCardInformation creditCardInformation);
}

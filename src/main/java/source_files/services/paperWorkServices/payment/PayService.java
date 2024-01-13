package source_files.services.paperWorkServices.payment;

import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;

public interface PayService {

    boolean pay(double amount, CreditCardInformation creditCardInformation);
}

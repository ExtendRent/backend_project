package source_files.services.paperWorkServices.abstracts;

import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;

public interface PaymentService {

    boolean payWithCreditCard(int paymentDetailsId,
                              CreditCardInformation creditCardInformation);


}

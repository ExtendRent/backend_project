package source_files.services.paperWorkServices.abstracts;

import source_files.data.requests.itemRequests.paymentRequests.PayWithCreditCardRequest;

public interface PaymentService {

    boolean payWithCreditCard(PayWithCreditCardRequest payWithCreditCardRequest);


}

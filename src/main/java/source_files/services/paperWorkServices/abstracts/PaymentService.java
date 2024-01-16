package source_files.services.paperWorkServices.abstracts;

import source_files.data.requests.paperworkRequests.RentalRequests.AddRentalRequest;

public interface PaymentService {

    AddRentalRequest payWithCreditCard(AddRentalRequest addRentalRequest);


}

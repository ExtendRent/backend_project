package source_files.services.paperWorkServices.abstracts;

import source_files.data.requests.paperworkRequests.RentalRequests.CreateRentalRequest;

public interface PaymentService {

    CreateRentalRequest payWithCreditCard(CreateRentalRequest createRentalRequest);


}

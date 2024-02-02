package source_files.services.paperWorkServices.abstracts;

import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.requests.paperworkRequests.RentalRequests.CreateRentalRequest;

public interface PaymentService {

    PaymentDetailsEntity pay(CreateRentalRequest createRentalRequest, RentalEntity rentalEntity);

    PaymentDetailsEntity payWithCreditCard(CreateRentalRequest createRentalRequest, RentalEntity rentalEntity);


}

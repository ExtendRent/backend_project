package source_files.services.paperWork.abstracts;

import source_files.controllers.paperWork.requests.Rental.CreateRentalRequest;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;

public interface PaymentService {

    PaymentDetailsEntity pay(CreateRentalRequest createRentalRequest, RentalEntity rentalEntity);

    PaymentDetailsEntity payWithCreditCard(CreateRentalRequest createRentalRequest, RentalEntity rentalEntity);


}

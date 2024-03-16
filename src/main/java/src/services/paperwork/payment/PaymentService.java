package src.services.paperwork.payment;

import src.controllers.paperwork.requests.Rental.CreateRentalRequest;
import src.data.models.paperwork_entities.paymentEntities.PaymentDetailsEntity;
import src.data.models.paperwork_entities.rentalEntities.RentalEntity;

public interface PaymentService {

    PaymentDetailsEntity pay(CreateRentalRequest createRentalRequest, RentalEntity rentalEntity);

    PaymentDetailsEntity payWithCreditCard(CreateRentalRequest createRentalRequest, RentalEntity rentalEntity);


}

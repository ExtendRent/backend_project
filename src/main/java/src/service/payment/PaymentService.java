package src.service.payment;

import src.controller.rental.requests.CreateRentalRequest;
import src.repository.payment.detail.PaymentDetailsEntity;
import src.repository.rental.RentalEntity;

public interface PaymentService {

    PaymentDetailsEntity pay(CreateRentalRequest createRentalRequest, RentalEntity rentalEntity);

    PaymentDetailsEntity payWithCreditCard(CreateRentalRequest createRentalRequest, RentalEntity rentalEntity);


}

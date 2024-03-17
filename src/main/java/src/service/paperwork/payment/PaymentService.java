package src.service.paperwork.payment;

import src.controller.paperwork.rental.requests.CreateRentalRequest;
import src.repository.paperwork.payment.detail.PaymentDetailsEntity;
import src.repository.paperwork.rental.RentalEntity;

public interface PaymentService {

    PaymentDetailsEntity pay(CreateRentalRequest createRentalRequest, RentalEntity rentalEntity);

    PaymentDetailsEntity payWithCreditCard(CreateRentalRequest createRentalRequest, RentalEntity rentalEntity);


}

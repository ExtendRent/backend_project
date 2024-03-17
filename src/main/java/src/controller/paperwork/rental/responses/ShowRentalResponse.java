package src.controller.paperwork.rental.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import src.controller.user.customer.responses.CustomerResponse;
import src.controller.vehicle.car.responses.CarResponse;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ShowRentalResponse {

    CustomerResponse customerResponse;
    CarResponse carResponse;

    LocalDate startDate;
    LocalDate endDate;

    String discountCode;
    double amount;
}

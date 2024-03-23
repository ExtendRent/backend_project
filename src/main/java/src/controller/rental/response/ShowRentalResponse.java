package src.controller.rental.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import src.controller.user.customer.response.CustomerResponse;
import src.controller.vehicle.car.response.CarResponse;

import java.time.LocalDate;

@ToString
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

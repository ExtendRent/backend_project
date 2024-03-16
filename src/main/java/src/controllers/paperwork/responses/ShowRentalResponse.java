package src.controllers.paperwork.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import src.controllers.user.responses.CustomerResponse;
import src.controllers.vehicle.responses.CarResponse;

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

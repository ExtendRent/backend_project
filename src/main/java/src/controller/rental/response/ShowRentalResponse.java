package src.controller.rental.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import src.controller.user.customer.response.CustomerResponse;
import src.controller.vehicle.car.response.CarResponse;

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

    @Override
    public String toString() {
        return "ShowRentalResponse{" +
                "customerResponse=" + customerResponse +
                ", carResponse=" + carResponse +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", discountCode='" + discountCode + '\'' +
                ", amount=" + amount +
                '}';
    }
}

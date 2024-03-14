package source_files.controllers.paperWork.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import source_files.controllers.user.dtos.CustomerDTO;
import source_files.controllers.vehicle.dtos.CarDTO;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ShowRentalResponse {

    CustomerDTO customerDTO;
    CarDTO carDTO;

    LocalDate startDate;
    LocalDate endDate;

    String discountCode;
    double amount;
}

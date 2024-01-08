package source_files.data.DTO.paperWorkDTOs;

import lombok.Getter;
import lombok.Setter;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.DTO.vehicleDTOs.CarDTO;

import java.time.LocalDate;

@Getter
@Setter
public class RentalDTO {
    CustomerDTO customerDTO;
    CarDTO carDTO;
    LocalDate startDate;
    LocalDate endDate;
    PaymentDetailsDTO paymentDetailsDTO;
}

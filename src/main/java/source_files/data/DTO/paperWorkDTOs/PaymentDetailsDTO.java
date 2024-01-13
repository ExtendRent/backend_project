package source_files.data.DTO.paperWorkDTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class PaymentDetailsDTO {
    int id;
    double amount;
    PaymentTypeDTO paymentTypeDTO;
    LocalDate createdDate;
}

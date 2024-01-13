package source_files.data.DTO.paperWorkDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor //iç içe response geldiği için
public class PaymentDTO {
    PaymentDetailsDTO paymentDetailsDTO;
    boolean paymentState = false;
}

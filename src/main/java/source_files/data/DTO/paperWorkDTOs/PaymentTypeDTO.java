package source_files.data.DTO.paperWorkDTOs;

import lombok.Getter;
import lombok.Setter;
import source_files.data.types.itemTypes.PaymentType;

@Getter
@Setter
public class PaymentTypeDTO {
    int id;
    String name;
    PaymentType paymentType;
}

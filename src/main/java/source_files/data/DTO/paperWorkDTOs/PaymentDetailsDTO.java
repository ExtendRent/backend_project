package source_files.data.DTO.paperWorkDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentTypeEntity;

@Builder
@Getter
@Setter
public class PaymentDetailsDTO {
    int amount;
    PaymentTypeEntity paymentType;
}

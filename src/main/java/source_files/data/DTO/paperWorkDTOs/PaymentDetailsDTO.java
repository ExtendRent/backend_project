package source_files.data.DTO.paperWorkDTOs;

import lombok.Builder;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentTypeEntity;

@Builder
public record PaymentDetailsDTO(int amount,
                                PaymentTypeEntity paymentType) {
}

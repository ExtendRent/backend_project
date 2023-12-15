package source_files.data.DTO.paperWorkDTOs;

import lombok.Builder;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.data.models.vehicleEntities.CarEntity;

import java.time.LocalDateTime;

@Builder
public record RentalDetailsDTO(CustomerEntity customer,
                               CarEntity car,
                               PaymentDetailsEntity paymentDetails,
                               LocalDateTime startDate,
                               LocalDateTime endDate) {
}

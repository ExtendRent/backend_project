package source_files.data.DTO.paperWorkDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.data.models.vehicleEntities.CarEntity;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class RentalDetailsDTO {
    CustomerEntity customer;
    CarEntity car;
    PaymentDetailsEntity paymentDetails;
    LocalDateTime startDate;
    LocalDateTime endDate;
}

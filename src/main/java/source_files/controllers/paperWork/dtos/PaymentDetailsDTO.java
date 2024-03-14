package source_files.controllers.paperWork.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class PaymentDetailsDTO {
    int id;
    int paymentTypeEntityId;
    double amount;
    String PaymentTypeEntityName;
    LocalDateTime createdDate;
    boolean isDeleted;
}

package source_files.data.requests.paperworkRequests.RentalRequests;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.DTO.paperWorkDTOs.PaymentDetailsDTO;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;
import source_files.data.requests.BaseRequest;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRentalRequest implements BaseRequest {

    @Min(value = 1, message = "CustomerId must be greater than 0")
    @NotNull(message = "CustomerId cannot be null")
    private int customerEntityId;

    @NotNull(message = "CarId cannot be null")
    @Min(value = 1, message = "CarId must be greater than 0")
    private int carEntityId;

    @NotNull(message = "StartDate cannot be null")
    @FutureOrPresent(message = "StartDate must be a future or present date")
    private LocalDate startDate;

    @NotNull(message = "EndDate cannot be null")
    @FutureOrPresent(message = "EndDate must be a future or present date")
    private LocalDate endDate;

    @NotNull(message = "PaymentTypeId cannot be null")
    private Integer paymentTypeId;

    @NotNull(message = "DiscountCode cannot be null")
    private Double amount;

    private String discountCode;

    private PaymentDetailsDTO paymentDetailsDTO;

    private CreditCardInformation creditCardInformation;
}

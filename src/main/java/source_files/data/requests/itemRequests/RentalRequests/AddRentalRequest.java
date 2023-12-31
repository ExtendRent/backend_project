package source_files.data.requests.itemRequests.RentalRequests;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import source_files.data.requests.BaseRequest;

import java.time.LocalDate;

@Getter
@Setter
public class AddRentalRequest implements BaseRequest {

    @Min(value = 1, message = "CustomerId must be greater than 0")
    private int customerEntityId;

    @Min(value = 1, message = "CarId must be greater than 0")
    private int carEntityId;

    @NotNull(message = "StartDate cannot be null")
    @FutureOrPresent(message = "StartDate must be a future or present date")
    private LocalDate startDate;

    @NotNull(message = "EndDate cannot be null")
    @FutureOrPresent(message = "EndDate must be a future or present date")
    private LocalDate endDate;

    @Nullable
    private String discountCode;

    @Min(value = 1, message = "PaymentTypeId must be greater than 0")
    private int paymentTypeEntityId;
}

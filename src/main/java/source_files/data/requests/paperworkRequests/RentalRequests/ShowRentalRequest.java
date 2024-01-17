package source_files.data.requests.paperworkRequests.RentalRequests;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowRentalRequest {

    @Nullable
    String discountCode;
    @Nullable
    @Min(value = 1, message = "CustomerId must be greater than 0")
    private Integer customerEntityId;
    @Min(value = 1, message = "CarId must be greater than 0")
    private int carEntityId;
    @NotNull(message = "StartDate cannot be null")
    @FutureOrPresent(message = "StartDate must be a future or present date")
    private LocalDate startDate;
    @NotNull(message = "EndDate cannot be null")
    @FutureOrPresent(message = "EndDate must be a future or present date")
    private LocalDate endDate;
}

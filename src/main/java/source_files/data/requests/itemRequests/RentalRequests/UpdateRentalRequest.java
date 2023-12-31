package source_files.data.requests.itemRequests.RentalRequests;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateRentalRequest {

    int id;

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

    @Min(value = 0, message = "StartKilometer must be greater than or equal to 0")
    @Pattern(regexp = "^[0-9]+$", message = "Kilometre sadece sayılardan oluşmalıdır.")
    private int startKilometer;

    @Min(value = 1, message = "PaymentTypeId must be greater than 0")
    private int paymentTypeEntityId;


}

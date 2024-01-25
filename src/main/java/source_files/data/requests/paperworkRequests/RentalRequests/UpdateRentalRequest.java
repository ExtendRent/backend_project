package source_files.data.requests.paperworkRequests.RentalRequests;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateRentalRequest {
    @NotNull(message = "id cannot be null")
    @Min(1)
    int id;

    @NotNull(message = "CustomerId cannot be null")
    @Min(value = 1, message = "CustomerId must be greater than 0")
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

    @NotNull(message = "StartKilometer cannot be null")
    @Min(value = 0, message = "StartKilometer must be greater than or equal to 0")
    @Pattern(regexp = "^[0-9]+$", message = "Kilometre sadece sayılardan oluşmalıdır.")
    private int startKilometer;
}

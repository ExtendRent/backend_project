package source_files.data.requests.paperworkRequests.RentalRequests;

import jakarta.validation.constraints.*;
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
    @NotBlank(message = "id cannot be blank")
    @Min(1)
    int id;

    @NotNull(message = "CustomerId cannot be null")
    @NotBlank(message = "CustomerId cannot be blank")
    @Min(value = 1, message = "CustomerId must be greater than 0")
    private int customerEntityId;

    @NotNull(message = "CarId cannot be null")
    @NotBlank(message = "CarId cannot be blank")
    @Min(value = 1, message = "CarId must be greater than 0")
    private int carEntityId;

    @NotBlank(message = "StartDate cannot be blank")
    @NotNull(message = "StartDate cannot be null")
    @FutureOrPresent(message = "StartDate must be a future or present date")
    private LocalDate startDate;

    @NotBlank(message = "EndDate cannot be blank")
    @NotNull(message = "EndDate cannot be null")
    @FutureOrPresent(message = "EndDate must be a future or present date")
    private LocalDate endDate;

    @NotNull(message = "StartKilometer cannot be null")
    @NotBlank(message = "StartKilometer cannot be blank")
    @Min(value = 0, message = "StartKilometer must be greater than or equal to 0")
    @Pattern(regexp = "^[0-9]+$", message = "Kilometre sadece sayılardan oluşmalıdır.")
    private int startKilometer;
}

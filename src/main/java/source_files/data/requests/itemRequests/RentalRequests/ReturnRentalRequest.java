package source_files.data.requests.itemRequests.RentalRequests;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import source_files.data.requests.BaseRequest;

import java.time.LocalDate;

@Getter
@Setter
public class ReturnRentalRequest implements BaseRequest {

    @Nullable
    LocalDate returnDate;
    @Min(value = 1, message = "rental ID must be greater than 0")
    @NotBlank
    private int rentalEntityId;
    @Min(value = 0, message = "End kilometer must be greater than or equal to 0")
    @Pattern(regexp = "^[0-9]+$", message = "Kilometre sadece sayılardan oluşmalıdır.")
    @NotBlank(message = "Bitiş kilometresi boş bırakılamaz")
    private int endKilometer;
}

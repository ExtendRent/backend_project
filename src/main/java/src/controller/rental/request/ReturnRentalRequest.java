package src.controller.rental.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReturnRentalRequest {

    @NotBlank
    @NotNull(message = "return date cannot be null")
    LocalDate returnDate;
    @Min(value = 1, message = "rental ID must be greater than 0")
    @NotNull(message = "rental ID cannot be null")
    private int id;
    @Min(value = 0, message = "End kilometer must be greater than or equal to 0")
    @Pattern(regexp = "^[0-9]+$", message = "Kilometre sadece sayılardan oluşmalıdır.")
    @NotNull(message = "End kilometer cannot be null")
    private int endKilometer;

}

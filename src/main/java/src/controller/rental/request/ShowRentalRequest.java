package src.controller.rental.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@ToString
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

    @NotNull(message = "CarId cannot be null")
    @Min(value = 1, message = "CarId must be greater than 0")
    private int carEntityId;


    @FutureOrPresent(message = "StartDate must be a future or present date")
    private LocalDate startDate;


    @FutureOrPresent(message = "EndDate must be a future or present date")
    private LocalDate endDate;

}

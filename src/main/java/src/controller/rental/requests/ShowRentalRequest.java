package src.controller.rental.requests;

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

    @NotNull(message = "CarId cannot be null")
    @Min(value = 1, message = "CarId must be greater than 0")
    private int carEntityId;


    @FutureOrPresent(message = "StartDate must be a future or present date")
    private LocalDate startDate;


    @FutureOrPresent(message = "EndDate must be a future or present date")
    private LocalDate endDate;

    @Override
    public String toString() {
        return "ShowRentalRequest{" +
                "discountCode='" + discountCode + '\'' +
                ", customerEntityId=" + customerEntityId +
                ", carEntityId=" + carEntityId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

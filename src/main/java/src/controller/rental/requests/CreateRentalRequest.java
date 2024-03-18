package src.controller.rental.requests;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import src.repository.payment.CreditCardInformation;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateRentalRequest {

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
    private int paymentTypeId;

    @NotNull(message = "amount cannot be null")
    private Double amount;

    private String discountCode;

    private CreditCardInformation creditCardInformation;

    @Override
    public String toString() {
        return "CreateRentalRequest{" +
                "customerEntityId=" + customerEntityId +
                ", carEntityId=" + carEntityId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", paymentTypeId=" + paymentTypeId +
                ", amount=" + amount +
                ", discountCode='" + discountCode + '\'' +
                ", creditCardInformation=" + creditCardInformation +
                '}';
    }
}

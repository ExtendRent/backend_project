package src.controller.rental.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateRentalRequest {
    @NotNull(message = "id cannot be null")
    @Min(1)
    int id;
    @Min(1)
    @NotNull(message = "customer id cannot be null")
    private int customerEntityId;//input olarak alınmayacak !!
    @Min(1)
    @NotNull(message = "car id cannot be null")
    private int carEntityId;//input olarak alınmayacak !!


    @Min(1)
    @NotNull(message = "payment details id cannot be null")
    private int paymentDetailsEntityId; //input olarak alınmayacak !!

    @NotNull(message = "start date cannot be null")
    private LocalDate startDate;//input olarak alınmayacak !!

    @NotNull(message = "end date cannot be null")
    private LocalDate endDate;//input olarak alınmayacak !!

    private LocalDate returnDate;//input olarak alınmayacak !!

    private Integer startKilometer;

    private Integer endKilometer;

    private Integer discountEntityId;//input olarak alınmayacak !!

    @NotNull
    private int rentalStatusId;

    @NotNull(message = "is active cannot be null")
    private boolean isActive;

}

package source_files.data.requests.paperworkRequests.RentalRequests;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountEntity;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.data.models.vehicleEntities.CarEntity;

import java.time.LocalDate;

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

    @NotNull(message = "is active cannot be null")
    private boolean isActive;

}

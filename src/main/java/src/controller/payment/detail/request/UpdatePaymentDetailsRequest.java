package src.controller.payment.detail.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePaymentDetailsRequest {
    @NotNull(message = "Id null geçilemez")
    private int id;
    @NotNull(message = "tutar null geçilemez")
    @Min(value = 0, message = "tutar 0'dan küçük olamaz")
    private double amount;

}

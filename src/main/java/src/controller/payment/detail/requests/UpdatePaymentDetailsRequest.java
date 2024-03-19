package src.controller.payment.detail.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    @Override
    public String toString() {
        return "UpdatePaymentDetailsRequest{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}

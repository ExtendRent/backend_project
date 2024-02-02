package source_files.data.requests.paperworkRequests.paymentRequests;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import source_files.data.requests.BaseRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePaymentDetailsRequest extends BaseRequest {

    @NotNull
    private int id;

    @NotNull
    private double amount;

    @NotNull(message = "Ödeme tipi null geçilemez")
    private int paymentTypeEntityId;

    @NotNull(message = "aktiflik null geçilemez")
    private boolean isActive;
}

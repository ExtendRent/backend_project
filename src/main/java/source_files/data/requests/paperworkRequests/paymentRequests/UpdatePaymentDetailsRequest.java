package source_files.data.requests.paperworkRequests.paymentRequests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import source_files.data.requests.BaseRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePaymentDetailsRequest extends BaseRequest {
    @NotNull(message = "Id null geçilemez")
    private int id;
    @NotNull(message = "tutar null geçilemez")
    @Min(value = 0, message = "tutar 0'dan küçük olamaz")
    private double amount;


}

package source_files.data.requests.paperworkRequests.paymentRequests;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import source_files.data.enums.types.itemTypes.ItemType;
import source_files.data.requests.BaseRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePaymentDetailsRequest extends BaseRequest {
    private final ItemType itemType = ItemType.PAYMENT_DETAILS;
    @NotNull(message = "Id null geçilemez")
    private int id;
    @NotNull(message = "tutar null geçilemez")
    private double amount;
}

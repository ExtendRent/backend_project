package source_files.data.requests.paperworkRequests.paymentRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.requests.BaseRequest;
import source_files.data.types.itemTypes.PaymentType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentDetailsRequest implements BaseRequest {
    @NotBlank(message = "id boş geçilemez")
    @NotNull
    private int paymentDetailsId;
    @NotBlank(message = "tutar boş geçilemez")
    @NotNull
    private double amount;
    private PaymentType paymentType;
}

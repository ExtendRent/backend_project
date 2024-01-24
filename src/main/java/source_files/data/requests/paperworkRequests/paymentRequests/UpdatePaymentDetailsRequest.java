package source_files.data.requests.paperworkRequests.paymentRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.requests.BaseRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentDetailsRequest implements BaseRequest {

    @NotBlank(message = "id boş geçilemez")
    @NotNull
    private int id;

    @NotBlank(message = "tutar boş geçilemez")
    @NotNull
    private double amount;

    @NotNull(message = "Ödeme tipi null geçilemez")
    @NotBlank(message = "Ödeme tipi boş geçilemez")
    private int paymentTypeEntityId;

    @NotNull(message = "aktiflik null geçilemez")
    @NotBlank(message = "aktiflik boş geçilemez")
    private boolean isActive;
}

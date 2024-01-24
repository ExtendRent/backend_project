package source_files.data.requests.paperworkRequests.paymentRequests;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.types.itemTypes.PaymentType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentTypeRequest {
    @NotBlank(message = "Ödeme tipi boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Ödeme tipi sadece harflerden oluşmalıdır.")
    private String paymentTypeEntityName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private boolean isActive;
}

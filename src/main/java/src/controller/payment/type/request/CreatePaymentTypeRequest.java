package src.controller.payment.type.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import src.service.payment.type.model.DefaultPaymentType;

@ToString
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
    @Pattern(regexp = "^[A-Z-_]+$", message = "Ödeme tipi sadece büyük harflerden ve boşluksuz olmalıdır.")
    private DefaultPaymentType paymentType;

    @NotNull
    private boolean isActive;

}

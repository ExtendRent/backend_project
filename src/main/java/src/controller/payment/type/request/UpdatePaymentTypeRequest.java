package src.controller.payment.type.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentTypeRequest {
    @NotNull
    private int id;


    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[\sa-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = "Ödeme tipi sadece harflerden oluşmalıdır.")
    private String name;

    @NotNull
    private boolean isActive;

}

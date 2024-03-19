package src.controller.payment.type.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Override
    public String toString() {
        return "UpdatePaymentTypeRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}

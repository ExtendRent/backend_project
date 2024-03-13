package source_files.data.requests.paperworkRequests.paymentRequests;

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

    @NotNull
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[\sa-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = "Ödeme tipi sadece harflerden oluşmalıdır.")
    private String name;

    @NotNull
    private boolean isActive;
}

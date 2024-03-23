package src.controller.vehicle.features.common.shift.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateShiftTypeRequest {

    @NotBlank(message = "Vites ismi boş geçilemez")
    @Size(min = 2, message = "Vites ismi en az 2 karakter olmalıdır.")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Vites ismi sadece harflerden ve boşluklardan oluşmalıdır.")
    String name;
}

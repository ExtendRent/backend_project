package src.controller.vehicle.features.common.fuel.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFuelTypeRequest {

    @NotBlank(message = "Yakıt ismi boş geçilemez")
    @Size(min = 2, message = "Yakıt ismi en az 2 karakter olmalıdır.")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "yakıt ismi sadece harflerden ve boşluklardan oluşmalıdır.")
    String name;

}

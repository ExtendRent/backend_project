package source_files.controllers.vehicle.requests.VehicleFeaturesRequests.ShiftTypeRequests;

import jakarta.validation.constraints.NotBlank;
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
public class UpdateShiftTypeRequest {
    @NotNull(message = "id null olamaz")
    int id;
    @NotNull(message = "Vites ismi null olamaz")
    @NotBlank(message = "Vites ismi boş geçilemez")
    @Size(min = 2, message = "Vites ismi en az 2 karakter olmalıdır.")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Vites ismi sadece harflerden ve boşluklardan oluşmalıdır.")
    String name;

}

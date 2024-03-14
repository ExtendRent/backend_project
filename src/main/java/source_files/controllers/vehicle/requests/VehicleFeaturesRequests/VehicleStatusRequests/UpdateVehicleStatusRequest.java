package source_files.controllers.vehicle.requests.VehicleFeaturesRequests.VehicleStatusRequests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehicleStatusRequest {
    @NotNull
    @Min(1)
    int id;
    @NotNull(message = "Araç durumu ismi null olamaz")
    @NotBlank(message = "Araç durumu ismi boş geçilemez")
    @Size(min = 2, message = "Araç durumu ismi en az 2 karakter olmalıdır.")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Araç durumu ismi sadece harflerden ve boşluklardan oluşmalıdır.")
    String name;
}

package src.controller.vehicle.features.common.status.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import src.service.vehicle.features.common.status.model.DefaultVehicleStatus;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateVehicleStatusRequest {

    @NotBlank(message = "Araç durumu ismi boş geçilemez")
    @Size(min = 2, message = "Araç durumu ismi en az 2 karakter olmalıdır.")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Araç durumu ismi sadece harflerden ve boşluklardan oluşmalıdır.")
    String name;

    @Pattern(regexp = "^[A-Z-_]+$", message = "Araç durumu tipi sadece büyük harflerden ve boşluksuz olmalıdır.")
    DefaultVehicleStatus vehicleStatus;

}

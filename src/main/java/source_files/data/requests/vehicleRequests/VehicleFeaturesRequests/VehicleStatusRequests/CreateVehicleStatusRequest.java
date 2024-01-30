package source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.VehicleStatusRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.Status.DefaultVehicleStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateVehicleStatusRequest {
    @NotNull(message = "Araç durumu ismi null olamaz")
    @NotBlank(message = "Araç durumu ismi boş geçilemez")
    @Size(min = 2, message = "Araç durumu ismi en az 2 karakter olmalıdır.")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Araç durumu ismi sadece harflerden ve boşluklardan oluşmalıdır.")
    String name;

    @Pattern(regexp = "^[A-Z-_]+$", message = "Araç durumu tipi sadece büyük harflerden ve boşluksuz olmalıdır.")
    DefaultVehicleStatus vehicleStatus;
}

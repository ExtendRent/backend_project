package source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.VehicleStatusRequests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.enums.types.itemTypes.ItemType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehicleStatusRequest {
    private final ItemType itemType = ItemType.VEHICLE_STATUS;
    @NotNull
    @Min(1)
    int id;
    @NotNull(message = "Araç durumu ismi null olamaz")
    @NotBlank(message = "Araç durumu ismi boş geçilemez")
    @Size(min = 2, message = "Araç durumu ismi en az 2 karakter olmalıdır.")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Araç durumu ismi sadece harflerden ve boşluklardan oluşmalıdır.")
    String name;
}

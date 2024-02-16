package source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.enums.types.itemTypes.ItemType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFuelTypeRequest {
    private final ItemType itemType = ItemType.FUEL_TYPE;
    @NotNull(message = "Yakıt ismi null olamaz")
    @NotBlank(message = "Yakıt ismi boş geçilemez")
    @Size(min = 2, message = "Yakıt ismi en az 2 karakter olmalıdır.")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "yakıt ismi sadece harflerden ve boşluklardan oluşmalıdır.")
    String name;
}

package source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests;

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
public class UpdateFuelTypeRequest {
    @NotNull(message = "id null olamaz")
    int id;
    @NotNull(message = "Yakıt ismi null olamaz")
    @NotBlank(message = "Yakıt ismi boş geçilemez")
    @Size(min = 2, message = "Yakıt ismi en az 2 karakter olmalıdır.")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "yakıt ismi sadece harflerden ve boşluklardan oluşmalıdır.")
    String name;

    @NotNull(message = "Yakıt tipi null olamaz")
    @NotBlank(message = "Yakıt tipi adı boş geçilemez")
    @Size(min = 2, message = "Yakıt tipi en az 2 karakter olmalıdır.")
    @Pattern(regexp = "^[A-Z]+$", message = "yakıt tipi sadece BÜYÜK harflerden oluşmalıdır.")
    String fuelType;
}

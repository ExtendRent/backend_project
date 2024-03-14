package source_files.controllers.vehicle.requests.VehicleFeaturesRequests.BrandRequests;

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
public class UpdateBrandRequest {
    @NotNull(message = "id null olamaz")
    int id;
    @NotNull(message = "Marka null olamaz")
    @NotBlank(message = "Marka adı boş geçilemez")
    @Size(min = 2, message = "Marka en az 2 karakter olmalıdır.")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = "Marka sadece harflerden oluşmalıdır.")
    String name;

    int brandImageEntityId;
}

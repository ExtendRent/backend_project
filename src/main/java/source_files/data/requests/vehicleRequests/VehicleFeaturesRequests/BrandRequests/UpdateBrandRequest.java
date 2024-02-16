package source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests;

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
public class UpdateBrandRequest {
    private final ItemType itemType = ItemType.BRAND;
    @NotNull(message = "id null olamaz")
    int id;
    @NotNull(message = "Marka null olamaz")
    @NotBlank(message = "Marka adı boş geçilemez")
    @Size(min = 2, message = "Marka en az 2 karakter olmalıdır.")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = "Marka sadece harflerden oluşmalıdır.")
    String name;

    int brandImageEntityId;
}

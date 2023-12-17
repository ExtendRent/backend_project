package source_files.data.requests.itemRequests.VehicleFeaturesRequests.BrandRequests;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBrandRequest {
    @Size(min = 2, message = "Marka en az 2 karakter olmalıdır.")
    String name;
}

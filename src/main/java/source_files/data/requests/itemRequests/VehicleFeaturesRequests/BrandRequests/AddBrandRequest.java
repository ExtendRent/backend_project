package source_files.data.requests.itemRequests.VehicleFeaturesRequests.BrandRequests;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.requests.BaseRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBrandRequest extends BaseRequest {
    @Size(min = 2, message = "Marka en az 2 karakter olmalıdır.")
    String name;
}

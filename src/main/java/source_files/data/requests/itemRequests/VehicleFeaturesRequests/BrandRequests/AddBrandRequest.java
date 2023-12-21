package source_files.data.requests.itemRequests.VehicleFeaturesRequests.BrandRequests;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import source_files.data.requests.BaseRequest;

@Getter
@Setter
public class AddBrandRequest extends BaseRequest {
    @Size(min = 2, message = "Marka en az 2 karakter olmalıdır.")
    String name;
}

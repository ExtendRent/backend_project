package source_files.data.requests.itemRequests.VehicleFeaturesRequests.CarModelRequests;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import source_files.data.requests.BaseRequest;

@Getter
@Setter
@Builder
public class AddCarModelRequest extends BaseRequest {
    @Size(min = 2, message = "Marka en az 2 karakter olmalıdır.")
    String name;
    int brandId;
}

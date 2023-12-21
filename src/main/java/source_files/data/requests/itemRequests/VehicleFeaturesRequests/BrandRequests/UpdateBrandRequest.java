package source_files.data.requests.itemRequests.VehicleFeaturesRequests.BrandRequests;

import lombok.Getter;
import lombok.Setter;
import source_files.data.requests.BaseRequest;

@Getter
@Setter
public class UpdateBrandRequest extends BaseRequest {
    int id;
    String name;
}

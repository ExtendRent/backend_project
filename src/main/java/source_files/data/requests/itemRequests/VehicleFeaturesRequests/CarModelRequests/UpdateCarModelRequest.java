package source_files.data.requests.itemRequests.VehicleFeaturesRequests.CarModelRequests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import source_files.data.requests.BaseRequest;

@Getter
@Setter
@Builder
public class UpdateCarModelRequest extends BaseRequest {

    int id;
    String name;
    int brandId;
}

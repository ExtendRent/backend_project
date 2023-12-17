package source_files.data.requests.itemRequests.VehicleFeaturesRequests.CarModelRequests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddCarModelRequest {
    // int id;
    String name;
    int brandId;
}

package source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarSegmentRequests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.enums.types.itemTypes.ItemType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarSegmentRequest {
    private final ItemType itemType = ItemType.CAR_SEGMENT;
    int id;
    String name;
}

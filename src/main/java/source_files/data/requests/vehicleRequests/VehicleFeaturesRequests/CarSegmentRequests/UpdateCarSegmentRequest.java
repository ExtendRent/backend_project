package source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarSegmentRequests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarSegmentRequest {
    int id;
    String name;
}

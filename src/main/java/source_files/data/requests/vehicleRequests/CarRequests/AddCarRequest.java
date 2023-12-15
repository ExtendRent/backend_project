package source_files.data.requests.vehicleRequests.CarRequests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import source_files.data.types.DrivingLicenseType;

import java.util.List;

//@Builder
@Getter
@Setter
@Builder
public class AddCarRequest {

    int branId;
    int bodyTypeId;
    int modelId;
    int colorId;
    int year;
    String details;
    double rentalPrice;
    String licensePlate;
    int kilometer;
    List<DrivingLicenseType> expectedDrivingLicenseTypes;
}

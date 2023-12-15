package source_files.data.requests.vehicleRequests.CarRequests;

import lombok.Getter;
import lombok.Setter;
import source_files.data.types.DrivingLicenseType;

import java.util.List;

//@Builder
@Getter
@Setter
public class AddCarRequest {

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

package source_files.data.requests.vehicleRequests.CarRequests;

import lombok.Getter;
import lombok.Setter;
import source_files.data.types.DrivingLicenseType;

import java.util.List;

@Getter
@Setter
public class UpdateCarRequest {
    int id;
    int brandId;
    int modelId;
    int bodyTypeId;
    int colorId;
    int year;
    String details;
    double rentalPrice;
    String licensePlate;
    int kilometer;
    List<DrivingLicenseType> expectedDrivingLicenseTypes;
}

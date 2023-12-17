package source_files.data.requests.vehicleRequests.CarRequests;

import jakarta.validation.constraints.*;
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


    int brandId;
    int bodyTypeId;
    @Min(0)
    int modelId;
    @Min(0)
    int colorId;
    @Min(value = 2005, message = "Yıl en küçük 2005 olmalıdır.")
    @Max(value = 2024, message = "Yıl en çok 2024 olmalıdır.")
    int year;
    String details;
    @DecimalMin(value = "0.0", message = "Kiralama ücreti 0 dan küçük olamaz.")
    double rentalPrice;
    @NotBlank(message = "License plate can not be blank")
    @Pattern(regexp = "^[0-9]{2} [A-Z]{1,3} [0-9]{3}$", message = "Invalid license plate format")
    String licensePlate;
    @Min(value = 0, message = "Kilometre 0 dan küçük olamaz.")
    int kilometer;
    List<DrivingLicenseType> expectedDrivingLicenseTypes;
}

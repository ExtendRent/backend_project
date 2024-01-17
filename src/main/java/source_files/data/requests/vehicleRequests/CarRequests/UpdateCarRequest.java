package source_files.data.requests.vehicleRequests.CarRequests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.requests.BaseRequest;
import source_files.data.types.itemTypes.DrivingLicenseType;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest implements BaseRequest {

    @Min(1)
    int id;
    @Min(1)
    int brandEntityId;
    @Min(1)
    int carModelEntityId;
    @Min(1)
    int carBodyTypeEntityId;
    @Min(1)
    int colorEntityId;
    @Min(value = 2005, message = "Yıl en küçük 2005 olmalıdır.")
    @Max(value = 2024, message = "Yıl en yüksek 2024 olmalıdır.")
    int year;
    String details;
    @DecimalMin(value = "0.0", message = "Kiralama ücreti 0 dan küçük olamaz.")
    double rentalPrice;
    @NotBlank(message = "License plate can not be blank")
    @Pattern(regexp = "^[0-9]{2} [A-Z]{1,3} [0-9]{3}$", message = "Invalid license plate format")
    String licensePlate;
    @Min(value = 0, message = "Kilometre 0 dan küçük olamaz.")
    int kilometer;
    @NotNull
    List<String> imagesEntityImagePath;
    @NotNull
    @NotEmpty
    List<DrivingLicenseType> expectedDrivingLicenseTypes;
    @NotNull
    String shiftType;
    @NotNull
    String fuelType;
    @Min(1)
    @Max(15)
    private int seat;
    @Min(1)
    @Max(15)
    private int luggage;
    private boolean isAvailable = true;
    private LocalDate availabilityDate = null;
}

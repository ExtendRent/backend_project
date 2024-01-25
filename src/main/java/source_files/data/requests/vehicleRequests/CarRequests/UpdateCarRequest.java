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
    @NotNull(message = "id null olamaz")
    int id;

    @NotNull(message = "brandId null olamaz")
    @Min(1)
    int brandEntityId;

    @NotNull(message = "id null olamaz")
    @Min(1)
    int carModelEntityId;

    @NotNull(message = "carBodyTypeId null olamaz")
    @Min(1)
    int carBodyTypeEntityId;
    @NotNull(message = "colorId null olamaz")
    @Min(1)
    int colorEntityId;

    @NotNull(message = "yıl null olamaz.")
    @Min(value = 2005, message = "Yıl en küçük 2005 olmalıdır.")
    @Max(value = 2024, message = "Yıl en yüksek 2024 olmalıdır.")
    int year;

    @Size(max = 500, message = "Acıklama en fazla 500 karakter olmalıdır.")
    String details;

    @DecimalMin(value = "0.0", message = "Kiralama ücreti 0 dan küçük olamaz.")
    @NotNull(message = "rentalPrice can not be null")
    double rentalPrice;

    @NotBlank(message = "License plate can not be blank")
    @NotNull(message = "License plate can not be null")
    @Pattern(regexp = "^[0-9]{2} [A-Z]{1,3} [0-9]{3}$", message = "Invalid license plate format")
    String licensePlate;

    @NotNull(message = "kilometer null olamaz")
    @Min(value = 0, message = "Kilometre 0 dan küçük olamaz.")
    int kilometer;

    @NotNull(message = "imagePaths null olamaz")
    List<String> imagePaths;

    @NotNull(message = "expectedDrivingLicenseTypes null olamaz")
    @NotEmpty
    List<DrivingLicenseType> expectedDrivingLicenseTypes;

    @NotNull
    int shiftTypeEntityId;

    @NotNull
    int fuelTypeEntityId;

    @NotNull(message = "seat null olamaz")
    @Min(1)
    @Max(15)
    int seat;

    @NotNull(message = "luggage null olamaz")
    @Min(1)
    @Max(15)
    int luggage;

    private boolean isAvailable = true;
    private LocalDate availabilityDate = null;
}

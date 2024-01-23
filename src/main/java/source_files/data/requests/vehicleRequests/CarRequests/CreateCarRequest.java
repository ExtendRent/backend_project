package source_files.data.requests.vehicleRequests.CarRequests;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import source_files.data.requests.BaseRequest;
import source_files.data.types.itemTypes.DrivingLicenseType;

import java.util.List;

//@Builder
@Getter
@Setter
public class CreateCarRequest implements BaseRequest {
    @NotNull(message = "brandId null olamaz")
    @NotBlank(message = "brandId boş geçilemez")
    @Min(1)
    int brandEntityId;

    @NotNull(message = "id null olamaz")
    @NotBlank(message = "id boş geçilemez")
    @Min(1)
    int carModelEntityId;

    @NotNull(message = "carBodyTypeId null olamaz")
    @NotBlank(message = "carBodyTypeId boş geçilemez")
    @Min(1)
    int carBodyTypeEntityId;
    @NotNull(message = "colorId null olamaz")
    @NotBlank(message = "colorId boş geçilemez")
    @Min(1)
    int colorEntityId;

    @NotNull(message = "yıl null olamaz.")
    @NotBlank(message = "yıl boş olamaz.")
    @Min(value = 2005, message = "Yıl en küçük 2005 olmalıdır.")
    @Max(value = 2024, message = "Yıl en yüksek 2024 olmalıdır.")
    int year;

    @Size(max = 500, message = "Acıklama en fazla 500 karakter olmalıdır.")
    String details;

    @DecimalMin(value = "0.0", message = "Kiralama ücreti 0 dan küçük olamaz.")
    @NotBlank(message = "rentalPrice can not be blank")
    @NotNull(message = "rentalPrice can not be null")
    double rentalPrice;

    @NotBlank(message = "License plate can not be blank")
    @NotNull(message = "License plate can not be null")
    @Pattern(regexp = "^[0-9]{2} [A-Z]{1,3} [0-9]{3}$", message = "Invalid license plate format")
    String licensePlate;

    @NotNull(message = "kilometer null olamaz")
    @NotBlank(message = "kilometer boş olamaz")
    @Min(value = 0, message = "Kilometre 0 dan küçük olamaz.")
    int kilometer;

    @NotNull(message = "imagePaths null olamaz")
    List<String> imagePaths;

    @NotNull(message = "expectedDrivingLicenseTypes null olamaz")
    @NotEmpty
    List<DrivingLicenseType> expectedDrivingLicenseTypes;

    @NotNull
    @NotBlank(message = "shiftType boş olamaz")
    String shiftType;

    @NotBlank(message = "fuelType boş olamaz")
    @NotNull
    String fuelType;

    @NotNull(message = "seat null olamaz")
    @NotBlank(message = "seat boş olamaz")
    @Min(1)
    @Max(15)
    int seat;

    @NotNull(message = "luggage null olamaz")
    @NotBlank(message = "luggage boş olamaz")
    @Min(1)
    @Max(15)
    int luggage;
}

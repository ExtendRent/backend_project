package src.controllers.item.requests;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateDrivingLicenseTypeRequest {
    @Min(1)
    int id;
    @NotNull(message = "Driving license type name can not be null")
    @NotBlank(message = "Driving license type name can not be blank")
    @Pattern(regexp = "^[A-Z]{1,3}$", message = "Invalid driving license type name")
    String name;

    @NotNull(message = "Description can not be null")
    @NotBlank(message = "Description can not be blank")
    @Size(max = 30)
    String description;

    @NotNull(message = "License level can not be null")
    @Min(0)
    int licenseLevel;
}

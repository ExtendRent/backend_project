package src.controller.license.request;

import jakarta.validation.constraints.*;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateDrivingLicenseTypeRequest {
    @NotBlank(message = "Driving license type name can not be blank")
    @Pattern(regexp = "^[A-Z]{1,3}$", message = "Invalid driving license type name")
    String name;

    @NotBlank(message = "Description can not be blank")
    @Size(max = 30)
    String description;

    @NotNull(message = "License level can not be null")
    @Min(0)
    int licenseLevel;

}

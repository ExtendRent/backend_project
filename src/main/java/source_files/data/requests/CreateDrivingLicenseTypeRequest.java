package source_files.data.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateDrivingLicenseTypeRequest {
    @NotNull(message = "Driving license type name can not be null")
    @NotBlank(message = "Driving license type name can not be blank")
    @Pattern(regexp = "^[A-Z]{1,3}$", message = "Invalid driving license type name")
    String name;

    @NotNull(message = "Description can not be null")
    @NotBlank(message = "Description can not be blank")
    @Size(max = 30)
    String description;
}

package source_files.data.requests.userRequests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.types.DrivingLicenseType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Builder
public class AddCustomerRequest {
    @NotBlank(message = "hata mesajı")
    @Size(min = 2, max = 20)
    String name;

    @NotBlank(message = "hata mesajı")
    @Size(min = 2, max = 20)
    String surname;

    @Email//-> Email alırken @gmail @hotmail gibi kullanımları denetler.
    @NotBlank(message = "hata mesajı")
    String emailAddress;

    @Size(min = 8, max = 30)
    @NotBlank(message = "hata mesajı")
    String password;

    @NotBlank(message = "hata mesajı")
    @Size(min = 10, max = 10)//-> 0 yazılmadan telefon numaraları 10 haneli.
    String phoneNumber;

    @Size(max = 6)//-> Ehliyet seri numarası 6 haneli olur.
    String drivingLicenseNumber;
    @Min(0)
    @Max(16)
    DrivingLicenseType drivingLicenseType;
}

package source_files.data.requests.userRequests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record AddCustomerRequest(
        @NotBlank(message = "hata mesajı")
        @Size(min = 2, max = 20)
        String name,

        @NotBlank(message = "hata mesajı")
        @Size(min = 2, max = 20)
        String surname,

        @Email//-> Email alırken @gmail @hotmail gibi kullanımları denetler.
        @NotBlank(message = "hata mesajı")
        String emailAddress,

        @Size(min = 8, max = 30)
        @NotBlank(message = "hata mesajı")

        String password,
        @NotBlank(message = "hata mesajı")
        @Size(min = 10, max = 10)//-> 0 yazılmadan telefon numaraları 10 haneli.
        int phoneNumber,

        @Size(max = 6)//-> Ehliyet seri numarası 6 haneli olur.
        int drivingLicenseNumber) {
}

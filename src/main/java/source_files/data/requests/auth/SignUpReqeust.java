package source_files.data.requests.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import source_files.data.types.itemTypes.DrivingLicenseType;
import source_files.data.types.userTypes.UserRole;

import java.util.List;

@Data
@AllArgsConstructor
public class SignUpReqeust {

    @Size(min = 2, max = 20)
    String name;

    @NotBlank(message = "Soyisim boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    String surname;

    @Email//-> Email alırken @gmail @hotmail gibi kullanımları denetler.
    @NotBlank(message = "Admin mail adresi boş geçilemez")
    String emailAddress;

    @Size(min = 8, max = 30)
    @NotBlank(message = "Şifre boş geçilemez")
    String password;

    @NotBlank(message = "telefon numarası boş geçilemez")
    @Size(min = 10, max = 10, message = "Telefon numarası 10 hane olmalıdır.")
    @Pattern(regexp = "^[0-9]+$", message = "Telefon numarası sadece sayılardan oluşmalıdır.")
    String phoneNumber;

    Double salary;

    String drivingLicenseNumber;

    List<DrivingLicenseType> drivingLicenseTypes;

    UserRole authority;
}

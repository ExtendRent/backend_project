package source_files.data.requests.userRequests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.Status.UserStatus;
import source_files.data.requests.BaseRequest;
import source_files.data.types.userTypes.UserRole;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateAdminRequest implements BaseRequest {


    @NotNull(message = "id null olamaz")
    @NotBlank(message = "id boş geçilemez")
    int id;

    @NotNull(message = "isim null olamaz")
    @NotBlank(message = "isim boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    String name;


    @NotNull(message = "soyisim null olamaz")
    @NotBlank(message = "Soyisim boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    String surname;


    @NotNull(message = "Admin mail adresi null olamaz")
    @NotBlank(message = "Admin mail adresi boş geçilemez")
    @Email//-> Email alırken @gmail @hotmail gibi kullanımları denetler.
    String emailAddress;


    @NotNull(message = "Şifre null olamaz")
    @NotBlank(message = "Şifre boş geçilemez")
    @Size(min = 8, max = 30)
    String password;


    @NotNull(message = "telefon numarası null olamaz")
    @NotBlank(message = "telefon numarası boş geçilemez")
    @Size(min = 10, max = 10, message = "Telefon numarası 10 hane olmalıdır.")
    @Pattern(regexp = "^[0-9]+$", message = "Telefon numarası sadece sayılardan oluşmalıdır.")
    String phoneNumber;

    @NotNull(message = "maaş null olamaz")
    @NotBlank(message = "maaş boş geçilemez")
    @Min(0)
    Double salary;

    String imagePath;
    UserStatus status = UserStatus.VERIFIED;
    private UserRole authority = UserRole.ADMIN;
}

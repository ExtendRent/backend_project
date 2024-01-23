package source_files.data.requests.userRequests;

import jakarta.validation.constraints.*;
import lombok.*;
import source_files.data.Status.UserStatus;
import source_files.data.requests.BaseRequest;
import source_files.data.types.userTypes.UserRole;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateAdminRequest implements BaseRequest {
    @NotNull(message = "isim null olamaz")
    @NotBlank(message = "isim boş geçilemez")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    @Size(min = 2, max = 20)
    String name;

    @NotNull(message = "soyisim null olamaz")
    @NotBlank(message = "Soyisim boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    String surname;

    @Email//-> Email alırken @gmail @hotmail gibi kullanımları denetler.
    @NotNull(message = "Admin mail adresi null olamaz")
    @NotBlank(message = "Admin mail adresi boş geçilemez")
    String emailAddress;

    @Size(min = 8, max = 30)
    @NotNull(message = "Şifre null olamaz")
    @NotBlank(message = "Şifre boş geçilemez")
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

package source_files.data.requests.userRequests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.enums.Status.DefaultUserStatus;
import source_files.data.requests.BaseRequest;
import source_files.data.enums.types.userTypes.UserRole;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateAdminRequest extends BaseRequest {


    @NotNull(message = "maaş null olamaz")
    @NotBlank(message = "maaş boş geçilemez")
    @Min(0)
    Double salary;
    @NotNull(message = "id null olamaz")
    private int id;
    @NotNull(message = "isim null olamaz")
    @NotBlank(message = "isim boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    private String name;
    @NotNull(message = "soyisim null olamaz")
    @NotBlank(message = "Soyisim boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    private String surname;
    @NotNull(message = "Admin mail adresi null olamaz")
    @NotBlank(message = "Admin mail adresi boş geçilemez")
    @Email//-> Email alırken @gmail @hotmail gibi kullanımları denetler.
    private String emailAddress;
    @NotNull(message = "Şifre null olamaz")
    @NotBlank(message = "Şifre boş geçilemez")
    @Size(min = 8, max = 30)
    private String password;
    @NotNull(message = "telefon numarası null olamaz")
    @NotBlank(message = "Çalışan telefon numarası boş geçilemez")
    @Size(min = 10, max = 10, message = "Telefon numarası 10 hane olmalıdır.")
    @Pattern(regexp = "^[0-9]+$", message = "Telefon numarası sadece sayılardan oluşmalıdır.")
    private String phoneNumber;
    private String imagePath;
    private DefaultUserStatus status;
    private UserRole authority = UserRole.ADMIN;
}

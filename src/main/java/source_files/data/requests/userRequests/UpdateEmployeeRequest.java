package source_files.data.requests.userRequests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.enums.defaultDataEnums.Status.DefaultUserStatus;
import source_files.data.requests.BaseRequest;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateEmployeeRequest extends BaseRequest {

    @NotNull(message = "id cannot be null")
    int id;
    @NotNull(message = "id cannot be null")
    @NotBlank(message = "Çalışan adı boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    String name;
    @NotNull(message = "soyisim null olamaz")
    @NotBlank(message = "Çalışan soyadı boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    String surname;
    @Email//-> Email alırken @gmail @hotmail gibi kullanımları denetler.
    @NotNull(message = "mail adresi null olamaz")
    @NotBlank(message = "Çalışan mail adresi boş geçilemez")
    String emailAddress;

    @Size(min = 8, max = 30)
    @NotNull(message = "Şifre null olamaz")
    @NotBlank(message = "Çalışan şifresi boş geçilemez")
    String password;
    @NotBlank(message = "Çalışan telefon numarası boş geçilemez")
    @NotNull(message = "Telefon numarası null olamaz")
    @Size(min = 10, max = 10, message = "Telefon numarası 10 hane olmalıdır.")
    @Pattern(regexp = "^[0-9]+$", message = "Telefon numarası sadece sayılardan oluşmalıdır.")
    String phoneNumber;
    @NotNull(message = "maaş null olamaz")
    @Min(0)
    Double salary;
    private DefaultUserStatus status;
    private int userImageEntityId;
}

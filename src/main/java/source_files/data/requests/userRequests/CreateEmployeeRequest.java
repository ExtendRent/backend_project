package source_files.data.requests.userRequests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.requests.BaseRequest;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateEmployeeRequest implements BaseRequest {

    @NotNull(message = "isim null olamaz")
    @NotBlank(message = "Çalışan adı boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    String name;

    @NotNull(message = "soyisim null olamaz")
    @NotBlank(message = "Çalışan soyadı boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    String surname;

    @NotNull(message = "Çalışan mail adresi null olamaz")
    @NotBlank(message = "Çalışan mail adresi boş geçilemez")
    @Email//-> Email alırken @gmail @hotmail gibi kullanımları denetler.
    String emailAddress;

    @NotNull(message = "Şifre null olamaz")
    @NotBlank(message = "Çalışan şifresi boş geçilemez")
    @Size(min = 8, max = 30)
    String password;

    @NotNull(message = "Çalışan telefon numarası null olamaz")
    @NotBlank(message = "Çalışan telefon numarası boş geçilemez")
    @Size(min = 10, max = 10, message = "Telefon numarası 10 hane olmalıdır.")
    @Pattern(regexp = "^[0-9]+$", message = "Telefon numarası sadece sayılardan oluşmalıdır.")
    String phoneNumber;

    @NotNull(message = "Maaş null olamaz")
    @NotBlank(message = "Maas boş geçilemez")
    @Min(0)
    Double salary;
}

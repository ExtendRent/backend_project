package source_files.controllers.user.requests;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateAdminRequest {
    @NotNull(message = "isim null olamaz")
    @NotBlank(message = "isim boş geçilemez")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    @Size(min = 2, max = 20)
    private String name;
    @NotNull(message = "soyisim null olamaz")
    @NotBlank(message = "Soyisim boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    private String surname;
    @Email//-> Email alırken @gmail @hotmail gibi kullanımları denetler.
    @NotNull(message = "Admin mail adresi null olamaz")
    @NotBlank(message = "Admin mail adresi boş geçilemez")
    private String emailAddress;
    @Size(min = 8, max = 30)
    @NotNull(message = "Şifre null olamaz")
    @NotBlank(message = "Şifre boş geçilemez")
    private String password;
    @NotNull(message = "telefon numarası null olamaz")
    @NotBlank(message = "Çalışan telefon numarası boş geçilemez")
    @Size(min = 10, max = 10, message = "Telefon numarası 10 hane olmalıdır.")
    @Pattern(regexp = "^[0-9]+$", message = "Telefon numarası sadece sayılardan oluşmalıdır.")
    private String phoneNumber;
    @NotNull(message = "maaş null olamaz")
    @Min(0)
    private Double salary;
    private int userImageEntityId;
}

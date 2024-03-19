package src.controller.user.admin.requests;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateAdminRequest {
    @NotBlank(message = "isim boş geçilemez")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    @Size(min = 2, max = 20)
    private String name;
    @NotBlank(message = "Soyisim boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    private String surname;
    @Email//-> Email alırken @gmail @hotmail gibi kullanımları denetler.
            (message = "Admin mail adresi null olamaz")
    @NotBlank(message = "Admin mail adresi boş geçilemez")
    private String emailAddress;
    @Size(min = 8, max = 30)
    @NotBlank(message = "Şifre boş geçilemez")
    private String password;

    @NotBlank(message = "Çalışan telefon numarası boş geçilemez")
    @Size(min = 10, max = 10, message = "Telefon numarası 10 hane olmalıdır.")
    @Pattern(regexp = "^[0-9]+$", message = "Telefon numarası sadece sayılardan oluşmalıdır.")
    private String phoneNumber;
    @NotNull(message = "maaş null olamaz")
    @Min(0)
    private Double salary;
    private int userImageEntityId;

    @Override
    public String toString() {
        return "CreateAdminRequest{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary=" + salary +
                ", userImageEntityId=" + userImageEntityId +
                '}';
    }
}

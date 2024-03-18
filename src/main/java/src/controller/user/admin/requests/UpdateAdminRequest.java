package src.controller.user.admin.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.service.user.model.DefaultUserStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateAdminRequest {

    @NotNull(message = "maaş null olamaz")
    @Min(0)
    Double salary;
    @NotNull(message = "id null olamaz")
    private int id;
    @NotBlank(message = "isim boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    private String name;
    @NotBlank(message = "Soyisim boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    private String surname;
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
    private DefaultUserStatus status;
    private int userImageEntityId;

    @Override
    public String toString() {
        return "UpdateAdminRequest{" +
                "salary=" + salary +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", userImageEntityId=" + userImageEntityId +
                '}';
    }
}

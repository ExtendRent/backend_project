package src.controller.user.customer.requests;

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
public class UpdateCustomerRequest {

    @NotNull(message = "Müşteri id null olamaz")
    int id;
    @NotNull
    int drivingLicenseTypeEntityId;
    @NotBlank(message = "Müşteri adı boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    private String name;
    @NotBlank(message = "Müşteri soyadı boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    private String surname;

    @NotBlank(message = "Müşteri mail adresi boş geçilemez")
    @Email//-> Email alırken @gmail @hotmail gibi kullanımları denetler.
    private String emailAddress;

    @NotBlank(message = "Müşteri şifresi boş geçilemez")
    @Size(min = 8, max = 30)
    private String password;

    @NotBlank(message = "Çalışan telefon numarası boş geçilemez")
    @Size(min = 10, max = 10, message = "Telefon numarası 10 hane olmalıdır.")
    @Pattern(regexp = "^[0-9]+$", message = "Telefon numarası sadece sayılardan oluşmalıdır.")
    private String phoneNumber;
    @NotBlank(message = "Ehliyet numarası boş geçilemez")
    @Size(max = 6, message = "Ehliyet seri numarası 6 haneli olmalıdır.")
    private String drivingLicenseNumber;
    private DefaultUserStatus status;
    private int userImageEntityId;

    @Override
    public String toString() {
        return "UpdateCustomerRequest{" +
                "id=" + id +
                ", drivingLicenseTypeEntityId=" + drivingLicenseTypeEntityId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' +
                ", status=" + status +
                ", userImageEntityId=" + userImageEntityId +
                '}';
    }
}

package src.controller.user.customer.requests;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateCustomerRequest {

    @NotBlank(message = "Müşteri adı boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    String name;

    @NotBlank(message = "Müşteri soyadı boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    String surname;
    @NotBlank(message = "Müşteri mail adresi boş geçilemez")
    @Email//-> Email alırken @gmail @hotmail gibi kullanımları denetler.
    String emailAddress;
    @NotBlank(message = "Müşteri şifresi boş geçilemez")
    @Size(min = 8, max = 30)
    String password;
    @NotNull(message = "Müşteri telefon numarası null olamaz")
    @NotBlank(message = "Çalışan telefon numarası boş geçilemez")
    @Size(min = 10, max = 10, message = "Telefon numarası 10 hane olmalıdır.")
    @Pattern(regexp = "^[0-9]+$", message = "Telefon numarası sadece sayılardan oluşmalıdır.")
    String phoneNumber;
    @Size(max = 6, message = "Ehliyet seri numarası 6 haneli olmalıdır.")
    String drivingLicenseNumber;
    @NotNull
    int drivingLicenseTypeEntityId;
    @NotNull
    private int userImageEntityId;

    @Override
    public String toString() {
        return "CreateCustomerRequest{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' +
                ", drivingLicenseTypeEntityId=" + drivingLicenseTypeEntityId +
                ", userImageEntityId=" + userImageEntityId +
                '}';
    }
}

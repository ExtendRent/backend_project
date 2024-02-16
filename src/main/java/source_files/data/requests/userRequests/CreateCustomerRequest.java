package source_files.data.requests.userRequests;

import jakarta.validation.constraints.*;
import lombok.*;
import source_files.data.enums.types.userTypes.UserRole;
import source_files.data.requests.BaseRequest;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateCustomerRequest extends BaseRequest {

    private static UserRole authority = UserRole.CUSTOMER;
    @NotNull(message = "Müşteri adı null olamaz")
    @NotBlank(message = "Müşteri adı boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    String name;
    @NotNull(message = "Müşteri soyadı null olamaz")
    @NotBlank(message = "Müşteri soyadı boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    String surname;
    @NotNull(message = "Müşteri mail adresi null olamaz")
    @NotBlank(message = "Müşteri mail adresi boş geçilemez")
    @Email//-> Email alırken @gmail @hotmail gibi kullanımları denetler.
    String emailAddress;
    @NotNull(message = "Müşteri şifresi null olamaz")
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
    private int userImageEntityId;
}

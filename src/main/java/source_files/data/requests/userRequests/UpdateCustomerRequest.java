package source_files.data.requests.userRequests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.Status.UserStatus;
import source_files.data.requests.BaseRequest;
import source_files.data.types.itemTypes.DrivingLicenseType;
import source_files.data.types.userTypes.UserRole;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCustomerRequest implements BaseRequest {

    @NotNull(message = "Müşteri id null olamaz")
    @NotBlank(message = "Müşteri id boş.QRect.")
    int id;

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
    @NotBlank(message = "Müşteri telefon numarası boş geçilemez")
    @Size(min = 10, max = 10, message = "Telefon numarası 10 hane olmalıdır.")
    @Pattern(regexp = "^[0-9]+$", message = "Telefon numarası sadece sayılardan oluşmalıdır.")
    String phoneNumber;

    @NotBlank(message = "Ehliyet numarası boş geçilemez")
    @Size(max = 6, message = "Ehliyet seri numarası 6 haneli olmalıdır.")
    String drivingLicenseNumber;

    @NotBlank(message = "Ehliyet tipi boş geçilemez.")
    @Size(min = 1, max = 16, message = "Sürücü belgesi türü listesi 1 ile 16 arasında olmalıdır.")
    List<DrivingLicenseType> drivingLicenseTypes;

    String imagePath;
    UserStatus status = UserStatus.PENDING_VERIFYING;
    private UserRole authority = UserRole.CUSTOMER;
}

package source_files.controllers.security.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import source_files.controllers.user.requests.CreateAdminRequest;
import source_files.controllers.user.requests.CreateCustomerRequest;
import source_files.controllers.user.requests.CreateEmployeeRequest;
import source_files.data.enums.types.userTypes.UserRole;

@Data
@AllArgsConstructor
@Builder
public class SignUpReqeust {

    @Size(min = 2, max = 20)
    String name;

    @NotBlank(message = "Soyisim boş geçilemez")
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    String surname;

    @Email//-> Email alırken @gmail @hotmail gibi kullanımları denetler.
    @NotBlank(message = "Mail adresi boş geçilemez")
    String emailAddress;

    @Size(min = 8, max = 30)
    @NotBlank(message = "Şifre boş geçilemez")
    String password;

    @NotBlank(message = "telefon numarası boş geçilemez")
    @Size(min = 10, max = 10, message = "Telefon numarası 10 hane olmalıdır.")
    @Pattern(regexp = "^[0-9]+$", message = "Telefon numarası sadece sayılardan oluşmalıdır.")
    String phoneNumber;

    Double salary;

    String drivingLicenseNumber;

    int drivingLicenseTypeEntityId;

    @NotNull
    UserRole authority;

    @NotNull
    int userImageEntityId;

    public CreateCustomerRequest forCustomer() {
        return CreateCustomerRequest.builder()
                .name(name)
                .surname(surname)
                .emailAddress(emailAddress)
                .password(password)
                .phoneNumber(phoneNumber)
                .drivingLicenseNumber(drivingLicenseNumber)
                .drivingLicenseTypeEntityId(drivingLicenseTypeEntityId)
                .userImageEntityId(userImageEntityId)
                .build();
    }

    public CreateAdminRequest forAdmin() {
        return CreateAdminRequest.builder()
                .name(name)
                .surname(surname)
                .emailAddress(emailAddress)
                .password(password)
                .phoneNumber(phoneNumber)
                .salary(salary)
                .userImageEntityId(userImageEntityId)
                .build();
    }

    public CreateEmployeeRequest forEmployee() {
        return CreateEmployeeRequest.builder()
                .name(name)
                .surname(surname)
                .emailAddress(emailAddress)
                .password(password)
                .phoneNumber(phoneNumber)
                .salary(salary)
                .userImageEntityId(userImageEntityId)
                .build();
    }
}

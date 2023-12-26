package source_files.data.requests.userRequests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddEmployeeRequest {
    @NotBlank(message = "Çalışan adı boş geçilemez")
    @Size(min = 2, max = 20)
    String name;

    @NotBlank(message = "Çalışan soyadı boş geçilemez")
    @Size(min = 2, max = 20)
    String surname;

    @Email//-> Email alırken @gmail @hotmail gibi kullanımları denetler.
    @NotBlank(message = "Çalışan mail adresi boş geçilemez")
    String emailAddress;

    @Size(min = 8, max = 30)
    @NotBlank(message = "Çalışan şifresi boş geçilemez")
    String password;

    @NotBlank(message = "Çalışan telefon numarası boş geçilemez")
    @Size(min = 10, max = 10, message = "Telefon numarası 10 hane olmalıdır.")
    String phoneNumber;
    @Min(0)
    Double salary;
}
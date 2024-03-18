package src.repository.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardInformation {

    @NotNull(message = "kart numarası null olamaz")
    @NotBlank(message = "kart numarası boş geçilemez")
    @Size(min = 16, max = 16, message = "kart numarası 16 karakter olmalıdır.")
    @Pattern(regexp = "^[0-9]+$", message = "kart numarası sadece rakamlardan olusmalır.")
    private String cardNumber;

    @NotNull(message = "isim null olamaz")
    @NotBlank(message = "isim boş geçilemez")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    @Size(min = 2, max = 20)
    private String cardOwnerName;

    @NotNull(message = "soyisim null olamaz")
    @NotBlank(message = "soyisim boş geçilemez")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = "isim/soyisim sadece harflerden oluşmalıdır.")
    @Size(min = 2, max = 20)
    private String cardOwnerSurname;

    @NotNull(message = "son kullanma tarihi null olamaz")
    @NotBlank(message = "son kullanma tarihi boş geçilemez")
    private LocalDate expirationDate;

    @NotNull(message = "cvc null olamaz")
    @NotBlank(message = "cvc boş geçilemez")
    @Size(min = 3, max = 3, message = "cvc 3 karakter olmalıdır.")
    @Pattern(regexp = "^[0-9]+$", message = "cvc sadece rakamlardan olusmalır.")
    private String cvc;
}

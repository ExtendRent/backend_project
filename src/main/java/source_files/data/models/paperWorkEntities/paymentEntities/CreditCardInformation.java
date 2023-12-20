package source_files.data.models.paperWorkEntities.paymentEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.YearMonth;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardInformation {
    //TODO validasyonlar yazılacak
    private String cardNumber;
    private String cardOwnerName;
    private String cardOwnerSurname;
    private LocalDate expirationDate;
    private String cvc;
}

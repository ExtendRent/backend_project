package source_files.data.models.paperWorkEntities.paymentEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.YearMonth;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardInformation {

    private String cardNumber;
    private String cardOwnerName;
    private String cardOwnerSurname;
    private YearMonth expirationDate;
    private String cvc;
}

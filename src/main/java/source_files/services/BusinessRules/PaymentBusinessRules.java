package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class PaymentBusinessRules {

    public void checkCreditCard(CreditCardInformation creditCardInformation) {
        this.checkCreditCardNumber(creditCardInformation.getCardNumber());

    }

    //---------------CHECKING METHODS--------------------------------

    public void checkCreditCardNumber(String cardNumber) {
    }

    public void checkOwnerOfCreditCardFullName(String name, String surname) {
    }

    //-----------------------------------------------------------------
    public String creditCardOwnerUniqueName(String name) {
        return null;
    }

    public String creditCardOwnerUniqueSurname(String surname) {
        return null;
    }

    public void checkCreditCardExpirationDate(LocalDate expirationDate) {
        if (expirationDate.isAfter(LocalDate.now())) {
            throw new IllegalStateException("Kart son kullanım tarihiniz geçmiş bir tarihtir.");
        }
    }
}

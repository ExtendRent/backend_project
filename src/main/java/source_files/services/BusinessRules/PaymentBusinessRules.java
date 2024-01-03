package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;
import source_files.exception.DataNotFoundException;

import java.time.LocalDate;
import java.util.List;

import static source_files.exception.NotFoundExceptionType.PAYMENT_DETAILS_LIST_NOT_FOUND;

@AllArgsConstructor
@Service
public class PaymentBusinessRules implements BaseBusinessRulesService {


    //--------------------- AUTO FIX METHODS ---------------------
    public CreditCardInformation fixCreditCardInformation(CreditCardInformation creditCardInformation) {
        creditCardInformation.setCardOwnerName(this.fixCreditCardOwnerName(creditCardInformation.getCardOwnerName()));
        creditCardInformation.setCardOwnerSurname(this.fixCreditCardOwnerSurname(creditCardInformation.getCardOwnerSurname()));
        return creditCardInformation;
    }

    //---------------AUTO CHECKING METHODS--------------------------------
    public CreditCardInformation checkCreditCard(CreditCardInformation creditCardInformation) {
        this.checkCreditCardNumber(creditCardInformation.getCardNumber());
        this.checkOwnerOfCreditCardFullName(creditCardInformation.getCardOwnerName(), creditCardInformation.getCardOwnerSurname());
        return creditCardInformation;
    }


    //----------------------------METHODS--------------------------------

    public String fixCreditCardOwnerName(String name) {
        return name.replace(" ", "").toUpperCase();
    }

    public String fixCreditCardOwnerSurname(String surname) {
        return surname.replace(" ", "").toUpperCase();
    }


    private void checkCreditCardNumber(String cardNumber) {
    }

    private void checkOwnerOfCreditCardFullName(String name, String surname) {
    }

    private void checkCreditCardExpirationDate(LocalDate expirationDate) {
        if (expirationDate.isAfter(LocalDate.now())) {
            throw new IllegalStateException("Kart son kullanım tarihiniz geçmiş bir tarihtir.");
        }
    }


    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(PAYMENT_DETAILS_LIST_NOT_FOUND, "Aradığınız kriterlere uygun ödeme kaydı bulunamadı");
        }
        return list;
    }

    @Override
    public String fixName(String name) {
        return null;
    }
}

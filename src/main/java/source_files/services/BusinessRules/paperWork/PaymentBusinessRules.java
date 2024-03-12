package source_files.services.BusinessRules.paperWork;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;
import source_files.exception.DataNotFoundException;
import source_files.exception.PaymentException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseBusinessRulesService;
import source_files.services.systemServices.SysPaymentDetailsServiceImpl;

import java.time.LocalDate;
import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.PAYMENT_DETAILS_LIST_NOT_FOUND;
import static source_files.exception.exceptionTypes.PaymentExceptionType.EXPIRY_DATE_HAS_EXPIRED;

@RequiredArgsConstructor
@Service
public class PaymentBusinessRules implements BaseBusinessRulesService {

    private final SysPaymentDetailsServiceImpl sysPaymentDetailsServiceImpl;

    //--------------------- AUTO FIX METHODS ---------------------
    public CreditCardInformation fixCreditCardInformation(CreditCardInformation creditCardInformation) {
        creditCardInformation.setCardOwnerName(this.fixCreditCardOwnerName(creditCardInformation.getCardOwnerName()));
        creditCardInformation.setCardOwnerSurname(this.fixCreditCardOwnerSurname(creditCardInformation.getCardOwnerSurname()));
        return creditCardInformation;
    }

    //---------------AUTO CHECKING METHODS--------------------------------
    public void checkCreditCard(CreditCardInformation creditCardInformation) {
        this.checkCreditCardNumber(creditCardInformation.getCardNumber());
        this.checkOwnerOfCreditCardFullName(creditCardInformation.getCardOwnerName(), creditCardInformation.getCardOwnerSurname());
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
            throw new PaymentException(EXPIRY_DATE_HAS_EXPIRED);
        }
    }


    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(PAYMENT_DETAILS_LIST_NOT_FOUND);
        }
        
    }

    @Override
    public String fixName(String name) {
        return null;
    }
}

package source_files.services.BusinessRules;

import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;

public class PaymentBusinessRules {

    public void checkCreditCard(CreditCardInformation creditCardInformation){
        this.checkCreditCardNumber(creditCardInformation.getCardNumber());

    }

    //---------------CHECKING METHODS--------------------------------

    public void checkCreditCardNumber(String cardNumber){

    }

    public void checkOwnerOfCreditCardFullName(String name,String surname){

    }

    //-----------------------------------------------------------------
    public String creditCardOwnerUniqueName(String name){
        return null;
    }
    public String creditCardOwnerUniqueSurname(String surname){
        return null;
    }


}

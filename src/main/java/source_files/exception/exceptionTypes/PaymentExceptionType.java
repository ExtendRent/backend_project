package source_files.exception.exceptionTypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentExceptionType {

    PAYMENT_REJECTED(4000, "Payment Rejected From Your Bank !"),
    CREDIT_CARD_INFORMATION_NOT_VERIFIED(4001, "Your Credit Card Informations Not Verified !");
    //------------------------------------------------------------------
    private final Integer errorCode;
    private final String message;

}

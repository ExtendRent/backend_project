package source_files.services.paperWorkServices.payment;

import source_files.data.types.PaymentType;

import java.util.HashMap;

import static source_files.data.types.PaymentType.BANK_MONEY_TRANSFER;
import static source_files.data.types.PaymentType.CREDIT_CARD;

public class PaymentMain {

    //Ödeme tipini key, Ödeme methodunu value olarak kayıt ediyoruz.
    HashMap<PaymentType, PaymentMethods> paymentMethodsMap = new HashMap<>();

    public PaymentMain() {
        paymentMethodsMap.put(BANK_MONEY_TRANSFER, new PayWithBankTransfer());
        paymentMethodsMap.put(CREDIT_CARD, new PayWithCreditCard());
    }

    public String main(PaymentType paymentType, String kartBilgileri) { // Müşteriden alınan bilgiler buraya düşer.
        return paymentMethodsMap.get(paymentType).pay(kartBilgileri);
    }

}

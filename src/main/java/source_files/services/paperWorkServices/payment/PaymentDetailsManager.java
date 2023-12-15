package source_files.services.paperWorkServices.payment;

import source_files.data.types.PaymentType;

public class PaymentDetailsManager {

    private final PaymentMain paymentMain;

    public PaymentDetailsManager(PaymentMain paymentMain) {
        this.paymentMain = paymentMain;
    }

    String pay(PaymentType paymentType, String kartBilgileri){
        return paymentMain.main(paymentType,kartBilgileri);
    }


}

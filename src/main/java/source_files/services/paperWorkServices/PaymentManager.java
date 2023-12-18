package source_files.services.paperWorkServices;

import org.springframework.stereotype.Service;
import source_files.data.types.PaymentType;
import source_files.services.paperWorkServices.payment.PaymentMain;

@Service
//@AllArgsConstructor
public class PaymentManager {

    private final PaymentMain paymentMain;


    public PaymentManager(PaymentMain paymentMain) {
        this.paymentMain = paymentMain;
    }

    String pay(PaymentType paymentType, String kartBilgileri) {
        return paymentMain.main(paymentType, kartBilgileri);
    }


}

package source_files.services.paperWorkServices.payment;

public class PayWithCreditCard implements PaymentMethods {
    @Override
    public String pay(String kartBilgileri) {

        //ÖDEME İŞLEMLERİ...
        return "kredi kartı ile ödendi";
    }
}

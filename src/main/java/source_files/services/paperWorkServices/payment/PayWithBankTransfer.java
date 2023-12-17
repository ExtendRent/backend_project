package source_files.services.paperWorkServices.payment;

public class PayWithBankTransfer implements PaymentMethods {

    @Override
    public String pay(String kartBilgileri) {
        //ÖDEME İŞLEMLERİ...
        return "Havale ile ödendi";
    }
}

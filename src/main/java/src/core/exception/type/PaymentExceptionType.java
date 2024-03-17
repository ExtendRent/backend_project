package src.core.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentExceptionType {


    PAYMENT_REJECTED(4000, "Ödeme Bankanız tarafından Reddedildi"),
    CREDIT_CARD_INFORMATION_NOT_VERIFIED(4001, "Kredi Kartı Bilgileriniz Doğrulanmadı"),
    EXPIRY_DATE_HAS_EXPIRED(4002, "Kredi Kartı Bilgileriniz Doğrulanamadı"),
    PAYMENT_TYPE_IS_NOT_ACTIVE(4003, "Ödeme Tipi Aktif Değil"),
    NOT_SUPPORTED_PAYMENT_TYPE(4004, "Desteklenmeyen Ödeme Türü"),
    PAYMENT_ERROR(4005, "Ödeme Hatası");
    //------------------------------------------------------------------
    private final Integer errorCode;
    private final String message;

}

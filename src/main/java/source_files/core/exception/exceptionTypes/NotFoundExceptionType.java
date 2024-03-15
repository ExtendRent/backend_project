package source_files.core.exception.exceptionTypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotFoundExceptionType {

    GENERIC_EXCEPTION(1, "Bilinmeyen hata"),

    //DATA NOT FOUND Types for Users:
    USER_DATA_NOT_FOUND(1001, "Kullanıcı bulunamadı"),
    USER_LIST_NOT_FOUND(1002, "Aradığınız kriterlerde kullanıcı bulunamadı"),
    CUSTOMER_DATA_NOT_FOUND(1003, "Müşteri bulunamadı"),
    CUSTOMER_LIST_NOT_FOUND(1004, "Aradığınız kriterlerde müşteri bulunamadı"),
    ADMIN_DATA_NOT_FOUND(1005, "Admin bulunamadı"),
    ADMIN_LIST_NOT_FOUND(1006, "Aradığınız kriterlerde admin bulunamadı"),
    EMPLOYEE_DATA_NOT_FOUND(1007, "Çalışan bulunamadı"),
    EMPLOYEE_LIST_NOT_FOUND(1008, "Aradığınız kriterlerde çalışan bulunamadı"),

    //------------------------------------------------------------------

    //DATA NOT FOUND Types for Items:
    BRAND_DATA_NOT_FOUND(1009, "Marka bulunamadı"),
    BRAND_LIST_NOT_FOUND(1010, "Aradığınız kriterlerde marka bulunamadı"),

    COLOR_DATA_NOT_FOUND(1011, "Renk bulunamadı"),
    COLOR_LIST_NOT_FOUND(1012, "Aradığınız kriterlerde renk bulunamadı"),
    BODY_TYPE_DATA_NOT_FOUND(1013, "Kasa tipi bulunamadı"),
    BODY_TYPE_LIST_NOT_FOUND(1014, "Aradığınız kriterlerde kasa tipi bulunamadı"),

    MODEL_DATA_NOT_FOUND(1015, "Model bulunamadı"),
    MODEL_LIST_NOT_FOUND(1016, "Aradığınız kriterlerde model bulunamadı"),

    CAR_DATA_NOT_FOUND(1017, "Araç bulunamadı"),
    CAR_LIST_NOT_FOUND(1018, "Aradığınız kriterlerde araç bulunamadı"),

    RENTAL_DATA_NOT_FOUND(1019, "Kiralama kaydı bulunamadı"),
    RENTAL_LIST_NOT_FOUND(1020, "Aradığınız kriterlerde kiralama kaydı bulunamadı"),

    PAYMENT_DETAILS_DATA_NOT_FOUND(1021, "Ödeme detayı bulunamadı"),
    PAYMENT_DETAILS_LIST_NOT_FOUND(1022, "Aradığınız kriterlerde ödeme detayı bulunamadı"),

    PAYMENT_TYPE_NOT_FOUND(1023, "Ödeme şekli bulunamadı"),
    PAYMENT_TYPE_LIST_NOT_FOUND(1024, "Aradığınız kriterlerde ödeme şekli bulunamadı"),
    DISCOUNT_CODE_NOT_FOUND(1025, "İndirim kodu bulunamadı"),
    DISCOUNT_CODE_LIST_NOT_FOUND(1026, "Aradığınız kriterlerde indirim kodu bulunamadı"),

    FUEL_TYPE_NOT_FOUND(1027, "Yakıt tipi bulunamadı"),
    FUEL_TYPE_LIST_NOT_FOUND(1028, "Aradığınız kriterlerde yakıt tipi bulunamadı"),
    SHIFT_TYPE_NOT_FOUND(1029, "Vites tipi bulunamadı"),
    SHIFT_TYPE_LIST_NOT_FOUND(1030, "Aradığınız kriterlerde vites tipi bulunamadı"),

    VEHICLE_STATUS_NOT_FOUND(1031, "Araç durumu bulunamadı"),

    VEHICLE_STATUS_LIST_NOT_FOUND(1032, "Aradığınız kriterlerde araç durumu bulunamadı"),

    DRIVING_LICENSE_TYPE_NOT_FOUND(1033, "Ehliyet tipi bulunamadı"),
    DRIVING_LICENSE_TYPE_LIST_NOT_FOUND(1034, "Aradığınız kriterlerde ehliyet tipi bulunamadı"),

    CAR_SEGMENT_NOT_FOUND(1035, "Araç segmenti bulunamadı"),
    CAR_SEGMENT_LIST_NOT_FOUND(1036, "Aradığınız kriterlerde araç segmenti bulunamadı"),

    IMAGE_NOT_FOUND(1037, "Fotoğraf bulunamadı"),
    IMAGE_LIST_NOT_FOUND(1038, "Aradığınız kriterlerde fotoğraf bulunamadı"),

    USER_ROLE_NOT_FOUND(1039, "Kullanıcı rolü bulunamadı"),

    RENTAL_STATUS_NOT_FOUND(1040, "Kiralama durumu bulunamadı"),
    RENTAL_STATUS_LIST_NOT_FOUND(1041, "Aradığınız kriterlerde kiralama durumu bulunamadı"),
    EMAIL_ADDRESS_NOT_FOUND(1042, "Bu email adresine ait kullanıcı bulunamadı");
    //------------------------------------------------------------------
    private final Integer errorCode;
    private final String message;
}

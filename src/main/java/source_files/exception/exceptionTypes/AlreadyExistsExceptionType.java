package source_files.exception.exceptionTypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AlreadyExistsExceptionType {

    USER_ALREADY_EXISTS(2001, "Kullanıcı zaten mevcut"),

    CUSTOMER_ALREADY_EXISTS(2002, "Müşteri zaten mevcut"),

    ADMIN_ALREADY_EXISTS(2003, "Admin zaten mevcut"),

    EMPLOYEE_ALREADY_EXISTS(2004, "Çalışan zaten mevcut"),

    //------------------------------------------------------------------

    //ALREADY EXISTS Types for Items:
    BRAND_ALREADY_EXISTS(2005, "Marka zaten mevcut"),

    COLOR_ALREADY_EXISTS(2006, "Renk zaten mevcut"),

    BODY_TYPE_ALREADY_EXISTS(2007, "Kasa tipi zaten mevcut"),

    MODEL_ALREADY_EXISTS(2008, "Model zaten mevcut"),

    CAR_ALREADY_EXISTS(2009, "Araç zaten mevcut"),

    RENTAL_ALREADY_EXISTS(2010, "Kiralama zaten mevcut"),

    PAYMENT_DETAILS_ALREADY_EXISTS(2011, "Ödeme detayı zaten mevcut"),

    DISCOUNT_ALREADY_EXISTS(2012, "İndirim zaten mevcut"),

    LICENSE_PLATE_ALREADY_EXISTS(2013, "Plaka zaten mevcut"),
    PHONE_NUMBER_ALREADY_EXISTS(2014, "Bu telefon numarası zaten mevcut"),
    DRIVING_LICENSE_NUMBER_ALREADY_EXISTS(2015, "geçerli bir ehliyet numarası giriniz"),
    DRIVING_LICENSE_TYPE_ALREADY_EXISTS(2016, "Ehliyet tipi zaten mevcut"),
    SHIFT_TYPE_ALREADY_EXISTS(2017, "Vites tipi zaten mevcut"),
    VEHICLE_STATUS_ALREADY_EXISTS(2018, "Araç durumu zaten mevcut"),

    CAR_SEGMENT_ALREADY_EXSISTS(2019, "Araç segmenti zaten mevcut"),

    PAYMENT_TYPE_ALREADY_EXISTS(2020, "Ödeme tipi zaten mevcut"),
    EMAIL_ADDRESS_ALREADY_EXISTS(2021, "Bu email zaten mevcut");

    //------------------------------------------------------------------
    private final Integer errorCode;
    private final String message;
}

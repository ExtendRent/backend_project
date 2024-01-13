package source_files.exception.exceptionTypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotFoundExceptionType {

    GENERIC_EXCEPTION(1, "Unkown Error!"),

    //DATA NOT FOUND Types for Users:
    USER_DATA_NOT_FOUND(1001, "User Not Found!"),
    USER_LIST_NOT_FOUND(1002, "User List is Empty!"),

    CUSTOMER_DATA_NOT_FOUND(1003, "Customer Not Found!"),
    CUSTOMER_LIST_NOT_FOUND(1004, "Customer List is Empty!"),

    ADMIN_DATA_NOT_FOUND(1005, "Admin Not Found!"),
    ADMIN_LIST_NOT_FOUND(1006, "Admin List is Empty!"),

    EMPLOYEE_DATA_NOT_FOUND(1007, "Employee Not Found!"),
    EMPLOYEE_LIST_NOT_FOUND(1008, "Employee List is Empty!"),

    //------------------------------------------------------------------

    //DATA NOT FOUND Types for Items:
    BRAND_DATA_NOT_FOUND(1009, "Brand Not Found!"),
    BRAND_LIST_NOT_FOUND(1010, "Brand List is Empty!"),

    COLOR_DATA_NOT_FOUND(1011, "Color Not Found!"),
    COLOR_LIST_NOT_FOUND(1012, "Color List is Empty!"),

    BODY_TYPE_DATA_NOT_FOUND(1013, "Body Type Not Found!"),
    BODY_TYPE_LIST_NOT_FOUND(1014, "Body Type List is Empty!"),

    MODEL_DATA_NOT_FOUND(1015, "Model Not Found!"),
    MODEL_LIST_NOT_FOUND(1016, "Model List is Empty!"),

    CAR_DATA_NOT_FOUND(1017, "Car Not Found!"),
    CAR_LIST_NOT_FOUND(1018, "Car List is Empty!"),

    RENTAL_DATA_NOT_FOUND(1019, "Rental Not Found!"),
    RENTAL_LIST_NOT_FOUND(1020, "Rental List is Empty!"),

    PAYMENT_DETAILS_DATA_NOT_FOUND(1021, "Payment Details Not found!"),
    PAYMENT_DETAILS_LIST_NOT_FOUND(1022, "Payment Details List is Empty!"),

    PAYMENT_TYPE_NOT_FOUND(1023, "Payment Type Not Found"),
    PAYMENT_TYPE_LIST_NOT_FOUND(1024, "Payment Type List is EMpty"),
    DISCOUNT_CODE_NOT_FOUND(1025, "Discount Code Not Found!"),
    DISCOUNT_CODE_LIST_NOT_FOUND(1026, "Discount Code List is Empty!");
    //------------------------------------------------------------------
    private final Integer errorCode;
    private final String message;


}

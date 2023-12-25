package source_files.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AlreadyExistsExceptionType {

    USER_ALREADY_EXISTS(2001, "User Already Exists!"),

    CUSTOMER_ALREADY_EXISTS(2002, "Customer Already Exists!"),

    ADMIN_ALREADY_EXISTS(2003, "Admin Already Exists!"),

    EMPLOYEE_ALREADY_EXISTS(2004, "Employee Already Exists!"),

    //------------------------------------------------------------------

    //ALREADY EXISTS Types for Items:
    BRAND_ALREADY_EXISTS(2005, "Brand Already Exists!"),

    COLOR_ALREADY_EXISTS(2006, "Color Already Exists!"),

    BODY_TYPE_ALREADY_EXISTS(2007, "Body Already Exists!"),

    MODEL_ALREADY_EXISTS(2008, "Model Already Exists!"),

    CAR_ALREADY_EXISTS(2009, "Car Already Exists!"),

    RENTAL_ALREADY_EXISTS(2010, "Rental Already Exists!"),

    PAYMENT_DETAILS_ALREADY_EXISTS(2011, "Payment Details Already Exists!"),

    DISCOUNT_ALREADY_EXISTS(2012, "Discount Code Already Exists"),

    LICENSE_PLATE_ALREADY_EXISTS(2013, "License Plate Already Exists"),

    PHONE_NUMBER_ALREADY_EXISTS(2014,"Phone Number Already Exists"),

    DRIVING_LICENSE_NUMBER_ALREADY_EXISTS(2015,"Driving License Number Already Exists"),

    EMAIL_ADDRESS_ALREADY_EXISTS(2016,"Email Address Already Exists"),
    NAME_ALREADY_EXISTS(2017,"Name Already Exists");

    //------------------------------------------------------------------
    private final Integer errorCode;
    private final String message;
}

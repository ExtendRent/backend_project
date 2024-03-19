package src.controller.payment.type;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String UPDATING_PAYMENT_TYPE = ANSI_BLUE + "Updating payment type: " + ANSI_RESET + "{}";
    public static final String PAYMENT_TYPE_UPDATED = ANSI_GREEN + "Payment type updated: " + ANSI_RESET + "{}";
    public static final String GETTING_PAYMENT_TYPE_DETAILS = ANSI_BLUE + "Getting payment type details for id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_PAYMENT_TYPE_DETAILS = ANSI_GREEN + "Retrieved payment type details: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_ALL_PAYMENT_TYPES = ANSI_BLUE + "Retrieving all payment types" + ANSI_RESET;
    public static final String RETRIEVED_ALL_PAYMENT_TYPES = ANSI_GREEN + "Retrieved all payment types. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVING_PAYMENT_TYPES_BY_ACTIVE_STATE = ANSI_BLUE + "Retrieving payment types by active state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_PAYMENT_TYPES_BY_ACTIVE_STATE = ANSI_GREEN + "Retrieved payment types by active state. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
}

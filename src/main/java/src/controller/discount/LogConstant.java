package src.controller.discount;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String CREATING_NEW_DISCOUNT_CODE = ANSI_BLUE + "Creating a new discount code: " + ANSI_RESET + "{}";
    public static final String DISCOUNT_CODE_CREATED_SUCCESSFULLY = ANSI_GREEN + "Discount code created successfully." + ANSI_RESET;
    public static final String UPDATING_DISCOUNT_CODE = ANSI_BLUE + "Updating discount code with ID: " + ANSI_RESET + "{}";
    public static final String DISCOUNT_CODE_UPDATED_SUCCESSFULLY = ANSI_GREEN + "Discount code updated successfully with ID: " + ANSI_RESET + "{}";
    public static final String GETTING_DISCOUNT_CODE_DETAILS = ANSI_BLUE + "Getting discount code details for ID: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DISCOUNT_CODE_DETAILS_RETRIEVED_SUCCESSFULLY = ANSI_GREEN + "Discount code details retrieved successfully for ID: " + ANSI_RESET + "{}";
    public static final String GETTING_DISCOUNT_CODE_BY_CODE = ANSI_BLUE + "Getting discount code details for code: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DISCOUNT_CODE_DETAILS_RETRIEVED_SUCCESSFULLY_BY_CODE = ANSI_GREEN + "Discount code details retrieved successfully for code: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_ALL_DISCOUNT_CODES = ANSI_BLUE + "Retrieving all discount codes" + ANSI_RESET;
    public static final String ALL_DISCOUNT_CODES_RETRIEVED_SUCCESSFULLY = ANSI_GREEN + "All discount codes retrieved successfully." + ANSI_RESET;
    public static final String RETRIEVING_DISCOUNT_CODES_BY_DELETED_STATE = ANSI_BLUE + "Retrieving discount codes by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DISCOUNT_CODES_RETRIEVED_SUCCESSFULLY_BY_DELETED_STATE = ANSI_GREEN + "Discount codes retrieved successfully by deleted state: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_DISCOUNT_CODES_BY_ACTIVE_STATE = ANSI_BLUE + "Retrieving discount codes by active state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DISCOUNT_CODES_RETRIEVED_SUCCESSFULLY_BY_ACTIVE_STATE = ANSI_GREEN + "Discount codes retrieved successfully by active state: " + ANSI_RESET + "{}";
    public static final String DELETING_DISCOUNT_CODE = ANSI_BLUE + "Deleting discount code with ID: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET + ", hard delete: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DISCOUNT_CODE_DELETED_SUCCESSFULLY = ANSI_GREEN + "Discount code deleted successfully with ID: " + ANSI_RESET + "{}";
}

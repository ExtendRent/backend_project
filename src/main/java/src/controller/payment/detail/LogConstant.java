package src.controller.payment.detail;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {
    // Log Messages
    public static final String UPDATING_PAYMENT_DETAILS = ANSI_BLUE + "Updating payment details: " + ANSI_RESET + "{}";
    public static final String PAYMENT_DETAILS_UPDATED = ANSI_GREEN + "Payment details updated: " + ANSI_RESET + "{}";
    public static final String GETTING_PAYMENT_DETAILS = ANSI_BLUE + "Getting payment details for id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_PAYMENT_DETAILS = ANSI_GREEN + "Retrieved payment details: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_ALL_PAYMENT_DETAILS = ANSI_BLUE + "Retrieving all payment details" + ANSI_RESET;
    public static final String RETRIEVED_ALL_PAYMENT_DETAILS = ANSI_GREEN + "Retrieved all payment details. " + ANSI_RESET + "Quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_MONTHLY_INCOME = ANSI_BLUE + "Getting monthly income for period: " + ANSI_RESET + "{} - {}";
    public static final String MONTHLY_INCOME_RETRIEVED = ANSI_GREEN + "Monthly income retrieved: " + ANSI_RESET + "{}";
    public static final String GETTING_YEARLY_INCOME = ANSI_BLUE + "Getting yearly income for year: " + ANSI_RESET + "{}";
    public static final String YEARLY_INCOME_RETRIEVED = ANSI_GREEN + "Yearly income retrieved: " + ANSI_RESET + "{}";
    public static final String GETTING_TOTAL_INCOME = ANSI_BLUE + "Getting total income" + ANSI_RESET;
    public static final String TOTAL_INCOME_RETRIEVED = ANSI_GREEN + "Total income retrieved: " + ANSI_RESET + "{}";
    public static final String FILTERING_PAYMENT_DETAILS = ANSI_BLUE + "Filtering payment details with minAmount: {}, maxAmount: {}, minDate: {}, maxDate: {}, isDeleted: {}" + ANSI_RESET;
    public static final String PAYMENT_DETAILS_FILTERED = ANSI_GREEN + "Payment details filtered. " + ANSI_RESET + "Quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
}

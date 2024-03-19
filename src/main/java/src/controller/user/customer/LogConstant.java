package src.controller.user.customer;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String CREATING_NEW_CUSTOMER = ANSI_BLUE + "Creating a new customer: " + ANSI_RESET + "{}";
    public static final String CUSTOMER_SUCCESSFULLY_CREATED = ANSI_GREEN + "Customer successfully created " + ANSI_RESET;
    public static final String UPDATING_CUSTOMER = ANSI_BLUE + "Updating customer: " + ANSI_RESET + "{}";
    public static final String CUSTOMER_UPDATED = ANSI_GREEN + "Customer updated: " + ANSI_RESET + "{}";
    public static final String GETTING_ALL_CUSTOMERS = ANSI_BLUE + "Getting all customers" + ANSI_RESET;
    public static final String RETRIEVED_ALL_CUSTOMERS = ANSI_GREEN + "Retrieved all customers. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_CUSTOMER_COUNT_BY_DELETED_STATE = ANSI_BLUE + "Getting customer count by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_CUSTOMER_COUNT_BY_DELETED_STATE = ANSI_GREEN + "Retrieved customer count by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_CUSTOMER_COUNT_BY_STATUS = ANSI_BLUE + "Getting customer count by status: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_CUSTOMER_COUNT_BY_STATUS = ANSI_GREEN + "Retrieved customer count by status: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_CUSTOMER_BY_ID = ANSI_BLUE + "Getting customer by id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_CUSTOMER_BY_ID = ANSI_GREEN + "Retrieved customer by id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_RENTAL_HISTORY = ANSI_BLUE + "Getting rental history for customer with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_RENTAL_HISTORY = ANSI_GREEN + "Retrieved rental history for customer with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET + ", quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_CUSTOMERS_BY_DELETED_STATE = ANSI_BLUE + "Getting customers by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_CUSTOMERS_BY_DELETED_STATE = ANSI_GREEN + "Retrieved customers by deleted state. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DELETING_CUSTOMER_WITH_ID = ANSI_BLUE + "Deleting customer with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET + " , hard delete: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String CUSTOMER_DELETED_SUCCESSFULLY_WITH_ID = ANSI_GREEN + "Customer deleted successfully with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
}

package src.controller.vehicle.features.common.brand;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {
    // Log Messages
    public static final String CREATING_NEW_BRAND = ANSI_BLUE + "Creating a new brand: " + ANSI_RESET + "{}";
    public static final String BRAND_SUCCESSFULLY_CREATED = ANSI_GREEN + "Brand successfully created " + ANSI_RESET;
    public static final String UPDATING_BRAND = ANSI_BLUE + "Updating brand: " + ANSI_RESET + "{}";
    public static final String BRAND_UPDATED = ANSI_GREEN + "Brand updated: " + ANSI_RESET + "{}";
    public static final String GETTING_BRAND_DETAILS = ANSI_BLUE + "Getting brand details for id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_BRAND_DETAILS = ANSI_GREEN + "Retrieved brand details: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_ALL_BRANDS = ANSI_BLUE + "Retrieving all brands" + ANSI_RESET;
    public static final String RETRIEVED_ALL_BRANDS = ANSI_GREEN + "Retrieved all brands. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVING_BRANDS_BY_DELETED_STATE = ANSI_BLUE + "Retrieving brands by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_BRANDS_BY_DELETED_STATE = ANSI_GREEN + "Retrieved brands by deleted state. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DELETING_BRAND_WITH_ID = ANSI_BLUE + "Deleting brand with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET + " , hard delete: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String BRAND_DELETED_SUCCESSFULLY_WITH_ID = ANSI_GREEN + "Brand deleted successfully with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
}



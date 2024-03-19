package src.controller.license;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String CREATING_NEW_DRIVING_LICENSE_TYPE = ANSI_BLUE + "Creating a new driving license type: " + ANSI_RESET + "{}";
    public static final String DRIVING_LICENSE_TYPE_CREATED_SUCCESSFULLY = ANSI_GREEN + "Driving license type created successfully " + ANSI_RESET;
    public static final String UPDATING_DRIVING_LICENSE_TYPE = ANSI_BLUE + "Updating driving license type: " + ANSI_RESET + "{}";
    public static final String DRIVING_LICENSE_TYPE_UPDATED = ANSI_GREEN + "Driving license type updated: " + ANSI_RESET + "{}";
    public static final String GETTING_DRIVING_LICENSE_TYPE_DETAILS = ANSI_BLUE + "Getting driving license type details for id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DRIVING_LICENSE_TYPE_DETAILS_RETRIEVED = ANSI_GREEN + "Retrieved driving license type details: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_ALL_DRIVING_LICENSE_TYPES = ANSI_BLUE + "Retrieving all driving license types" + ANSI_RESET;
    public static final String ALL_DRIVING_LICENSE_TYPES_RETRIEVED = ANSI_GREEN + "Retrieved all driving license types. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVING_DRIVING_LICENSE_TYPES_BY_DELETED_STATE = ANSI_BLUE + "Retrieving driving license types by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DRIVING_LICENSE_TYPES_BY_DELETED_STATE_RETRIEVED = ANSI_GREEN + "Retrieved driving license types by deleted state. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DELETING_DRIVING_LICENSE_TYPE_WITH_ID = ANSI_BLUE + "Deleting driving license type with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET + " , hard delete: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DRIVING_LICENSE_TYPE_DELETED_SUCCESSFULLY_WITH_ID = ANSI_GREEN + "Driving license type deleted successfully with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
}

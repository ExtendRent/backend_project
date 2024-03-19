package src.controller.user.admin;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String CREATING_NEW_ADMIN = ANSI_BLUE + "Creating a new admin: " + ANSI_RESET + "{}";
    public static final String ADMIN_SUCCESSFULLY_CREATED = ANSI_GREEN + "Admin successfully created " + ANSI_RESET;
    public static final String UPDATING_ADMIN = ANSI_BLUE + "Updating admin: " + ANSI_RESET + "{}";
    public static final String ADMIN_UPDATED = ANSI_GREEN + "Admin updated: " + ANSI_RESET + "{}";
    public static final String GETTING_ALL_ADMINS = ANSI_BLUE + "Getting all admins" + ANSI_RESET;
    public static final String RETRIEVED_ALL_ADMINS = ANSI_GREEN + "Retrieved all admins. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_ADMINS_BY_DELETED_STATE = ANSI_BLUE + "Getting admins by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_ADMINS_BY_DELETED_STATE = ANSI_GREEN + "Retrieved admins by deleted state. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_ADMIN_BY_ID = ANSI_BLUE + "Getting admin by id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_ADMIN_BY_ID = ANSI_GREEN + "Retrieved admin by id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_ADMIN_COUNT_BY_DELETED_STATE = ANSI_BLUE + "Getting admin count by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_ADMIN_COUNT_BY_DELETED_STATE = ANSI_GREEN + "Retrieved admin count by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DELETING_ADMIN_WITH_ID = ANSI_BLUE + "Deleting admin with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET + " , hard delete: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String ADMIN_DELETED_SUCCESSFULLY_WITH_ID = ANSI_GREEN + "Admin deleted successfully with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
}

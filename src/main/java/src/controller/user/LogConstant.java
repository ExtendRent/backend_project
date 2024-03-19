package src.controller.user;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String GETTING_ALL_USERS = ANSI_BLUE + "Getting all users" + ANSI_RESET;
    public static final String RETRIEVED_ALL_USERS = ANSI_GREEN + "Retrieved all users. Total count: " + ANSI_RESET + "{}";
    public static final String GETTING_USERS_BY_DELETED_STATE = ANSI_BLUE + "Getting users by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_USERS_BY_DELETED_STATE = ANSI_GREEN + "Retrieved users by deleted state. " + ANSI_RESET + "Count: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_USER_BY_ID = ANSI_BLUE + "Getting user by id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_USER_BY_ID = ANSI_GREEN + "Retrieved user by id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_USER_COUNT_BY_DELETED_STATE = ANSI_BLUE + "Getting user count by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_USER_COUNT_BY_DELETED_STATE = ANSI_GREEN + "Retrieved user count by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String UPDATING_USER_PASSWORD = ANSI_BLUE + "Updating user password: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String USER_PASSWORD_UPDATED = ANSI_GREEN + "User password updated: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String BLOCKING_USER = ANSI_BLUE + "Blocking user: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String USER_BLOCKED = ANSI_GREEN + "User blocked: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
}

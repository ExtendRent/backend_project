package src.controller.vehicle.features.common.shift;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String CREATING_NEW_SHIFT = ANSI_BLUE + "Creating a new shift: " + ANSI_RESET + "{}";
    public static final String SHIFT_SUCCESSFULLY_CREATED = ANSI_GREEN + "Shift successfully created " + ANSI_RESET;
    public static final String UPDATING_SHIFT = ANSI_BLUE + "Updating shift: " + ANSI_RESET + "{}";
    public static final String SHIFT_UPDATED = ANSI_GREEN + "Shift updated: " + ANSI_RESET + "{}";
    public static final String GETTING_SHIFT_DETAILS = ANSI_BLUE + "Getting shift details for id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_SHIFT_DETAILS = ANSI_GREEN + "Retrieved shift details: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_ALL_SHIFTS = ANSI_BLUE + "Retrieving all shifts" + ANSI_RESET;
    public static final String RETRIEVED_ALL_SHIFTS = ANSI_GREEN + "Retrieved all shifts. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVING_SHIFTS_BY_DELETED_STATE = ANSI_BLUE + "Retrieving shifts by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_SHIFTS_BY_DELETED_STATE = ANSI_GREEN + "Retrieved shifts by deleted state. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DELETING_SHIFT_WITH_ID = ANSI_BLUE + "Deleting shift with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET + " , hard delete: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String SHIFT_DELETED_SUCCESSFULLY_WITH_ID = ANSI_GREEN + "Shift deleted successfully with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
}

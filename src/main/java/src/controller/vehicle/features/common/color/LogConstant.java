package src.controller.vehicle.features.common.color;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String CREATING_NEW_COLOR = ANSI_BLUE + "Creating a new color: " + ANSI_RESET + "{}";
    public static final String COLOR_SUCCESSFULLY_CREATED = ANSI_GREEN + "Color successfully created " + ANSI_RESET;
    public static final String UPDATING_COLOR = ANSI_BLUE + "Updating color: " + ANSI_RESET + "{}";
    public static final String COLOR_UPDATED = ANSI_GREEN + "Color updated: " + ANSI_RESET + "{}";
    public static final String GETTING_COLOR_DETAILS = ANSI_BLUE + "Getting color details for id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_COLOR_DETAILS = ANSI_GREEN + "Retrieved color details: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_ALL_COLORS = ANSI_BLUE + "Retrieving all colors" + ANSI_RESET;
    public static final String RETRIEVED_ALL_COLORS = ANSI_GREEN + "Retrieved all colors. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVING_COLORS_BY_DELETED_STATE = ANSI_BLUE + "Retrieving colors by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_COLORS_BY_DELETED_STATE = ANSI_GREEN + "Retrieved colors by deleted state. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DELETING_COLOR_WITH_ID = ANSI_BLUE + "Deleting color with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET + " , hard delete: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String COLOR_DELETED_SUCCESSFULLY_WITH_ID = ANSI_GREEN + "Color deleted successfully with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
}

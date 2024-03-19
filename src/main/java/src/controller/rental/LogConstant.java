package src.controller.rental;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String SHOWING_RENTAL_DETAILS = ANSI_BLUE + "Showing rental details: " + ANSI_RESET + "{}";
    public static final String RENTAL_DETAILS_SHOWN = ANSI_GREEN + "Rental details shown." + ANSI_RESET;
    public static final String CREATING_NEW_RENTAL = ANSI_BLUE + "Creating a new rental: " + ANSI_RESET + "{}";
    public static final String RENTAL_SUCCESSFULLY_CREATED = ANSI_GREEN + "Rental successfully created." + ANSI_RESET;
    public static final String UPDATING_RENTAL = ANSI_BLUE + "Updating rental: " + ANSI_RESET + "{}";
    public static final String RENTAL_UPDATED = ANSI_GREEN + "Rental updated: " + ANSI_RESET + "{}";
    public static final String STARTING_RENTAL = ANSI_BLUE + "Starting rental with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RENTAL_STARTED = ANSI_GREEN + "Rental started: " + ANSI_RESET + "{}";
    public static final String RETURNING_RENTAL = ANSI_BLUE + "Returning rental: " + ANSI_RESET + "{}";
    public static final String RENTAL_RETURNED = ANSI_GREEN + "Rental returned." + ANSI_RESET;
    public static final String CANCELING_RENTAL = ANSI_BLUE + "Canceling rental with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RENTAL_CANCELED = ANSI_GREEN + "Rental canceled: " + ANSI_RESET + "{}";
    public static final String GETTING_ALL_RENTALS = ANSI_BLUE + "Getting all rentals" + ANSI_RESET;
    public static final String RETRIEVED_ALL_RENTALS = ANSI_GREEN + "Retrieved all rentals. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_ALL_RENTAL_STATUSES = ANSI_BLUE + "Getting all rental statuses" + ANSI_RESET;
    public static final String RETRIEVED_ALL_RENTAL_STATUSES = ANSI_GREEN + "Retrieved all rental statuses. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_RENTAL_COUNT_BY_DELETED_STATE = ANSI_BLUE + "Getting rental count by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_RENTAL_COUNT_BY_DELETED_STATE = ANSI_GREEN + "Retrieved rental count by deleted state: " + ANSI_RESET + "{}";
    public static final String GETTING_RENTAL_COUNT_BY_STATUS = ANSI_BLUE + "Getting rental count by status: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_RENTAL_COUNT_BY_STATUS = ANSI_GREEN + "Retrieved rental count by status: " + ANSI_RESET + "{}";
    public static final String GETTING_RENTAL_BY_ID = ANSI_BLUE + "Getting rental by id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_RENTAL_BY_ID = ANSI_GREEN + "Retrieved rental by id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_RENTALS_BY_DELETED_STATE = ANSI_BLUE + "Getting rentals by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_RENTALS_BY_DELETED_STATE = ANSI_GREEN + "Retrieved rentals by deleted state. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String GETTING_RENTALS_BY_STATUS = ANSI_BLUE + "Getting rentals by status: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_RENTALS_BY_STATUS = ANSI_GREEN + "Retrieved rentals by status. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DELETING_RENTAL = ANSI_BLUE + "Deleting rental with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET + " , hard delete: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RENTAL_DELETED_SUCCESSFULLY = ANSI_GREEN + "Rental deleted successfully with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
}

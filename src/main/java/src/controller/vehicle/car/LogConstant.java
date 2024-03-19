package src.controller.vehicle.car;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String CREATING_NEW_CAR = ANSI_BLUE + "Creating a new car: " + ANSI_RESET + "{}";
    public static final String CAR_SUCCESSFULLY_CREATED = ANSI_GREEN + "Car successfully created " + ANSI_RESET;
    public static final String UPDATING_CAR = ANSI_BLUE + "Updating car: " + ANSI_RESET + "{}";
    public static final String CAR_UPDATED = ANSI_GREEN + "Car updated: " + ANSI_RESET + "{}";
    public static final String GETTING_CAR_DETAILS = ANSI_BLUE + "Getting car details for id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_CAR_DETAILS = ANSI_GREEN + "Retrieved car details: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_ALL_CARS = ANSI_BLUE + "Retrieving all cars" + ANSI_RESET;
    public static final String RETRIEVED_ALL_CARS = ANSI_GREEN + "Retrieved all cars. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVING_CAR_COUNT_BY_DELETED_STATE = ANSI_BLUE + "Retrieving car count by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_CAR_COUNT_BY_DELETED_STATE = ANSI_GREEN + "Retrieved car count by deleted state. " + ANSI_RESET + "count: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVING_CAR_COUNT_BY_STATUS_ID = ANSI_BLUE + "Retrieving car count by status ID: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_CAR_COUNT_BY_STATUS_ID = ANSI_GREEN + "Retrieved car count by status ID. " + ANSI_RESET + "count: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String FILTERING_CARS = ANSI_BLUE + "Filtering cars" + ANSI_RESET;
    public static final String FILTERED_CARS = ANSI_GREEN + "Filtered cars. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVING_CARS_BY_AVAILABILITY = ANSI_BLUE + "Retrieving cars by availability between: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET + " and " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_CARS_BY_AVAILABILITY = ANSI_GREEN + "Retrieved cars by availability. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVING_ALL_CARS_BY_DELETED_STATE = ANSI_BLUE + "Retrieving all cars by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_ALL_CARS_BY_DELETED_STATE = ANSI_GREEN + "Retrieved all cars by deleted state. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DELETING_CAR_WITH_ID = ANSI_BLUE + "Deleting car with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET + ", hard delete: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String CAR_DELETED_SUCCESSFULLY_WITH_ID = ANSI_GREEN + "Car deleted successfully with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
}

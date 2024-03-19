package src.controller.vehicle.features.car.body;


import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String CREATING_NEW_CAR_BODY_TYPE = ANSI_BLUE + "Creating a new car body type: " + ANSI_RESET + "{}";
    public static final String CAR_BODY_TYPE_SUCCESSFULLY_CREATED = ANSI_GREEN + "Car body type successfully created " + ANSI_RESET;
    public static final String UPDATING_CAR_BODY_TYPE = ANSI_BLUE + "Updating car body type: " + ANSI_RESET + "{}";
    public static final String CAR_BODY_TYPE_UPDATED = ANSI_GREEN + "Car body type updated: " + ANSI_RESET + "{}";
    public static final String GETTING_CAR_BODY_TYPE_DETAILS = ANSI_BLUE + "Getting car body type details for id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_CAR_BODY_TYPE_DETAILS = ANSI_GREEN + "Retrieved car body type details: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_ALL_CAR_BODY_TYPES = ANSI_BLUE + "Retrieving all car body types" + ANSI_RESET;
    public static final String RETRIEVED_ALL_CAR_BODY_TYPES = ANSI_GREEN + "Retrieved all car body types. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVING_CAR_BODY_TYPES_BY_DELETED_STATE = ANSI_BLUE + "Retrieving car body types by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_CAR_BODY_TYPES_BY_DELETED_STATE = ANSI_GREEN + "Retrieved car body types by deleted state. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DELETING_CAR_BODY_TYPE_WITH_ID = ANSI_BLUE + "Deleting car body type with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET + " , hard delete: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String CAR_BODY_TYPE_DELETED_SUCCESSFULLY_WITH_ID = ANSI_GREEN + "Car body type deleted successfully with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
}

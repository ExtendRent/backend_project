package src.controller.vehicle.features.common.fuel;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Mesajları
    public static final String CREATING_NEW_FUEL_TYPE = ANSI_BLUE + "Creating a new fuel type: " + ANSI_RESET + "{}";
    public static final String FUEL_TYPE_SUCCESSFULLY_CREATED = ANSI_GREEN + "Fuel type successfully created " + ANSI_RESET;
    public static final String UPDATING_FUEL_TYPE = ANSI_BLUE + "Updating fuel type: " + ANSI_RESET + "{}";
    public static final String FUEL_TYPE_UPDATED = ANSI_GREEN + "Fuel type updated: " + ANSI_RESET + "{}";
    public static final String GETTING_FUEL_TYPE_DETAILS = ANSI_BLUE + "Getting fuel type details for id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_FUEL_TYPE_DETAILS = ANSI_GREEN + "Retrieved fuel type details: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_ALL_FUEL_TYPES = ANSI_BLUE + "Retrieving all fuel types" + ANSI_RESET;
    public static final String RETRIEVED_ALL_FUEL_TYPES = ANSI_GREEN + "Retrieved all fuel types. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVING_FUEL_TYPES_BY_DELETED_STATE = ANSI_BLUE + "Retrieving fuel types by deleted state: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_FUEL_TYPES_BY_DELETED_STATE = ANSI_GREEN + "Retrieved fuel types by deleted state. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DELETING_FUEL_TYPE_WITH_ID = ANSI_BLUE + "Deleting fuel type with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String FUEL_TYPE_DELETED_SUCCESSFULLY_WITH_ID = ANSI_GREEN + "Fuel type deleted successfully with id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
}

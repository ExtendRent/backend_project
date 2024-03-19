package src.controller.vehicle.features.car.model;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String CREATING_NEW_CAR_MODEL = ANSI_BLUE + "Creating a new car model: " + ANSI_RESET + "{}";
    public static final String CAR_MODEL_SUCCESSFULLY_CREATED = ANSI_GREEN + "Car model successfully created" + ANSI_RESET;
    public static final String UPDATING_CAR_MODEL = ANSI_BLUE + "Updating car model: " + ANSI_RESET + "{}";
    public static final String CAR_MODEL_UPDATED = ANSI_GREEN + "Car model updated: " + ANSI_RESET + "{}";
    public static final String GETTING_CAR_MODEL_DETAILS = ANSI_BLUE + "Getting car model details for id: " + ANSI_RESET + "{}";
    public static final String RETRIEVED_CAR_MODEL_DETAILS = ANSI_GREEN + "Retrieved car model details: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_ALL_CAR_MODELS = ANSI_BLUE + "Retrieving all car models" + ANSI_RESET;
    public static final String RETRIEVED_ALL_CAR_MODELS = ANSI_GREEN + "Retrieved all car models. " + ANSI_RESET + "Quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVING_CAR_MODELS_BY_BRAND_ID = ANSI_BLUE + "Retrieving car models by brand id: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_CAR_MODELS_BY_DELETED_STATE_BEFORE_CALL = ANSI_BLUE + "Retrieving car models by deleted state before call: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_CAR_MODELS_BY_DELETED_STATE_AFTER_CALL = ANSI_GREEN + "Retrieving car models by deleted state after call. " + ANSI_RESET + "Quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String DELETING_CAR_MODEL_WITH_ID = ANSI_BLUE + "Deleting car model with id: " + ANSI_RESET + "{}" + ANSI_BOLD + ", hard delete: " + ANSI_RESET + "{}";
    public static final String CAR_MODEL_DELETED_SUCCESSFULLY_WITH_ID = ANSI_GREEN + "Car model deleted successfully with id: " + ANSI_RESET + "{}";
}

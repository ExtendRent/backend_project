package src.controller.vehicle.features.car.segment;
import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String CREATING_NEW_CAR_SEGMENT = ANSI_BLUE + "Creating a new car segment: " + ANSI_RESET + "{}";
    public static final String CAR_SEGMENT_SUCCESSFULLY_CREATED = ANSI_GREEN + "Car segment successfully created" + ANSI_RESET;
    public static final String UPDATING_CAR_SEGMENT = ANSI_BLUE + "Updating car segment: " + ANSI_RESET + "{}";
    public static final String CAR_SEGMENT_UPDATED = ANSI_GREEN + "Car segment updated: " + ANSI_RESET + "{}";
    public static final String GETTING_CAR_SEGMENT_DETAILS = ANSI_BLUE + "Getting car segment details for id: " + ANSI_RESET + "{}";
    public static final String RETRIEVED_CAR_SEGMENT_DETAILS = ANSI_GREEN + "Retrieved car segment details: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_ALL_CAR_SEGMENTS = ANSI_BLUE + "Retrieving all car segments" + ANSI_RESET;
    public static final String RETRIEVED_ALL_CAR_SEGMENTS = ANSI_GREEN + "Retrieved all car segments. Quantity: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_CAR_SEGMENTS_BY_DELETED_STATE = ANSI_BLUE + "Retrieving car segments by deleted state: " + ANSI_RESET + "{}";
    public static final String RETRIEVED_CAR_SEGMENTS_BY_DELETED_STATE = ANSI_GREEN + "Retrieved car segments by deleted state. Quantity: " + ANSI_RESET + "{}";
    public static final String DELETING_CAR_SEGMENT_WITH_ID = ANSI_BLUE + "Deleting car segment with id: " + ANSI_RESET + "{}" + ANSI_BOLD + ", hard delete: " + ANSI_RESET + "{}";
    public static final String CAR_SEGMENT_DELETED_SUCCESSFULLY_WITH_ID = ANSI_GREEN + "Car segment deleted successfully with id: " + ANSI_RESET + "{}";
}

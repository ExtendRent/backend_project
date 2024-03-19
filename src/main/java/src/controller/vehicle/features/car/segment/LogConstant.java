package src.controller.vehicle.features.car.segment;

public final class LogConstant {

    // Log Messages
    public static final String CREATING_NEW_CAR_SEGMENT = "Creating a new car segment: {}";
    public static final String CAR_SEGMENT_SUCCESSFULLY_CREATED = "Car segment successfully created";
    public static final String UPDATING_CAR_SEGMENT = "Updating car segment: {}";
    public static final String CAR_SEGMENT_UPDATED = "Car segment updated: {}";
    public static final String GETTING_CAR_SEGMENT_DETAILS = "Getting car segment details for id: {}";
    public static final String RETRIEVED_CAR_SEGMENT_DETAILS = "Retrieved car segment details: {}";
    public static final String RETRIEVING_ALL_CAR_SEGMENTS = "Retrieving all car segments";
    public static final String RETRIEVED_ALL_CAR_SEGMENTS = "Retrieved all car segments. Quantity: {}";
    public static final String RETRIEVING_CAR_SEGMENTS_BY_DELETED_STATE = "Retrieving car segments by deleted state: {}";
    public static final String RETRIEVED_CAR_SEGMENTS_BY_DELETED_STATE = "Retrieved car segments by deleted state. Quantity: {}";
    public static final String DELETING_CAR_SEGMENT_WITH_ID = "Deleting car segment with id: {}, hard delete: {}";
    public static final String CAR_SEGMENT_DELETED_SUCCESSFULLY_WITH_ID = "Car segment deleted successfully with id: {}";

    private LogConstant() {
        // Private constructor to prevent instantiation
    }
}

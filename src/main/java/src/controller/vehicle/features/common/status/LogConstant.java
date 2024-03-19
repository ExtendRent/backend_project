package src.controller.vehicle.features.common.status;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String UPDATING_VEHICLE_STATUS = ANSI_BLUE + "Updating vehicle status: " + ANSI_RESET + "{}";
    public static final String VEHICLE_STATUS_UPDATED = ANSI_GREEN + "Vehicle status updated: " + ANSI_RESET + "{}";
    public static final String GETTING_VEHICLE_STATUS_DETAILS = ANSI_BLUE + "Getting vehicle status details for id: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_VEHICLE_STATUS_DETAILS = ANSI_GREEN + "Retrieved vehicle status details: " + ANSI_RESET + "{}";
    public static final String GETTING_VEHICLE_STATUS_BY_STATUS = ANSI_BLUE + "Getting vehicle status by status: " + ANSI_RESET + ANSI_BOLD + "{}" + ANSI_RESET;
    public static final String RETRIEVED_VEHICLE_STATUS_BY_STATUS = ANSI_GREEN + "Retrieved vehicle status by status: " + ANSI_RESET + "{}";
    public static final String RETRIEVING_ALL_VEHICLE_STATUSES = ANSI_BLUE + "Retrieving all vehicle statuses" + ANSI_RESET;
    public static final String RETRIEVED_ALL_VEHICLE_STATUSES = ANSI_GREEN + "Retrieved all vehicle statuses. " + ANSI_RESET + "quantity: " + ANSI_BOLD + "{}" + ANSI_RESET;
}

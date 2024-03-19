package src.controller.image;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String UPLOADING_CAR_IMAGE = ANSI_BLUE + "Uploading car image for license plate: " + ANSI_RESET + "{}";
    public static final String CAR_IMAGE_UPLOADED_SUCCESSFULLY = ANSI_GREEN + "Car image uploaded successfully for license plate: " + ANSI_RESET + "{}";
    public static final String UPLOADING_USER_IMAGE = ANSI_BLUE + "Uploading user image for email address: " + ANSI_RESET + "{}";
    public static final String USER_IMAGE_UPLOADED_SUCCESSFULLY = ANSI_GREEN + "User image uploaded successfully for email address: " + ANSI_RESET + "{}";
    public static final String UPLOADING_BRAND_IMAGE = ANSI_BLUE + "Uploading brand image for brand name: " + ANSI_RESET + "{}";
    public static final String BRAND_IMAGE_UPLOADED_SUCCESSFULLY = ANSI_GREEN + "Brand image uploaded successfully for brand name: " + ANSI_RESET + "{}";
}

package src.controller.auth.verify;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String VERIFY_EMAIL_ADDRESS_REQUEST_RECEIVED = ANSI_BLUE + "Received request to verify email address with token: " + ANSI_RESET + "{}";
    public static final String VERIFY_EMAIL_ADDRESS_SUCCESSFUL = ANSI_GREEN + "Email address verification successful." + ANSI_RESET;
}

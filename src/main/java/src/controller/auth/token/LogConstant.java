package src.controller.auth.token;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String REFRESH_TOKEN_REQUEST_RECEIVED = ANSI_BLUE + "Received refresh token request with refresh token: " + ANSI_RESET + "{}";
    public static final String REFRESH_TOKEN_SUCCESSFUL = ANSI_GREEN + "Token refresh successful." + ANSI_RESET;
}

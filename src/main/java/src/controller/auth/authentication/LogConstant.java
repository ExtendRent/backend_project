package src.controller.auth.authentication;

import static src.controller.AnsiColorConstant.*;

public final class LogConstant {

    // Log Messages
    public static final String USER_SIGN_UP_REQUEST_RECEIVED = ANSI_BLUE + "Received sign up request for user: " + ANSI_RESET + "{}";
    public static final String USER_SIGN_UP_SUCCESSFUL = ANSI_GREEN + "User sign up successful for user: " + ANSI_RESET + "{}";
    public static final String USER_SIGN_IN_REQUEST_RECEIVED = ANSI_BLUE + "Received sign in request for user: " + ANSI_RESET + "{}";
    public static final String USER_SIGN_IN_SUCCESSFUL = ANSI_GREEN + "User sign in successful for user: " + ANSI_RESET + "{}";
    public static final String CHECKING_USER_CREDENTIALS = ANSI_BLUE + "Checking user credentials for user: " + ANSI_RESET + "{}";
    public static final String USER_CREDENTIALS_CHECKED = ANSI_GREEN + "User credentials checked for user: " + ANSI_RESET + "{}";
}

package src.core.exception;

import static src.controller.AnsiColorConstant.ANSI_RED;
import static src.controller.AnsiColorConstant.ANSI_RESET;

public final class ErrorLogConstant {

    // Error Messages
    public static final String ERROR_GENERIC_EXCEPTION = ANSI_RED + "An error occurred: " + ANSI_RESET + "{}\n";
    public static final String ERROR_DATA_NOT_FOUND = ANSI_RED + "Data not found: " + ANSI_RESET + "{}\n";
    public static final String ERROR_FILE = ANSI_RED + "File error: " + ANSI_RESET + "{}\n";
    public static final String ERROR_ALREADY_EXISTS = ANSI_RED + "Already exists: " + ANSI_RESET + "{}\n";
    public static final String ERROR_PAYMENT = ANSI_RED + "Payment error: " + ANSI_RESET + "{}\n";
    public static final String ERROR_NOT_SUITABLE = ANSI_RED + "Not suitable: " + ANSI_RESET + "{}\n";
    public static final String ERROR_VALIDATION = ANSI_RED + "Validation error: " + ANSI_RESET + "{}\n";
}
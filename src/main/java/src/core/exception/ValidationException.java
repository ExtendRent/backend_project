package src.core.exception;

import lombok.Getter;
import lombok.Setter;
import src.core.exception.type.ValidationExceptionType;

@Getter
@Setter
public class ValidationException extends RuntimeException {

    private final ValidationExceptionType validationExceptionType;

    private String detail;


    public ValidationException(ValidationExceptionType validationExceptionType, String detail) {
        super(validationExceptionType.getMessage());
        this.validationExceptionType = validationExceptionType;
        this.detail = detail;
    }

    public ValidationException(ValidationExceptionType validationExceptionType) {
        super(validationExceptionType.getMessage());
        this.validationExceptionType = validationExceptionType;
        this.detail = validationExceptionType.getMessage();
    }
}

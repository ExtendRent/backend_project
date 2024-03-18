package src.core.exception;

import lombok.Getter;
import src.core.exception.type.AlreadyExistsExceptionType;

@Getter
public class AlreadyExistsException extends RuntimeException {

    private final AlreadyExistsExceptionType alreadyExistsExceptionType;
    private final String detail;


    public AlreadyExistsException(AlreadyExistsExceptionType alreadyExistsExceptionType, String detail) {
        super(alreadyExistsExceptionType.getMessage());
        this.alreadyExistsExceptionType = alreadyExistsExceptionType;
        this.detail = detail;
    }

    public AlreadyExistsException(AlreadyExistsExceptionType alreadyExistsExceptionType) {
        super(alreadyExistsExceptionType.getMessage());
        this.alreadyExistsExceptionType = alreadyExistsExceptionType;
        this.detail = alreadyExistsExceptionType.getMessage();
    }

    @Override
    public String toString() {
        return "AlreadyExistsException{" +
                "errorCode=" + alreadyExistsExceptionType.getErrorCode() +
                ", detail='" + detail + '\'' +
                '}';
    }
}

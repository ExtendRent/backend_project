package source_files.exception;

import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException {

    private final NotFoundExceptionType notFoundExceptionType;
    private String detail;


    public DataNotFoundException(NotFoundExceptionType notFoundExceptionType, String detail) {
        super(notFoundExceptionType.getMessage());
        this.notFoundExceptionType = notFoundExceptionType;
        this.detail = detail;
    }

    public DataNotFoundException(NotFoundExceptionType notFoundExceptionType) {
        super(notFoundExceptionType.getMessage());
        this.notFoundExceptionType = notFoundExceptionType;
    }


}

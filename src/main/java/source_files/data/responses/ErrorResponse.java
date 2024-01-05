package source_files.data.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.exception.exceptionTypes.AlreadyExistsExceptionType;
import source_files.exception.exceptionTypes.NotFoundExceptionType;
import source_files.exception.exceptionTypes.ValidationExceptionType;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {

    private int errorCode;

    private LocalDateTime timestamp = LocalDateTime.now();
    private List<String> details;

    //TODO bilgi: CUSTOM Exception Handler ile error response dönüyoruz.
    public ErrorResponse(NotFoundExceptionType notFoundExceptionType, List<String> details) {
        this.errorCode = notFoundExceptionType.getErrorCode();
        this.details = details;
    }

    public ErrorResponse(AlreadyExistsExceptionType alreadyExistsExceptionType, List<String> details) {
        this.errorCode = alreadyExistsExceptionType.getErrorCode();
        this.details = details;
    }

    public ErrorResponse(ValidationExceptionType validationExceptionType, List<String> details) {
        this.errorCode = validationExceptionType.getErrorCode();
        this.details = details;
    }


}
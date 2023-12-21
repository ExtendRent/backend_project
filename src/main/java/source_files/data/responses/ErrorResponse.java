package source_files.data.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.exception.AlreadyExistsExceptionType;
import source_files.exception.NotFoundExceptionType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private int errorCode;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String details;

    //TODO bilgi: CUSTOM Exception Handler ile error response dönüyoruz.
    public ErrorResponse(NotFoundExceptionType notFoundExceptionType, String details) {
        this.errorCode = notFoundExceptionType.getErrorCode();
        this.message = notFoundExceptionType.getMessage();
        this.details = details;
    }

    public ErrorResponse(AlreadyExistsExceptionType alreadyExistsExceptionType, String details) {
        this.errorCode = alreadyExistsExceptionType.getErrorCode();
        this.message = alreadyExistsExceptionType.getMessage();
        this.details = details;
    }

}
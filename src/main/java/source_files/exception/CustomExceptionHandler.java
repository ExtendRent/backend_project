package source_files.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import source_files.data.responses.ErrorResponse;
import source_files.data.responses.TResponse;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public TResponse<?> handleException(Exception e) {
        return TResponse.tResponseBuilder()
                .isSuccess(false)
                .response(new ErrorResponse(NotFoundExceptionType.GENERIC_EXCEPTION, e.getMessage()))
                .build();
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public TResponse<?> handleDataNotFoundException(DataNotFoundException e) {
        return TResponse.tResponseBuilder()
                .isSuccess(false)
                .response(new ErrorResponse(e.getNotFoundExceptionType(), e.getDetail()))
                .build();
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TResponse<?> handleAlreadyExistsException(AlreadyExistsException e) {
        return TResponse.tResponseBuilder()
                .isSuccess(false)
                .response(new ErrorResponse(e.getAlreadyExistsExceptionType(), e.getDetail()))
                .build();
    }


}

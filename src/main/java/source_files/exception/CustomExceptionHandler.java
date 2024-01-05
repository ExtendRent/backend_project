package source_files.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import source_files.data.responses.ErrorResponse;
import source_files.data.responses.TResponse;
import source_files.exception.exceptionTypes.NotFoundExceptionType;
import source_files.exception.exceptionTypes.ValidationExceptionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public TResponse<?> handleException(Exception e) {
        return TResponse.tResponseBuilder()
                .isSuccess(false)
                .response(new ErrorResponse(NotFoundExceptionType.GENERIC_EXCEPTION, Collections.singletonList(e.getMessage())))
                .build();
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public TResponse<?> handleDataNotFoundException(DataNotFoundException e) {
        return TResponse.tResponseBuilder()
                .message(e.getNotFoundExceptionType().getMessage())
                .isSuccess(false)
                .response(new ErrorResponse(e.getNotFoundExceptionType(), Collections.singletonList(e.getDetail())))
                .build();
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TResponse<?> handleAlreadyExistsException(AlreadyExistsException e) {
        return TResponse.tResponseBuilder()
                .message(e.getAlreadyExistsExceptionType().getMessage())
                .isSuccess(false)
                .response(new ErrorResponse(e.getAlreadyExistsExceptionType(), Collections.singletonList(e.getDetail())))
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TResponse<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> validationErrors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            validationErrors.add(fieldName + ": " + errorMessage);
        });

        ValidationExceptionType validationExceptionType = ValidationExceptionType.VALIDATION_EXCEPTION;
        ValidationException validationException = new ValidationException(validationExceptionType, "Validation error");
        validationException.setDetail(validationErrors.toString());

        ErrorResponse errorResponse = new ErrorResponse(validationExceptionType, Collections.singletonList("Validation error"));
        errorResponse.setDetails(validationErrors);

        return TResponse.tResponseBuilder()
                .message("Validation error")
                .isSuccess(false)
                .response(errorResponse)
                .build();
    }


}

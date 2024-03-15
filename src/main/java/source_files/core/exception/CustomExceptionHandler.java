package source_files.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import source_files.core.exception.exceptionTypes.NotFoundExceptionType;
import source_files.core.exception.exceptionTypes.ValidationExceptionType;
import source_files.data.responses.ErrorResponse;
import source_files.data.responses.TResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public TResponse<?> handleException(Exception e) {
        return TResponse.tResponseBuilder()
                .response(new ErrorResponse(NotFoundExceptionType.GENERIC_EXCEPTION, Collections.singletonList(e.getMessage())))
                .build();
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public TResponse<?> handleDataNotFoundException(DataNotFoundException e) {
        return TResponse.tResponseBuilder()
                .response(new ErrorResponse(e.getNotFoundExceptionType(), Collections.singletonList(e.getDetail())))
                .build();
    }

    @ExceptionHandler(FileException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public TResponse<?> handleFileException(FileException e) {
        return TResponse.tResponseBuilder()
                .response(new ErrorResponse(e.getFileExceptionType(), Collections.singletonList(e.getDetail())))
                .build();
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TResponse<?> handleAlreadyExistsException(AlreadyExistsException e) {
        return TResponse.tResponseBuilder()
                .response(new ErrorResponse(e.getAlreadyExistsExceptionType(), Collections.singletonList(e.getDetail())))
                .build();
    }

    @ExceptionHandler(PaymentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TResponse<?> handlePaymentException(PaymentException e) {
        return TResponse.tResponseBuilder()
                .response(new ErrorResponse(e.getPaymentExceptionType(), Collections.singletonList(e.getDetail())))
                .build();
    }

    @ExceptionHandler(NotSuitableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TResponse<?> handleNotSuitableException(NotSuitableException e) {
        return TResponse.tResponseBuilder()
                .response(new ErrorResponse(e.getNotSuitableExceptionType(), Collections.singletonList(e.getDetail())))
                .build();
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TResponse<?> handleValidationException(ValidationException e) {
        return TResponse.tResponseBuilder()
                .response(new ErrorResponse(e.getValidationExceptionType(), Collections.singletonList(e.getDetail())))
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
                .response(errorResponse)
                .build();
    }


}

package src.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import src.controller.TResponse;
import src.core.exception.response.ErrorResponse;
import src.core.exception.type.NotFoundExceptionType;
import src.core.exception.type.ValidationExceptionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static src.core.exception.ErrorLogConstant.*;

@RestControllerAdvice
public class CustomExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public TResponse<?> handleException(Exception e) {
        log(ERROR_GENERIC_EXCEPTION, e);
        return TResponse.tResponseBuilder()
                .response(new ErrorResponse(NotFoundExceptionType.GENERIC_EXCEPTION, Collections.singletonList(e.getMessage())))
                .build();
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public TResponse<?> handleDataNotFoundException(DataNotFoundException e) {
        log(ERROR_DATA_NOT_FOUND, e);
        e.printStackTrace();
        return TResponse.tResponseBuilder()
                .response(new ErrorResponse(e.getNotFoundExceptionType(), Collections.singletonList(e.getDetail())))
                .build();
    }

    @ExceptionHandler(FileException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public TResponse<?> handleFileException(FileException e) {
        log(ERROR_FILE, e);
        return TResponse.tResponseBuilder()
                .response(new ErrorResponse(e.getFileExceptionType(), Collections.singletonList(e.getDetail())))
                .build();
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TResponse<?> handleAlreadyExistsException(AlreadyExistsException e) {
        log(ERROR_ALREADY_EXISTS, e);
        return TResponse.tResponseBuilder()
                .response(new ErrorResponse(e.getAlreadyExistsExceptionType(), Collections.singletonList(e.getDetail())))
                .build();
    }

    @ExceptionHandler(PaymentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TResponse<?> handlePaymentException(PaymentException e) {
        log(ERROR_PAYMENT, e);
        return TResponse.tResponseBuilder()
                .response(new ErrorResponse(e.getPaymentExceptionType(), Collections.singletonList(e.getDetail())))
                .build();
    }

    @ExceptionHandler(NotSuitableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TResponse<?> handleNotSuitableException(NotSuitableException e) {
        log(ERROR_NOT_SUITABLE, e);
        return TResponse.tResponseBuilder()
                .response(new ErrorResponse(e.getNotSuitableExceptionType(), Collections.singletonList(e.getDetail())))
                .build();
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TResponse<?> handleValidationException(ValidationException e) {
        log(ERROR_VALIDATION, e);
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

        log(ERROR_VALIDATION, validationException);
        ErrorResponse errorResponse = new ErrorResponse(validationExceptionType, Collections.singletonList("Validation error"));
        errorResponse.setDetails(validationErrors);

        return TResponse.tResponseBuilder()
                .response(errorResponse)
                .build();
    }

    private void log(String errorLogConstant, Exception e) {
        logger.error(errorLogConstant, e.toString());
        e.printStackTrace();
    }
}

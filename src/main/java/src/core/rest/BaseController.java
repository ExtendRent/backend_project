package src.core.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import src.controller.TResponse;

@Slf4j
public class BaseController {

    protected <T> ResponseEntity<TResponse<T>> answer(T item, HttpStatus status) {
        TResponse<T> response = TResponse.<T>tResponseBuilder()
                .response(item).build();

        return new ResponseEntity<>(response, status);
    }

    protected ResponseEntity<Void> answer(HttpStatus status) {
        return new ResponseEntity<>(status);
    }

}

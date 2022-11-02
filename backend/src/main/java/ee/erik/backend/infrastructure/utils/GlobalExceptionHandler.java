package ee.erik.backend.infrastructure.utils;


import ee.erik.backend.domain.entities.Error;
import ee.erik.backend.domain.services.exceptions.DomainEventDateException;

import ee.erik.backend.domain.services.exceptions.DomainNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            DomainEventDateException.class
    })
    public ResponseEntity<Error> handleEventDateExceptions(Exception ex, WebRequest request) throws IOException {
        return new ResponseEntity<>(new Error(HttpStatus.FORBIDDEN.value(), ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({
            DomainNotFoundException.class
    })
    public ResponseEntity<Error> handleNotFoundExceptions(Exception ex, WebRequest request) throws IOException {
        return new ResponseEntity<>(new Error(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}

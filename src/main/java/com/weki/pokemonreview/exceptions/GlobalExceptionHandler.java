package com.weki.pokemonreview.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorModel> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorModel error = new ErrorModel();
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(new Date());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

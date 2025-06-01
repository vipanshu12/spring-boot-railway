package com.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllExceptions(Exception ue, WebRequest request) {
        ErrorDetails error = new ErrorDetails(
            ue.getMessage(),                    // message
            LocalDateTime.now(),               // timestamp
            request.getDescription(false)      // error (e.g., URI info)
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

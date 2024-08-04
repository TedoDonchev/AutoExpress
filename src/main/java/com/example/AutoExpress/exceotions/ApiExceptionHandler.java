package com.example.AutoExpress.exceotions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserAlreadyExistsException e) {
        ApiException exception = new ApiException(
                                           e.getMessage(),
                                           HttpStatus.BAD_REQUEST,
                                            ZonedDateTime.now(ZoneId.of("Z"))
                                        );

        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

}

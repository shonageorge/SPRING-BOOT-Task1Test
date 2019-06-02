//global exception for track not found exception and track already exist exception

package com.stackroute.muzix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = TrackNotFoundException.class)                            //Global exception handler for track not found exception
    public ResponseEntity<Object> exception(TrackNotFoundException exception) {
        return new ResponseEntity<>("track not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TrackAlreadyExistException.class)                        //Global exception handler for track already exist exception
    public ResponseEntity<Object> exception(TrackAlreadyExistException exception) {
        return new ResponseEntity<>("track already Exists", HttpStatus.NOT_FOUND);
    }

}


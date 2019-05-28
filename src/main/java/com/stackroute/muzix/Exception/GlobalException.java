package com.stackroute.muzix.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

    @ControllerAdvice
    public class GlobalException {

        @ExceptionHandler(AlreadyExistException.class)
        public ResponseForException handleTrackAlreadyExistsException(AlreadyExistException trackAlreadyExistException) {

            ResponseForException error = new ResponseForException();
            error.setErrorMessage(trackAlreadyExistException.getMessage());
            //error.setRequestURI(request.getRequestURI());

            return error;
        }

        @ExceptionHandler(TrackNotFound.class)
        public ResponseForException handleTrackNotFoundException(TrackNotFound trackNotFound) {

            ResponseForException error = new ResponseForException();
            error.setErrorMessage(trackNotFound.getMessage());
            //error.setRequestURI(request.getRequestURI());

            return error;
        }
        @ExceptionHandler(NullPointerException.class)
        public ResponseForException handleNullPointerException(NullPointerException nullPointerException){

            ResponseForException error = new ResponseForException();
            error.setErrorMessage(nullPointerException.getMessage());
            //error.setRequestURI(request.getRequestURI());

            return error;
        }
    }


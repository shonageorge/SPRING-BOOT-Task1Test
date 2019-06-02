package com.stackroute.muzix.exception;

public class TrackAlreadyExistException extends Exception{            //Exception class for track already exist exception
    private String message;

    public TrackAlreadyExistException() {
    }

    public TrackAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
}

package com.stackroute.muzix.exception;

public class TrackNotFoundException extends Exception {                           //Exception class for track not found exception
    private String message;

    public TrackNotFoundException(){}

    public TrackNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}

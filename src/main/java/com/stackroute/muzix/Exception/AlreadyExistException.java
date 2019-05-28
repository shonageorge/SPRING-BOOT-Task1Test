package com.stackroute.muzix.Exception;

public class AlreadyExistException extends Exception{
    private String message;

    public AlreadyExistException() {
    }

    public AlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
}

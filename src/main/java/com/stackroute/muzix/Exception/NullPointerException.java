package com.stackroute.muzix.Exception;

public class NullPointerException extends Exception {
    private String message;

    public NullPointerException(){}

    public NullPointerException(String message) {
        super(message);
        this.message = message;
    }
}

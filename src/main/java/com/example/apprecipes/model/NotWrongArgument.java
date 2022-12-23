package com.example.apprecipes.model;

public class NotWrongArgument extends Exception{
    public NotWrongArgument() {
    }

    public NotWrongArgument(String message) {
        super(message);
    }

    public NotWrongArgument(String message, Throwable cause) {
        super(message, cause);
    }

    public NotWrongArgument(Throwable cause) {
        super(cause);
    }

    public NotWrongArgument(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

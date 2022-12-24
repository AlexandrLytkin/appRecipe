package com.example.apprecipes.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotWrongArgument extends RuntimeException{

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

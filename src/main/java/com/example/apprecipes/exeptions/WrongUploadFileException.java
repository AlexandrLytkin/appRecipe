package com.example.apprecipes.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WrongUploadFileException extends RuntimeException{

    public WrongUploadFileException() {
    }

    public WrongUploadFileException(String message) {
        super(message);
    }

    public WrongUploadFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongUploadFileException(Throwable cause) {
        super(cause);
    }

    public WrongUploadFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

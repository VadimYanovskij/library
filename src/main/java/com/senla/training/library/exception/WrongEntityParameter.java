package com.senla.training.library.exception;

public class WrongEntityParameter extends RuntimeException{

    public WrongEntityParameter() {
    }

    public WrongEntityParameter(String message) {
        super(message);
    }

    public WrongEntityParameter(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongEntityParameter(Throwable cause) {
        super(cause);
    }

    public WrongEntityParameter(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

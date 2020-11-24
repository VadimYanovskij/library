package com.senla.training.library.exception;

public class EntityAlreadyDeleted extends RuntimeException{

    public EntityAlreadyDeleted() {
    }

    public EntityAlreadyDeleted(String message) {
        super(message);
    }

    public EntityAlreadyDeleted(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityAlreadyDeleted(Throwable cause) {
        super(cause);
    }

    public EntityAlreadyDeleted(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

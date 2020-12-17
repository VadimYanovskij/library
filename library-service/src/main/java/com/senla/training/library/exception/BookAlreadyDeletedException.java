package com.senla.training.library.exception;

/**
 * Indicates a deleted book.
 *
 * @author Vadim Yanovskij
 */
public class BookAlreadyDeletedException extends RuntimeException{

    public BookAlreadyDeletedException() {
    }

    public BookAlreadyDeletedException(String message) {
        super(message);
    }

    public BookAlreadyDeletedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookAlreadyDeletedException(Throwable cause) {
        super(cause);
    }

    public BookAlreadyDeletedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

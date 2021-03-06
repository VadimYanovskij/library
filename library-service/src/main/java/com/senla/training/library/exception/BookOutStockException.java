package com.senla.training.library.exception;

/**
 * Indicates a out of stock book.
 *
 * @author Vadim Yanovskij
 */
public class BookOutStockException extends RuntimeException{
    public BookOutStockException() {
    }

    public BookOutStockException(String message) {
        super(message);
    }

    public BookOutStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookOutStockException(Throwable cause) {
        super(cause);
    }

    public BookOutStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

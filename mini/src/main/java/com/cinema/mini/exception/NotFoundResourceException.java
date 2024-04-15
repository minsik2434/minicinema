package com.cinema.mini.exception;

public class NotFoundResourceException extends RuntimeException{
    public NotFoundResourceException() {
        super();
    }

    public NotFoundResourceException(String message) {
        super(message);
    }

    public NotFoundResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundResourceException(Throwable cause) {
        super(cause);
    }
}

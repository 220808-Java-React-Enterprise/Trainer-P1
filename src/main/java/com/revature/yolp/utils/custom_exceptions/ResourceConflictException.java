package com.revature.yolp.utils.custom_exceptions;

public class ResourceConflictException extends RuntimeException {
    public ResourceConflictException() {
    }

    public ResourceConflictException(String message) {
        super(message);
    }

    public ResourceConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceConflictException(Throwable cause) {
        super(cause);
    }

    public ResourceConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

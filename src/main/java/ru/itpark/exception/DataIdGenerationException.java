package ru.itpark.exception;

public class DataIdGenerationException extends RuntimeException {
    public DataIdGenerationException() {
        super();
    }

    public DataIdGenerationException(String message) {
        super(message);
    }

    public DataIdGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataIdGenerationException(Throwable cause) {
        super(cause);
    }

    protected DataIdGenerationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

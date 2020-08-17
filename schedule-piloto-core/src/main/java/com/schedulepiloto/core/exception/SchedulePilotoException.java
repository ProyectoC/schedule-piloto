package com.schedulepiloto.core.exception;

public class SchedulePilotoException extends Exception {

    private ExceptionCode error;
    private String message;

    public SchedulePilotoException(ExceptionCode error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public SchedulePilotoException(ExceptionCode error, Throwable cause) {
        super(cause);
        this.error = error;
    }

    public SchedulePilotoException(Throwable cause) {
        super(cause);
    }

    public SchedulePilotoException(String message) {
        super(message);
        this.message = message;
    }

    public ExceptionCode getError() {
        return error;
    }

    public void setError(ExceptionCode error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

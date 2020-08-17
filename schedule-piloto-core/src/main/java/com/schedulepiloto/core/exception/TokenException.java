package com.schedulepiloto.core.exception;

public class TokenException extends Exception {

    private ExceptionCode error;

    public TokenException(ExceptionCode error, String messageInternal, Throwable cause) {
        super(messageInternal, cause);
        this.error = error;
    }

    public TokenException(String messageInternal, Throwable cause) {
        super(messageInternal, cause);
        this.error = ExceptionCode.ERROR_TOKEN_COMPANY;
    }

    public TokenException(String messageInternal) {
        super(messageInternal);
        this.error = ExceptionCode.ERROR_TOKEN_COMPANY;
    }

    public ExceptionCode getError() {
        return error;
    }

    public void setError(ExceptionCode error) {
        this.error = error;
    }
}

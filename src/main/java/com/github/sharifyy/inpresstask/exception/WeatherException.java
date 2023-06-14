package com.github.sharifyy.inpresstask.exception;

public class WeatherException extends RuntimeException{


    public enum ErrorCode {
        GENERAL,
        NOT_AUTHORIZED,
        CITY_NOT_FOUND
    }

    private final ErrorCode code;
    private final Object[] params;

    private final int statusCode;

    public WeatherException(ErrorCode code, int statusCode, Object... params) {
        this.code = code;
        this.statusCode = statusCode;
        this.params = params;
    }

    public WeatherException(String message, ErrorCode code, int statusCode, Object... params) {
        super(message);
        this.code = code;
        this.statusCode = statusCode;
        this.params = params;
    }

    public WeatherException(String message, Throwable cause, ErrorCode code, int statusCode, Object... params) {
        super(message, cause);
        this.code = code;
        this.statusCode = statusCode;
        this.params = params;
    }

    public WeatherException(Throwable cause, ErrorCode code, int statusCode, Object... params) {
        super(cause);
        this.code = code;
        this.statusCode = statusCode;
        this.params = params;
    }

    public WeatherException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode code, int statusCode, Object... params) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.statusCode = statusCode;
        this.params = params;
    }

    public ErrorCode getCode() {
        return code;
    }

    public Object[] getParams() {
        return params;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

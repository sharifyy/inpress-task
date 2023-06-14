package com.github.sharifyy.inpresstask.feign;

public class OpenWeatherApiFeignException extends RuntimeException {

    private final int statusCode;
    private final String errorMessage;

    public OpenWeatherApiFeignException(int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getMessage() {
        return "OpenWeatherApiFeignException - Status: " + statusCode + ", Message: " + errorMessage;
    }
}

package com.github.sharifyy.inpresstask.feign;

import com.github.sharifyy.inpresstask.exception.WeatherException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class OpenWeatherApiFeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return new WeatherException(getErrorCode(response.status()),response.status());
    }

    private  WeatherException.ErrorCode getErrorCode(int status) {
        return switch (status){
            case 401 -> WeatherException.ErrorCode.NOT_AUTHORIZED;
            case 404 -> WeatherException.ErrorCode.CITY_NOT_FOUND;
            default -> WeatherException.ErrorCode.GENERAL;
        };
    }
}

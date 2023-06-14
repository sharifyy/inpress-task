package com.github.sharifyy.inpresstask.feign;

import com.github.sharifyy.inpresstask.exception.WeatherException;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenWeatherApiFeignErrorDecoderTest {

    private OpenWeatherApiFeignErrorDecoder errorDecoder;
    private Request request;

    @BeforeEach
    public void setup() {
        errorDecoder = new OpenWeatherApiFeignErrorDecoder();
        request =  Request.create(Request.HttpMethod.GET,"fakeurl", Collections.emptyMap(), null,Charset.defaultCharset());
    }

    @Test
    public void testDecode_401_ReturnsNotAuthorizedException() {
        int status = 401;
        Response response = Response.builder().status(status).request(request).build();

        Exception exception = errorDecoder.decode("methodKey", response);

        assertTrue(exception instanceof WeatherException);
        assertEquals(WeatherException.ErrorCode.NOT_AUTHORIZED, ((WeatherException) exception).getCode());
        assertEquals(status, ((WeatherException) exception).getStatusCode());
    }

    @Test
    public void testDecode_404_ReturnsCityNotFoundException() {
        // given
        int status = 404;
        Response response = Response.builder().status(status).request(request).build();

        // when
        Exception exception = errorDecoder.decode("methodKey", response);

        // then
        assertTrue(exception instanceof WeatherException);
        assertEquals(WeatherException.ErrorCode.CITY_NOT_FOUND, ((WeatherException) exception).getCode());
        assertEquals(status, ((WeatherException) exception).getStatusCode());
    }

    @Test
    public void testDecode_OtherStatusCodes_ReturnsGeneralException() {
        // given
        int status = 500;
        Response response = Response.builder().status(status).request(request).build();

        // when
        Exception exception = errorDecoder.decode("methodKey", response);

        // then
        assertTrue(exception instanceof WeatherException);
        assertEquals(WeatherException.ErrorCode.GENERAL, ((WeatherException) exception).getCode());
        assertEquals(status, ((WeatherException) exception).getStatusCode());
    }
}

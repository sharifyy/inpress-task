package com.github.sharifyy.inpresstask.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class GlobalExceptionHandlerTest {


    @Autowired
    private GlobalExceptionHandler exceptionHandler;
    @Autowired
    MessageSource messageSource;



    @Test
    public void testGeneralExceptionHandler() {
        //given
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

        Exception exception = new Exception("Some error message");
        String message = messageSource.getMessage(WeatherException.ErrorCode.GENERAL.name(), null, httpServletRequest.getLocale());

        // when
        when(httpServletRequest.getLocale()).thenReturn(Locale.ENGLISH);
        when(httpServletRequest.getServletPath()).thenReturn("/api/v1/weather");

        ResponseEntity<WeatherExceptionDTO> response = exceptionHandler.general(exception, httpServletRequest);

        // then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(message, response.getBody().message());

    }

    @Test
    public void testWeatherException() {
        //given
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);

        WeatherException exception = new WeatherException(WeatherException.ErrorCode.CITY_NOT_FOUND,404);
        String message = messageSource.getMessage(WeatherException.ErrorCode.CITY_NOT_FOUND.name(), null, httpServletRequest.getLocale());

        // when
        when(httpServletRequest.getLocale()).thenReturn(Locale.ENGLISH);
        when(httpServletRequest.getServletPath()).thenReturn("/api/v1/weather");

        ResponseEntity<WeatherExceptionDTO> response = exceptionHandler.weatherException(exception, httpServletRequest);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(message, response.getBody().message());

    }


}
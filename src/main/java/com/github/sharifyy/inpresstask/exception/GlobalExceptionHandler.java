package com.github.sharifyy.inpresstask.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WeatherExceptionDTO> general(Exception e, HttpServletRequest httpServletRequest){
        logger.error(e.getMessage(),e);
        String message = messageSource.getMessage(WeatherException.ErrorCode.GENERAL.name(), null, httpServletRequest.getLocale());
        return ResponseEntity.status(500).body(new WeatherExceptionDTO(LocalDateTime.now(),httpServletRequest.getServletPath(),500,message));
    }

    @ExceptionHandler(WeatherException.class)
    public ResponseEntity<WeatherExceptionDTO> weatherException(WeatherException e, HttpServletRequest httpServletRequest){
        logger.error(e.getMessage(),e);
        String message = messageSource.getMessage(e.getCode().name(), e.getParams(), httpServletRequest.getLocale());
        return ResponseEntity.status(e.getStatusCode()).body(new WeatherExceptionDTO(LocalDateTime.now(),httpServletRequest.getServletPath(),e.getStatusCode(),message));
    }
}

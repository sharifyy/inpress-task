package com.github.sharifyy.inpresstask.exception;

import java.time.LocalDateTime;

public record WeatherExceptionDTO(LocalDateTime dateTime, String path,int code,String message) {
}

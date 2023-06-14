package com.github.sharifyy.inpresstask.weather.dto;

public record WeatherDataMetrics(
        double temperature,
        double feelsLike,
        double minTemperature,
        double maxTemperature,
        int pressure,
        int humidity
) {
}

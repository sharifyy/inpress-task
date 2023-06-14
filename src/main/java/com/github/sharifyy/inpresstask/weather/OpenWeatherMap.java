package com.github.sharifyy.inpresstask.weather;

import com.github.sharifyy.inpresstask.weather.dto.openweather.ForecastData;
import com.github.sharifyy.inpresstask.weather.dto.openweather.WeatherData;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OpenWeatherMap",url = "https://api.openweathermap.org/data/2.5")
public interface OpenWeatherMap {

    @Retryable(retryFor = { FeignException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    @GetMapping("/weather")
    WeatherData currentWeather(@RequestParam(name = "q") String q);

    @GetMapping("/forecast")
    ForecastData forecastWeather(@RequestParam(name = "q") String q);
}

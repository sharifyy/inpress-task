package com.github.sharifyy.inpresstask.weather;

import com.github.sharifyy.inpresstask.weather.dto.CurrentWeatherResponse;
import com.github.sharifyy.inpresstask.weather.dto.ForecastResponse;
import com.github.sharifyy.inpresstask.weather.dto.LocationRequestDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public CurrentWeatherResponse getCurrentWeather(@Valid LocationRequestDTO locationRequestDTO) {
        logger.info("Get current weather for {},{}", locationRequestDTO.getCity(), locationRequestDTO.getCountryCode());
        return weatherService.currentWeatherFor(locationRequestDTO);
    }

    @GetMapping("/forecast")
    public ForecastResponse getForecast(@Valid LocationRequestDTO locationRequestDTO) {
        logger.info("Get current weather for {},{}", locationRequestDTO.getCity(), locationRequestDTO.getCountryCode());
        return weatherService.forecast(locationRequestDTO);
    }


}

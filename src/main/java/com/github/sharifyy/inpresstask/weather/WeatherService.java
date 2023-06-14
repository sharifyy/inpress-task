package com.github.sharifyy.inpresstask.weather;

import com.github.sharifyy.inpresstask.weather.dto.CurrentWeatherResponse;
import com.github.sharifyy.inpresstask.weather.dto.ForecastResponse;
import com.github.sharifyy.inpresstask.weather.dto.openweather.ForecastData;
import com.github.sharifyy.inpresstask.weather.dto.LocationRequestDTO;
import com.github.sharifyy.inpresstask.weather.dto.openweather.WeatherData;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final OpenWeatherMap openWeatherMap;

    public WeatherService(OpenWeatherMap openWeatherMap) {
        this.openWeatherMap = openWeatherMap;
    }

    public CurrentWeatherResponse currentWeatherFor(@Valid LocationRequestDTO locationRequestDTO) {
        logger.debug("calling openWeatherApi for current weather");
        WeatherData weatherData = openWeatherMap.currentWeather(locationRequestDTO.getCity() + "," + locationRequestDTO.getCountryCode());
        return new CurrentWeatherResponse().buildWith(weatherData);
    }

    public ForecastResponse forecast(LocationRequestDTO locationRequestDTO) {
        logger.debug("calling openWeatherApi for forecast weather");
        ForecastData forecastData = openWeatherMap.forecastWeather(locationRequestDTO.getCity() + "," + locationRequestDTO.getCountryCode());
        return new ForecastResponse().from(forecastData);
    }
}

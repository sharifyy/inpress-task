package com.github.sharifyy.inpresstask.weather.dto;

import com.github.sharifyy.inpresstask.weather.dto.openweather.Clouds;
import com.github.sharifyy.inpresstask.weather.dto.openweather.Rain;
import com.github.sharifyy.inpresstask.weather.dto.openweather.*;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentWeatherResponseTest {

    @Test
    public void testBuildWith() {
        // given
        WeatherData weatherData = new WeatherData();
        weatherData.setBase("base");
        weatherData.setName("city");
        Clouds clouds = new Clouds();
        clouds.setAll(50);
        weatherData.setClouds(clouds);
        Coord coord = new Coord();
        coord.setLat(12.34);
        coord.setLon(56.78);
        weatherData.setCoord(coord);
        weatherData.setDt(1234567890L);
        weatherData.setVisibility(1000);
        Main main = new Main();
        main.setTemp(25.5);
        main.setFeels_like(23.4);
        main.setTemp_min(20.1);
        main.setTemp_max(28.9);
        main.setHumidity(70);
        main.setPressure(1015);
        weatherData.setMain(main);
        weatherData.setTimezone(7200);
        Weather weather = new Weather();
        weather.setMain("Rain");
        weather.setDescription("light rain");
        weather.setIcon("10d");
        List<Weather> weatherList = List.of(weather);
        weatherData.setWeather(weatherList);
        var wind = new com.github.sharifyy.inpresstask.weather.dto.openweather.Wind();
        wind.setSpeed(5.6);
        wind.setDeg(180);
        wind.setGust(7.8);
        weatherData.setWind(wind);
        Rain rain = new Rain();
        rain.setH1(2.1);
        weatherData.setRain(rain);

        CurrentWeatherResponse response = new CurrentWeatherResponse();

        // when
        response.buildWith(weatherData);

        // then
        assertEquals("base", response.getBase());
        assertEquals("city", response.getCity());
        assertEquals(50, response.getClouds().all());
        assertEquals(12.34, response.getCoordinates().latitude());
        assertEquals(56.78, response.getCoordinates().longitude());
        assertEquals(Instant.ofEpochMilli(1234567890L), response.getDatetime());
        assertEquals(1000, response.getVisibility());
        assertEquals(25.5, response.getWeatherDataMetrics().temperature());
        assertEquals(23.4, response.getWeatherDataMetrics().feelsLike());
        assertEquals(20.1, response.getWeatherDataMetrics().minTemperature());
        assertEquals(28.9, response.getWeatherDataMetrics().maxTemperature());
        assertEquals(1015, response.getWeatherDataMetrics().pressure());
        assertEquals(70, response.getWeatherDataMetrics().humidity());
        assertEquals(7200, response.getTimezoneOffsetInSeconds());
        assertEquals(1, response.getWeather().size());
        assertEquals("Rain", response.getWeather().get(0).status());
        assertEquals("light rain", response.getWeather().get(0).description());
        assertEquals("10d", response.getWeather().get(0).icon());
        assertEquals(5.6, response.getWind().speed());
        assertEquals(180, response.getWind().deg());
        assertEquals(7.8, response.getWind().gust());
        assertEquals(2.1, response.getRain().precipitation());
    }
}

package com.github.sharifyy.inpresstask.weather.dto;

import com.github.sharifyy.inpresstask.weather.dto.openweather.ForecastData;
import com.github.sharifyy.inpresstask.weather.dto.openweather.Main;
import com.github.sharifyy.inpresstask.weather.dto.openweather.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ForecastDataMetricTest {

    @Test
    public void testFrom() {
        // given
        ForecastData.ListData listData = new ForecastData.ListData();
        listData.setDt(1234567890L);
        Weather weather = new Weather();
        weather.setMain("Rain");
        weather.setDescription("light rain");
        weather.setIcon("10d");
        listData.setWeather(List.of(weather));
        com.github.sharifyy.inpresstask.weather.dto.openweather.Clouds clouds = new com.github.sharifyy.inpresstask.weather.dto.openweather.Clouds();
        clouds.setAll(50);
        listData.setClouds(clouds);
        var rain = new com.github.sharifyy.inpresstask.weather.dto.openweather.Rain();
        rain.setH3(2.1);
        listData.setRain(rain);
        listData.setVisibility(1000);
        var wind = new com.github.sharifyy.inpresstask.weather.dto.openweather.Wind();
        wind.setSpeed(5.6);
        wind.setDeg(180);
        wind.setGust(7.8);
        listData.setWind(wind);
        Main main = new Main();
        main.setTemp(25.5);
        main.setFeels_like(23.4);
        main.setTemp_min(20.1);
        main.setTemp_max(28.9);
        main.setHumidity(70);
        main.setPressure(1015);
        listData.setMain(main);

        ForecastDataMetric forecastDataMetric = new ForecastDataMetric();

        // when
        forecastDataMetric.from(listData);

        // then
        Assertions.assertEquals(1234567890L, forecastDataMetric.getDateTime().toEpochMilli());
        Assertions.assertEquals(1, forecastDataMetric.getWeather().size());
        Assertions.assertEquals("Rain", forecastDataMetric.getWeather().get(0).status());
        Assertions.assertEquals("light rain", forecastDataMetric.getWeather().get(0).description());
        Assertions.assertEquals("10d", forecastDataMetric.getWeather().get(0).icon());
        Assertions.assertEquals(50, forecastDataMetric.getClouds().all());
        Assertions.assertEquals(2.1, forecastDataMetric.getRain().precipitation());
        Assertions.assertEquals(1000, forecastDataMetric.getVisibility());
        Assertions.assertEquals(5.6, forecastDataMetric.getWind().speed());
        Assertions.assertEquals(180, forecastDataMetric.getWind().deg());
        Assertions.assertEquals(7.8, forecastDataMetric.getWind().gust());
        Assertions.assertEquals(25.5, forecastDataMetric.getWeatherDataMetrics().temperature());
        Assertions.assertEquals(23.4, forecastDataMetric.getWeatherDataMetrics().feelsLike());
        Assertions.assertEquals(20.1, forecastDataMetric.getWeatherDataMetrics().minTemperature());
        Assertions.assertEquals(28.9, forecastDataMetric.getWeatherDataMetrics().maxTemperature());
        Assertions.assertEquals(1015, forecastDataMetric.getWeatherDataMetrics().pressure());
        Assertions.assertEquals(70, forecastDataMetric.getWeatherDataMetrics().humidity());
    }
}

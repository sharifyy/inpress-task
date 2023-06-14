package com.github.sharifyy.inpresstask.weather.dto;

import com.github.sharifyy.inpresstask.weather.dto.openweather.ForecastData;
import com.github.sharifyy.inpresstask.weather.dto.openweather.Main;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ForecastDataMetric {
    private Instant dateTime;
    private WeatherDataMetrics weatherDataMetrics;
    private List<WeatherStatus> weather;
    private Clouds clouds;
    private Wind wind;
    private int visibility;
    private Rain rain;


    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public WeatherDataMetrics getWeatherDataMetrics() {
        return weatherDataMetrics;
    }

    public void setWeatherDataMetrics(WeatherDataMetrics weatherDataMetrics) {
        this.weatherDataMetrics = weatherDataMetrics;
    }

    public List<WeatherStatus> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherStatus> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }



    public ForecastDataMetric from(ForecastData.ListData data){
        this.setDateTime(Instant.ofEpochMilli(data.getDt()));
        this.setWeather(data.getWeather().stream().map(w->new WeatherStatus(w.getMain(),w.getDescription(),w.getIcon())).collect(Collectors.toList()));
        this.setClouds(new Clouds(data.getClouds().getAll()));
        this.setRain(new Rain(Optional.ofNullable(data.getRain()).map(r->r.getH3()).orElse(0.0)));
        this.setVisibility(data.getVisibility());
        this.setWind(new Wind(data.getWind().getSpeed(),data.getWind().getDeg(),data.getWind().getGust()));
        this.setWeatherDataMetrics(mapToWeatherDataMetrics(data.getMain()));
        return this;
    }

    private WeatherDataMetrics mapToWeatherDataMetrics(Main main) {
        return new WeatherDataMetrics(
                main.getTemp(),
                main.getFeels_like(),
                main.getTemp_min(),
                main.getTemp_max(),
                main.getPressure(),
                main.getHumidity()
        );
    }
}

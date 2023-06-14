package com.github.sharifyy.inpresstask.weather.dto;

import com.github.sharifyy.inpresstask.weather.dto.openweather.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CurrentWeatherResponse {

    private Coordinates coordinates;
    private List<WeatherStatus> weather;
    private String base;
    private WeatherDataMetrics weatherDataMetrics;
    private int visibility;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
    private Instant datetime;
    private int timezoneOffsetInSeconds;
    private String city;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<WeatherStatus> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherStatus> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public WeatherDataMetrics getWeatherDataMetrics() {
        return weatherDataMetrics;
    }

    public void setWeatherDataMetrics(WeatherDataMetrics weatherDataMetrics) {
        this.weatherDataMetrics = weatherDataMetrics;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Instant getDatetime() {
        return datetime;
    }

    public void setDatetime(Instant datetime) {
        this.datetime = datetime;
    }

    public int getTimezoneOffsetInSeconds() {
        return timezoneOffsetInSeconds;
    }

    public void setTimezoneOffsetInSeconds(int timezoneOffsetInSeconds) {
        this.timezoneOffsetInSeconds = timezoneOffsetInSeconds;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CurrentWeatherResponse buildWith(WeatherData weatherData){
        this.setBase(weatherData.getBase());
        this.setCity(weatherData.getName());
        this.setClouds(new Clouds(weatherData.getClouds().getAll()));
        this.setCoordinates(new Coordinates(weatherData.getCoord().getLat(),weatherData.getCoord().getLon()));
        this.setDatetime(Instant.ofEpochMilli(weatherData.getDt()));
        this.setVisibility(weatherData.getVisibility());
        this.setWeatherDataMetrics(mapToWeatherDataMetrics(weatherData.getMain()));
        this.setTimezoneOffsetInSeconds(weatherData.getTimezone());
        this.setWeather(weatherData.getWeather().stream()
                .map(weather -> new WeatherStatus(weather.getMain(),weather.getDescription(),weather.getIcon()))
                .collect(Collectors.toList()));
        this.setWind(new Wind(weatherData.getWind().getSpeed(),weatherData.getWind().getDeg(),weatherData.getWind().getGust()));
        this.setRain(new Rain(Optional.ofNullable(weatherData.getRain()).map(r->r.getH1()).orElse((double) 0)));
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

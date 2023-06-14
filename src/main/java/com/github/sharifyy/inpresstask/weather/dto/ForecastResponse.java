package com.github.sharifyy.inpresstask.weather.dto;

import com.github.sharifyy.inpresstask.weather.dto.openweather.ForecastData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ForecastResponse {
    private String city;
    private Coordinates coordinates;
    List<ForecastDataMetric> forecastDataMetrics = new ArrayList<>();
    private int timezoneOffsetInSeconds;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<ForecastDataMetric> getForecastDataMetrics() {
        return forecastDataMetrics;
    }

    public void setForecastDataMetrics(List<ForecastDataMetric> forecastDataMetrics) {
        this.forecastDataMetrics = forecastDataMetrics;
    }

    public int getTimezoneOffsetInSeconds() {
        return timezoneOffsetInSeconds;
    }

    public void setTimezoneOffsetInSeconds(int timezoneOffsetInSeconds) {
        this.timezoneOffsetInSeconds = timezoneOffsetInSeconds;
    }
    public ForecastResponse from(ForecastData data){
        this.setCity(data.getCity().getName());
        this.setCoordinates(new Coordinates(data.getCity().getCoord().getLat(),data.getCity().getCoord().getLon()));
        this.setForecastDataMetrics(
                data.getList().stream()
                        .map(d->new ForecastDataMetric().from(d))
                        .collect(Collectors.toList())
        );
        this.setTimezoneOffsetInSeconds(data.getCity().getTimezone());
        return this;
    }
}

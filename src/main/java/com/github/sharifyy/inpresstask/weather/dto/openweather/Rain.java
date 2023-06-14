package com.github.sharifyy.inpresstask.weather.dto.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rain {
    @JsonProperty("1h")
    private double h1;

    @JsonProperty("3h")
    private double h3;

    public double getH1() {
        return h1;
    }

    public void setH1(double h1) {
        this.h1 = h1;
    }

    public double getH3() {
        return h3;
    }

    public void setH3(double h3) {
        this.h3 = h3;
    }
}

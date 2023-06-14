package com.github.sharifyy.inpresstask.weather.dto;

import jakarta.validation.constraints.NotBlank;

public class LocationRequestDTO {

    @NotBlank(message = "city must be provided")
    private String city;
    @NotBlank(message = "country code must be provided")
    private String countryCode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


}

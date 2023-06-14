package com.github.sharifyy.inpresstask.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCurrentWeather() throws Exception {
        mockMvc.perform(get("/api/v1/weather")
                        .param("city", "zocca")
                        .param("countryCode", "IT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.coordinates.latitude").value(44.3473))
                .andExpect(jsonPath("$.coordinates.longitude").value(10.9904))
                .andExpect(jsonPath("$.city").value("Zocca"))
                .andExpect(jsonPath("$.weather").isArray())
                .andExpect(jsonPath("$.weather", hasSize(greaterThan(0))));
    }

    @Test
    public void testGetCurrentWeatherForBadSpelledCityThrowsNotFoundException() throws Exception {
        mockMvc.perform(get("/api/v1/weather")
                        .param("city", "hopefully there is no city with this name")
                        .param("countryCode", "IT"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.message").value("city name not found"));
    }

    @Test
    public void testGetForecast() throws Exception {
        mockMvc.perform(get("/api/v1/weather/forecast")
                        .param("city", "zocca")
                        .param("countryCode", "IT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.coordinates.latitude").value(44.3473))
                .andExpect(jsonPath("$.coordinates.longitude").value(10.9904))
                .andExpect(jsonPath("$.city").value("Zocca"))
                .andExpect(jsonPath("$.forecastDataMetrics").isArray())
                .andExpect(jsonPath("$.forecastDataMetrics", hasSize(greaterThan(0))));
    }

    @Test
    public void testGetForecastForBadSpelledCityThrowsNotFoundException() throws Exception {
        mockMvc.perform(get("/api/v1/weather")
                        .param("city", "hopefully there is no city with this name")
                        .param("countryCode", "IT"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.message").value("city name not found"));
    }

}
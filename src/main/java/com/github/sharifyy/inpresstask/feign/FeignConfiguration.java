package com.github.sharifyy.inpresstask.feign;

import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FeignConfiguration {

    @Value(value = "${openweathermap.apikey}")
    private String apiKey;
    private static final String APPID = "appid";

    @Bean
    public RequestInterceptor apiKeyInterceptor() {
        return requestTemplate -> requestTemplate.query(APPID, apiKey);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new OpenWeatherApiFeignErrorDecoder();
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(3, 3000, 1000);
    }

    @Bean
    @Profile("dev")
    Logger.Level feignLoggerLevelDev() {
        return Logger.Level.FULL;
    }

    @Bean
    @Profile("prod")
    Logger.Level feignLoggerLevelProd() {
        return Logger.Level.BASIC;
    }
}
package com.example.simonpearson.weatherapp;

import com.example.simonpearson.weatherapp.Entities.ForecastEntity;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by simon.pearson on 1/13/2015.
 */
public class ForecastService
{
    public static ForecastEntity GetWeatherForecast(String city, String countryCode)
    {
        final String url = Repositories.FormatForecastUrl(city, countryCode);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate.getForObject(url, ForecastEntity.class);
    }
}

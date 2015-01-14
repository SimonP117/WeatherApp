package com.example.simonpearson.weatherapp;

import com.example.simonpearson.weatherapp.Entities.WeatherEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by simon.pearson on 1/8/2015.
 */

public class WeatherService
{
    public static WeatherEntity GetWeather(String city, String countryCode)
    {
        final String url = Repositories.FormatWeatherUrl(city, countryCode);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate.getForObject(url, WeatherEntity.class);
    }
}
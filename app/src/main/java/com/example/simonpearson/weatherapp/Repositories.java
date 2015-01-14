package com.example.simonpearson.weatherapp;

/**
 * Created by simon.pearson on 1/12/2015.
 */
public class Repositories
{
    private static String weatherRepository = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String forecastRepository = "http://api.openweathermap.org/data/2.5/forecast/daily?q=";

    public static String FormatWeatherUrl(String city, String countryCode)
    {
        return weatherRepository + city + "," + countryCode;
    }

    public static String FormatForecastUrl(String city, String countryCode)
    {
        return forecastRepository + city + "," + countryCode;
    }
}

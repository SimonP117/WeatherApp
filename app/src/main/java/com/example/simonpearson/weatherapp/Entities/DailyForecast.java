package com.example.simonpearson.weatherapp.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by simon.pearson on 1/13/2015.
 */
@JsonIgnoreProperties({ "dt", "pressure", "humidity", "speed", "deg","clouds","rain","snow"})
public class DailyForecast
{
    public Temperature getTemp() {
        return temp;
    }

    public MainWeather [] getWeather() {
        return weather;
    }

    private Temperature temp;
    private MainWeather[] weather;
}

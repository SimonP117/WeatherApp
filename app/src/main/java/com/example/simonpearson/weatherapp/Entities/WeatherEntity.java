package com.example.simonpearson.weatherapp.Entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Created by simon.pearson on 1/8/2015.
 */
@JsonIgnoreProperties({ "sys", "base", "wind", "snow", "clouds","dt", "id", "cod" })
public class WeatherEntity
{
    @JsonProperty("coord")
    private Coordinates coordinates;
    @JsonProperty("weather")
    private MainWeather[] mainWeather;
    @JsonProperty("main")
    private WeatherMetrics weatherMetrics;

    @JsonProperty("name")
    private String cityName;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public MainWeather[] getMainWeather() {
        return mainWeather;
    }

    public WeatherMetrics getWeatherMetrics() {
        return weatherMetrics;
    }

    public String getCityName() {
        return cityName;
    }
}

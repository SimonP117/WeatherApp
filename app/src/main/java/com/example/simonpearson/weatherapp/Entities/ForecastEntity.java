package com.example.simonpearson.weatherapp.Entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by simon.pearson on 1/13/2015.
 */
@JsonIgnoreProperties({ "cod", "message", "city", "cnt", "clouds" })
public class ForecastEntity
{
    public DailyForecast[] getDailyForecasts() {
        return dailyForecasts;
    }

    @JsonProperty("list")
    private DailyForecast[] dailyForecasts;
}

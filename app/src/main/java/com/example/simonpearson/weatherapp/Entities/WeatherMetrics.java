package com.example.simonpearson.weatherapp.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
/**
 * Created by simon.pearson on 1/8/2015.
 */
public class WeatherMetrics {
    private int temp;
    private float humidity;
    private float pressure;
    private int temp_min;
    private int temp_max;

    public int getTemp() {
        return temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public int getTemp_min() {
        return temp_min;
    }

    public int getTemp_max() {
        return temp_max;
    }
}

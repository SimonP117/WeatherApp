package com.example.simonpearson.weatherapp.Entities;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by simon.pearson on 1/8/2015.
 */
public class MainWeather {
    private int id;
    private String main;
    private String description;
    private String icon;

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}

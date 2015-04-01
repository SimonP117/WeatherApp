package com.example.simonpearson.weatherapp.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by simon.pearson on 1/20/2015.
 */
@JsonIgnoreProperties({ "coord", "population", "id", "sys" })
public class City
{
    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    private String name;
    private String country;
}

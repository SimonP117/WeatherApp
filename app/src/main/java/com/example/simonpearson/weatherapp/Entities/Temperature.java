package com.example.simonpearson.weatherapp.Entities;

/**
 * Created by simon.pearson on 1/13/2015.
 */
public class Temperature
{
    private int day;

    public int getMin() {
        return min;
    }

    public int getDay() {
        return day;
    }

    public int getMax() {
        return max;
    }

    public int getNight() {
        return night;
    }

    public int getEve() {
        return eve;
    }

    public int getMorn() {
        return morn;
    }

    private int min;
    private int max;
    private int night;
    private int eve;
    private int morn;
}

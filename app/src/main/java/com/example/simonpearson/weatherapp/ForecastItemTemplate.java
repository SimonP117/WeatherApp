package com.example.simonpearson.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.app.Activity;
import java.util.Calendar;

import com.example.simonpearson.weatherapp.Entities.DailyForecast;
import com.example.simonpearson.weatherapp.Entities.ForecastEntity;

/**
 * Created by simon.pearson on 1/14/2015.
 */
public class ForecastItemTemplate extends ArrayAdapter<DailyForecast>
{
    private Context mContext;
    private int layoutResourceId;
    private DailyForecast[] data = null;

    public ForecastItemTemplate(Context context, int resource, DailyForecast[] objects)
    {
        super(context, resource, objects);

        this.mContext = context;
        this.layoutResourceId = resource;
        this.data = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        DailyForecast forecast = data[position];

        TextView dayText = (TextView) convertView.findViewById(R.id.day_forecast_value);
        dayText.setText(getDayOfTheWeek(position));

        TextView descriptionText = (TextView) convertView.findViewById(R.id.description_forecast_value);
        descriptionText.setText(forecast.getWeather()[0].getDescription());

        TextView maxTempText = (TextView) convertView.findViewById(R.id.maxtemp_forecast_value);
        maxTempText.setText(Integer.toString(forecast.getTemp().getMax() - 273));

        TextView minTempText = (TextView) convertView.findViewById(R.id.mintemp_forecast_value);
        minTempText.setText(Integer.toString(forecast.getTemp().getMin() - 273));

        return convertView;
    }

    private String getDayOfTheWeek(int distanceFromToday) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (((day + distanceFromToday) % 7) + 1) {
            case Calendar.MONDAY:
                return "MON";
            case Calendar.TUESDAY:
                return "TUE";
            case Calendar.WEDNESDAY:
                return "WED";
            case Calendar.THURSDAY:
                return "THU";
            case Calendar.FRIDAY:
                return "FRI";
            case Calendar.SATURDAY:
                return "SAT";
            case Calendar.SUNDAY:
                return "SUN";
        }
        return "";
    }

}

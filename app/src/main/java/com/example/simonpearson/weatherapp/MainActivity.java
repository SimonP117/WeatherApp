package com.example.simonpearson.weatherapp;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.content.Context;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import java.util.Arrays;

import com.example.simonpearson.weatherapp.Entities.DailyForecast;
import com.example.simonpearson.weatherapp.Entities.ForecastEntity;
import com.example.simonpearson.weatherapp.Entities.WeatherEntity;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class MainActivity extends ActionBarActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        new WeatherController().execute();
        new ForecastController().execute();

        setContentView(R.layout.activity_main);
    }

    private class WeatherController extends AsyncTask<Void, Void, WeatherEntity>
    {
        @Override
        protected WeatherEntity doInBackground(Void... params)
        {
            return WeatherService.GetWeather("Montreal", "ca");

        }
        @Override
        protected void onPostExecute(WeatherEntity weatherEntity)
        {
            TextView temperatureText = (TextView) findViewById(R.id.temperature_value);
            temperatureText.setText(Integer.toString(weatherEntity.getWeatherMetrics().getTemp() - 273));

            TextView descriptionText = (TextView) findViewById(R.id.description_value);
            descriptionText.setText(weatherEntity.getMainWeather()[0].getDescription());

            TextView cityText = (TextView) findViewById(R.id.city_value);
            cityText.setText(weatherEntity.getCityName());

            TextView maxTemp = (TextView) findViewById(R.id.maxtemp_value);
            maxTemp.setText(Integer.toString(weatherEntity.getWeatherMetrics().getTemp_max() - 273));

            TextView minTemp = (TextView) findViewById(R.id.mintemp_value);
            minTemp.setText(Integer.toString(weatherEntity.getWeatherMetrics().getTemp_min() - 273));

            ImageView imageview = (ImageView)findViewById(R.id.weather_icon);
            imageview.setImageDrawable(getResources().getDrawable(R.drawable.sun_93));
        }
    }

    private class ForecastController extends AsyncTask<Void, Void, ForecastEntity>
    {
        @Override
        protected ForecastEntity doInBackground(Void... params)
        {
            return ForecastService.GetWeatherForecast("Montreal", "ca");
        }
        @Override
        protected void onPostExecute(ForecastEntity weatherEntity)
        {
            ListView forecastListview = (ListView)findViewById(R.id.forecast_listview);
            DailyForecast[] data = Arrays.copyOfRange(weatherEntity.getDailyForecasts(), 1, weatherEntity.getDailyForecasts().length);
            ForecastItemTemplate forecastAdapter = new ForecastItemTemplate(mContext, R.layout.forecast_adapter, data);
            forecastListview.setAdapter(forecastAdapter);
        }
    }
}

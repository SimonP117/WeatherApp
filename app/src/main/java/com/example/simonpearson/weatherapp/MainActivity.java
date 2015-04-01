package com.example.simonpearson.weatherapp;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.content.Context;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ImageView;
import android.support.v4.widget.SwipeRefreshLayout;

import java.io.InputStream;
import java.util.Arrays;

import com.example.simonpearson.weatherapp.Entities.DailyForecast;
import com.example.simonpearson.weatherapp.Entities.ForecastEntity;
import com.example.simonpearson.weatherapp.Entities.WeatherEntity;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class MainActivity extends ActionBarActivity {

    private Context mContext;
    private boolean isInErrorState = false;
    private boolean hasPageBeenLoaded = false;
    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        InitializeMainActivity();

        new WeatherController().execute();
        new ForecastController().execute();
    }

    private void InitializeMainActivity()
    {
        setContentView(R.layout.activity_main);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);
                ( new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        isInErrorState = false;
                        hasPageBeenLoaded = false;

                        ScrollView main_page = (ScrollView) findViewById(R.id.main_page);
                        main_page.setVisibility(View.VISIBLE);

                        ScrollView error_page = (ScrollView) findViewById(R.id.main_page);
                        error_page.setVisibility(View.INVISIBLE);

                        new WeatherController().execute();
                        new ForecastController().execute();

                    }
                }, 3000);
            }
        });
    }
    private class WeatherController extends AsyncTask<Void, Void, WeatherEntity>
    {
        @Override
        protected WeatherEntity doInBackground(Void... params)
        {
            try
            {
                return WeatherService.GetWeather("Montreal", "ca");
            }
            catch (Exception e)
            {
                return null;
            }
        }
        @Override
        protected void onPostExecute(WeatherEntity weatherEntity)
        {
            if(weatherEntity == null)
            {
                if (!isInErrorState)
                {
                    isInErrorState = true;
                    ScrollView main_page = (ScrollView) findViewById(R.id.main_page);
                    main_page.setVisibility(View.INVISIBLE);

                    ScrollView error_page = (ScrollView) findViewById(R.id.main_page);
                    error_page.setVisibility(View.VISIBLE);
                }
                return;
            }

            swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
            if(swipeLayout.isRefreshing())
            {
                swipeLayout.setRefreshing(true);
            }
//            if(!hasPageBeenLoaded)
//            {
//                InitializeMainActivity();
//                hasPageBeenLoaded = true;
//            }

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
            try
            {
                return ForecastService.GetWeatherForecast("Montreal", "ca");
            }
            catch (Exception e)
            {
                return null;
            }
        }
        @Override
        protected void onPostExecute(ForecastEntity weatherEntity)
        {
            if(weatherEntity == null)
            {
                if (!isInErrorState)
                {
                    isInErrorState = true;
                    ScrollView main_page = (ScrollView) findViewById(R.id.main_page);
                    main_page.setVisibility(View.INVISIBLE);

                    ScrollView error_page = (ScrollView) findViewById(R.id.main_page);
                    error_page.setVisibility(View.VISIBLE);
                }
                return;
            }

//           if(!hasPageBeenLoaded)
//           {
//               InitializeMainActivity();
//               hasPageBeenLoaded = true;
//           }

            ListView forecastListview = (ListView)findViewById(R.id.forecast_listview);
            DailyForecast[] data = Arrays.copyOfRange(weatherEntity.getDailyForecasts(), 1, weatherEntity.getDailyForecasts().length);

            ForecastItemTemplate forecastAdapter = new ForecastItemTemplate(mContext, R.layout.forecast_adapter, data);
            forecastListview.setAdapter(forecastAdapter);
        }
    }

//    private class MyGifView extends View
//    {
//        Movie movie;
//        InputStream inputStream = null;
//        long movieStart = 0;
//
//        public MyGifView(Context context)
//        {
//            super(context);
//            inputStream = context.getResources().openRawResource(R.raw.loading);
//            movie = Movie.decodeStream(inputStream);
//        }
//
//
//        @Override
//        protected void onDraw(Canvas canvas)
//        {
//            canvas.drawColor(Color.WHITE);
//            super.onDraw(canvas);
//            long now = android.os.SystemClock.uptimeMillis();
//
//            if(movieStart == 0)
//                movieStart = now;
//
//            int relTime = (int)((now - movieStart) % movie.duration());
//            movie.setTime(relTime);
//            movie.draw(canvas,this.getWidth()/2 - 20, this.getHeight()/2 - 40);
//            this.invalidate();
//        }
//    }
}

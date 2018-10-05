package com.example.prakash.risingnepal.service;

import android.net.Uri;
import android.os.AsyncTask;


import com.example.prakash.risingnepal.Data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by akash on 4/27/2016.
 */
public class YahooWeatherService {
    private WeatherServiceCallback callback;
    private String location;
    private Exception error;


    public YahooWeatherService(WeatherServiceCallback callback)
    {
        this.callback = callback;
    }

    public String getLocation()
    {
        return location;
    }

    public void refreshWeather(String l)
    {
        this.location=l;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String JQL= String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")and u='c'",strings[0]);
                String endpoint= String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(JQL));

                try {
                    URL url=new URL(endpoint);
                    URLConnection connection=url.openConnection();

                    InputStream inputStream=connection.getInputStream();
                    BufferedReader buffer=new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result=new StringBuilder();
                    String line;
                    while((line=buffer.readLine())!=null)
                    {

                       result.append(line);

                    }
                    return result.toString();
                } catch (Exception e) {

                    error=e;
                }


                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if(s==null & error!=null)

                {

                    callback.serviceFalure(error);
                }

                try {
                    JSONObject data=new JSONObject(s);
                    JSONObject queryResult=data.optJSONObject("query");
                    int count=queryResult.optInt("count");
                    if(count==0)
                    {
                        callback.serviceFalure(new LocationWeatherException("No weather information for location"+ location));
                        return;

                    }
                    Channel channel=new Channel();
                    channel.populate(queryResult.optJSONObject("results").optJSONObject("channel"));
                    callback.serviceSuccess(channel);
                } catch (JSONException e) {
                    callback.serviceFalure(e);
                }
            }
        }.execute(location);



    }
    public class LocationWeatherException extends Exception
    {
        public LocationWeatherException(String detailMessage) {
            super(detailMessage);
        }
    }
}

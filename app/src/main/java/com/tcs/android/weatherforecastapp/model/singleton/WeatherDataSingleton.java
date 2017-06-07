package com.tcs.android.weatherforecastapp.model.singleton;

import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.WeatherApiResponsePojo;

/**
 * Created by swapnil on 15/03/2017.
 */

public class WeatherDataSingleton {

    private static volatile WeatherDataSingleton singleton = null;

    private WeatherApiResponsePojo apiWeatherData;

    public static WeatherDataSingleton getInstance(){
        if (singleton == null) {
            synchronized (WeatherDataSingleton.class) {
                if (singleton == null){
                    singleton = new WeatherDataSingleton();
                }
            }
        }
        return singleton;
    }

    public WeatherApiResponsePojo getApiWeatherData() {
        return apiWeatherData;
    }

    public void setApiWeatherData(WeatherApiResponsePojo apiWeatherData) {
        this.apiWeatherData = apiWeatherData;
    }

}

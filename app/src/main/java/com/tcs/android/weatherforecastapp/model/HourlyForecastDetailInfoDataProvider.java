package com.tcs.android.weatherforecastapp.model;

import com.tcs.android.weatherforecastapp.model.constants.Utils;
import com.tcs.android.weatherforecastapp.model.singleton.WeatherDataSingleton;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.HourlyDataPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.HourlyPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.WeatherApiResponsePojo;
import com.tcs.android.weatherforecastapp.view.interfaces.ForecastDetailInfoInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * to show hourly forecast details
 * Created by swapnil on 16/03/2017.
 */

public class HourlyForecastDetailInfoDataProvider implements ForecastDetailInfoInterface.presenterToModel{
    private ForecastDetailInfoInterface.modelToPresenter modelToPresenter;
    public HourlyForecastDetailInfoDataProvider(ForecastDetailInfoInterface.modelToPresenter modelToPresenter) {
        this.modelToPresenter = modelToPresenter;
    }

    @Override
    public void provideData(int position) {
        WeatherApiResponsePojo apiWeatherData = WeatherDataSingleton.getInstance().getApiWeatherData();
        if(null != apiWeatherData.getHourly()){
            HourlyPojo hourly = apiWeatherData.getHourly();
            if(null != hourly.getData()){
                HourlyDataPojo hourlyDataPojo = hourly.getData().get(position);
                if(null != hourlyDataPojo){
                    List<String> moreInfoList = populateWeatherData(hourlyDataPojo);
                    modelToPresenter.taskCompleted(moreInfoList);
                }
            }
        }
    }

    private List<String> populateWeatherData(HourlyDataPojo hourlyDataPojo) {
        List<String> moreInfoList = new ArrayList<>();

        if(null != hourlyDataPojo.getTemperature()){
            moreInfoList.add(Utils.TEMPERATURE + String.valueOf(Utils.addSuffix(hourlyDataPojo.getTemperature())));
        }else{
            moreInfoList.add(Utils.TEMPERATURE + Utils.notAvailable);
        }

        if(null != hourlyDataPojo.getApparentTemperature()){
            moreInfoList.add(Utils.APPARENT_TEMP +String.valueOf(Utils.addSuffix(hourlyDataPojo.getApparentTemperature())));
        }else{
            moreInfoList.add(Utils.APPARENT_TEMP + Utils.notAvailable);
        }

        if(null != hourlyDataPojo.getWindSpeed()){
            moreInfoList.add(Utils.WIND_SPEED +String.valueOf(hourlyDataPojo.getWindSpeed()));
        }else{
            moreInfoList.add(Utils.WIND_SPEED + Utils.notAvailable);
        }

        if(null != hourlyDataPojo.getCloudCover()){
            moreInfoList.add(Utils.CLOUD_COVER + String.valueOf(hourlyDataPojo.getCloudCover()));
        }else{
            moreInfoList.add(Utils.CLOUD_COVER + Utils.notAvailable);
        }

        if(null != hourlyDataPojo.getDewPoint()){
            moreInfoList.add(Utils.DEW_POINT + String.valueOf(hourlyDataPojo.getDewPoint()));
        }else{
            moreInfoList.add(Utils.DEW_POINT + Utils.notAvailable);
        }

        if(null != hourlyDataPojo.getHumidity()){
            moreInfoList.add(Utils.HUMIDITY + String.valueOf(hourlyDataPojo.getHumidity()));
        }else{
            moreInfoList.add(Utils.HUMIDITY + Utils.notAvailable);
        }

        if(null != hourlyDataPojo.getOzone()){
            moreInfoList.add(Utils.OZONE + String.valueOf(hourlyDataPojo.getOzone()));
        }else{
            moreInfoList.add(Utils.OZONE + Utils.notAvailable);
        }

        if(null != hourlyDataPojo.getPrecipIntensity()){
            moreInfoList.add(Utils.PRECIP_INTENSITY + String.valueOf(hourlyDataPojo.getPrecipIntensity()));
        }else{
            moreInfoList.add(Utils.PRECIP_INTENSITY + Utils.notAvailable);
        }

        if(null != hourlyDataPojo.getPrecipProbability()){
            moreInfoList.add(Utils.PRECIP_PROBABILITY + String.valueOf(hourlyDataPojo.getPrecipProbability()));
        }else{
            moreInfoList.add(Utils.PRECIP_PROBABILITY + Utils.notAvailable);
        }

        if(null != hourlyDataPojo.getPrecipIntensity()){
            moreInfoList.add(Utils.PRECIP_INTENSITY + String.valueOf(hourlyDataPojo.getPrecipIntensity()));
        }else{
            moreInfoList.add(Utils.PRECIP_INTENSITY + Utils.notAvailable);
        }

        return moreInfoList;
    }
}
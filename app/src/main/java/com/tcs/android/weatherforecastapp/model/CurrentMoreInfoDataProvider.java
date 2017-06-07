package com.tcs.android.weatherforecastapp.model;

import com.tcs.android.weatherforecastapp.model.constants.Utils;
import com.tcs.android.weatherforecastapp.model.singleton.WeatherDataSingleton;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.CurrentlyPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.WeatherApiResponsePojo;
import com.tcs.android.weatherforecastapp.view.interfaces.CurrentMoreInfoInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * this method will fetch data to display on click of more info
 * Created by swapnil on 16/03/2017.
 */

public class CurrentMoreInfoDataProvider implements CurrentMoreInfoInterface.presenterToModel{

    private CurrentMoreInfoInterface.modelToPresenter modelToPresenter;
    public CurrentMoreInfoDataProvider(CurrentMoreInfoInterface.modelToPresenter modelToPresenter) {
        this.modelToPresenter = modelToPresenter;
    }

    @Override
    public void provideData() {
        WeatherApiResponsePojo apiWeatherData = WeatherDataSingleton.getInstance().getApiWeatherData();

        if(null != apiWeatherData.getCurrently()){
            CurrentlyPojo currently = apiWeatherData.getCurrently();
            if(null != currently){
                List<String> moreInfoList = populateWeatherData(currently);
                modelToPresenter.taskCompleted(moreInfoList);
            }
        }

    }

    private List<String> populateWeatherData(CurrentlyPojo currently) {
        List<String> moreInfoList = new ArrayList<>();

        if(null != currently.getTemperature()){
            moreInfoList.add(Utils.TEMPERATURE + String.valueOf(Utils.addSuffix(currently.getTemperature())));
        }else{
            moreInfoList.add(Utils.TEMPERATURE + Utils.notAvailable);
        }

        if(null != currently.getApparentTemperature()){
            moreInfoList.add(Utils.APPARENT_TEMP +String.valueOf(Utils.addSuffix(currently.getApparentTemperature())));
        }else{
            moreInfoList.add(Utils.APPARENT_TEMP + Utils.notAvailable);
        }

        if(null != currently.getWindSpeed()){
            moreInfoList.add(Utils.WIND_SPEED +String.valueOf(currently.getWindSpeed()));
        }else{
            moreInfoList.add(Utils.WIND_SPEED + Utils.notAvailable);
        }

        if(null != currently.getCloudCover()){
            moreInfoList.add(Utils.CLOUD_COVER + String.valueOf(currently.getCloudCover()));
        }else{
            moreInfoList.add(Utils.CLOUD_COVER + Utils.notAvailable);
        }

        if(null != currently.getDewPoint()){
            moreInfoList.add(Utils.DEW_POINT + String.valueOf(currently.getDewPoint()));
        }else{
            moreInfoList.add(Utils.DEW_POINT + Utils.notAvailable);
        }

        if(null != currently.getHumidity()){
            moreInfoList.add(Utils.HUMIDITY + String.valueOf(currently.getHumidity()));
        }else{
            moreInfoList.add(Utils.HUMIDITY + Utils.notAvailable);
        }

        if(null != currently.getOzone()){
            moreInfoList.add(Utils.OZONE + String.valueOf(currently.getOzone()));
        }else{
            moreInfoList.add(Utils.OZONE + Utils.notAvailable);
        }

        if(null != currently.getPrecipIntensity()){
            moreInfoList.add(Utils.PRECIP_INTENSITY + String.valueOf(currently.getPrecipIntensity()));
        }else{
            moreInfoList.add(Utils.PRECIP_INTENSITY + Utils.notAvailable);
        }

        if(null != currently.getPrecipProbability()){
            moreInfoList.add(Utils.PRECIP_PROBABILITY + String.valueOf(currently.getPrecipProbability()));
        }else{
            moreInfoList.add(Utils.PRECIP_PROBABILITY + Utils.notAvailable);
        }

        if(null != currently.getWindBearing()){
            moreInfoList.add(Utils.WIND_BEARING + String.valueOf(currently.getWindBearing()));
        }else{
            moreInfoList.add(Utils.WIND_BEARING + Utils.notAvailable);
        }

        return moreInfoList;
    }
}

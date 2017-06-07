package com.tcs.android.weatherforecastapp.model;

import com.tcs.android.weatherforecastapp.model.constants.Utils;
import com.tcs.android.weatherforecastapp.model.singleton.WeatherDataSingleton;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.DailyDataPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.DailyPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.WeatherApiResponsePojo;
import com.tcs.android.weatherforecastapp.view.interfaces.ForecastDetailInfoInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapnil on 16/03/2017.
 */

public class DailyForecastDetailInfoDataProvider implements ForecastDetailInfoInterface.presenterToModel{
    private ForecastDetailInfoInterface.modelToPresenter modelToPresenter;
    public DailyForecastDetailInfoDataProvider(ForecastDetailInfoInterface.modelToPresenter modelToPresenter) {
        this.modelToPresenter = modelToPresenter;
    }

    @Override
    public void provideData(int position) {
        WeatherApiResponsePojo apiWeatherData = WeatherDataSingleton.getInstance().getApiWeatherData();
        if(null != apiWeatherData.getDaily()){
            DailyPojo daily = apiWeatherData.getDaily();
            if(null != daily.getData()){
                DailyDataPojo dailyDataPojo = daily.getData().get(position);
                if(null != dailyDataPojo){
                    List<String> moreInfoList = populateWeatherData(dailyDataPojo);
                    modelToPresenter.taskCompleted(moreInfoList);
                }
            }
        }
    }

    private List<String> populateWeatherData(DailyDataPojo dailyDataPojo) {
        List<String> moreInfoList = new ArrayList<>();
        if(null != dailyDataPojo.getApparentTemperatureMax()){
            moreInfoList.add(Utils.APPARENT_TEMP +String.valueOf(Utils.addSuffix(dailyDataPojo.getApparentTemperatureMax())));
        }else{
            moreInfoList.add(Utils.APPARENT_TEMP + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getWindSpeed()){
            moreInfoList.add(Utils.WIND_SPEED +String.valueOf(dailyDataPojo.getWindSpeed()));
        }else{
            moreInfoList.add(Utils.WIND_SPEED + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getApparentTemperatureMaxTime()){
            moreInfoList.add(Utils.APPARENT_TEMP_MAX_TIME +String.valueOf(dailyDataPojo.getApparentTemperatureMaxTime()));
        }else{
            moreInfoList.add(Utils.APPARENT_TEMP_MAX_TIME + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getApparentTemperatureMinTime()){
            moreInfoList.add(Utils.APPARENT_TEMP_MIN_TIME + String.valueOf(dailyDataPojo.getApparentTemperatureMinTime()));
        }else{
            moreInfoList.add(Utils.APPARENT_TEMP_MIN_TIME + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getApparentTemperatureMax()){
            moreInfoList.add(Utils.APPARENT_TEMP_MAX + String.valueOf(dailyDataPojo.getApparentTemperatureMax()));
        }else{
            moreInfoList.add(Utils.APPARENT_TEMP_MAX + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getApparentTemperatureMin()){
            moreInfoList.add(Utils.APPARENT_TEMP_MIN + String.valueOf(dailyDataPojo.getApparentTemperatureMin()));
        }else{
            moreInfoList.add(Utils.APPARENT_TEMP_MIN + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getCloudCover()){
            moreInfoList.add(Utils.CLOUD_COVER + String.valueOf(dailyDataPojo.getCloudCover()));
        }else{
            moreInfoList.add(Utils.CLOUD_COVER + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getDewPoint()){
            moreInfoList.add(Utils.DEW_POINT + String.valueOf(dailyDataPojo.getDewPoint()));
        }else{
            moreInfoList.add(Utils.DEW_POINT + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getHumidity()){
            moreInfoList.add(Utils.HUMIDITY + String.valueOf(dailyDataPojo.getHumidity()));
        }else{
            moreInfoList.add(Utils.HUMIDITY + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getMoonPhase()){
            moreInfoList.add(Utils.MOON_PHASE + String.valueOf(dailyDataPojo.getMoonPhase()));
        }else{
            moreInfoList.add(Utils.MOON_PHASE + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getOzone()){
            moreInfoList.add(Utils.OZONE + String.valueOf(dailyDataPojo.getOzone()));
        }else{
            moreInfoList.add(Utils.OZONE + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getPrecipIntensity()){
            moreInfoList.add(Utils.PRECIP_INTENSITY + String.valueOf(dailyDataPojo.getPrecipIntensity()));
        }else{
            moreInfoList.add(Utils.PRECIP_INTENSITY + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getPrecipIntensityMax()){
            moreInfoList.add(Utils.PRECIP_INTENSITY_MAX + String.valueOf(dailyDataPojo.getPrecipIntensityMax()));
        }else{
            moreInfoList.add(Utils.PRECIP_INTENSITY_MAX + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getPrecipIntensityMaxTime()){
            moreInfoList.add(Utils.PRECIP_INTENSITY_MAX_TIME + String.valueOf(dailyDataPojo.getPrecipIntensityMaxTime()));
        }else{
            moreInfoList.add(Utils.PRECIP_INTENSITY_MAX_TIME + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getPrecipProbability()){
            moreInfoList.add(Utils.PRECIP_PROBABILITY + String.valueOf(dailyDataPojo.getPrecipProbability()));
        }else{
            moreInfoList.add(Utils.PRECIP_PROBABILITY + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getPrecipIntensity()){
            moreInfoList.add(Utils.PRECIP_INTENSITY + String.valueOf(dailyDataPojo.getPrecipIntensity()));
        }else{
            moreInfoList.add(Utils.PRECIP_INTENSITY + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getPrecipIntensityMax()){
            moreInfoList.add(Utils.PRECIP_INTENSITY_MAX + String.valueOf(dailyDataPojo.getPrecipIntensityMax()));
        }else{
            moreInfoList.add(Utils.PRECIP_INTENSITY_MAX + Utils.notAvailable);
        }

        if(null != dailyDataPojo.getPrecipIntensityMaxTime()){
            moreInfoList.add(Utils.PRECIP_INTENSITY_MAX_TIME + String.valueOf(dailyDataPojo.getPrecipIntensityMaxTime()));
        }else{
            moreInfoList.add(Utils.PRECIP_INTENSITY_MAX_TIME + Utils.notAvailable);
        }

        return moreInfoList;
    }
}

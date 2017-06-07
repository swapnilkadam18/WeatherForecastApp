package com.tcs.android.weatherforecastapp.model;

import com.tcs.android.weatherforecastapp.model.constants.Utils;
import com.tcs.android.weatherforecastapp.model.pojos.ForecastListData;
import com.tcs.android.weatherforecastapp.model.singleton.WeatherDataSingleton;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.HourlyDataPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.HourlyPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.WeatherApiResponsePojo;
import com.tcs.android.weatherforecastapp.view.interfaces.ForecastSummaryInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapnil on 15/03/2017.
 */

public class HourForecastListDataProvider implements ForecastSummaryInterface.presenterToModel{

    private ForecastSummaryInterface.modelToPresenter modelToPresenter;

    public HourForecastListDataProvider(ForecastSummaryInterface.modelToPresenter modelToPresenter) {
        this.modelToPresenter = modelToPresenter;
    }

    @Override
    public void provideData() {
        List<ForecastListData> dataList = new ArrayList<>();
        ForecastListData data;
        WeatherApiResponsePojo apiWeatherData = WeatherDataSingleton.getInstance().getApiWeatherData();
        if(null != apiWeatherData.getHourly()){
            HourlyPojo hourly = apiWeatherData.getHourly();
            if(null != hourly.getData()){
                for (HourlyDataPojo hourlyData:hourly.getData()) {
                    data = new ForecastListData();
                    if(null != hourlyData.getSummary()){
                        data.setSummary(Utils.getQuotesFreeString(hourlyData.getSummary()));
                    }else{
                        data.setSummary("");
                    }
                    if(null != hourlyData.getIcon()){
                        data.setIconTitle(Utils.getQuotesFreeString(hourlyData.getIcon()));
                    }else {
                        data.setIconTitle("");
                    }
                    if(null != hourlyData.getTime()){
                        data.setTime(Utils.convertToHourlyFormat(hourlyData.getTime()));
                    }else{
                        data.setTime("");
                    }
                    dataList.add(data);
                }
            }

        }
        modelToPresenter.taskCompleted(dataList);
    }
}

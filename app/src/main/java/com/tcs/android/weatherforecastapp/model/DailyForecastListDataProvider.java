package com.tcs.android.weatherforecastapp.model;

import com.tcs.android.weatherforecastapp.model.constants.Utils;
import com.tcs.android.weatherforecastapp.model.pojos.ForecastListData;
import com.tcs.android.weatherforecastapp.model.singleton.WeatherDataSingleton;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.DailyDataPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.DailyPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.WeatherApiResponsePojo;
import com.tcs.android.weatherforecastapp.view.interfaces.ForecastSummaryInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * provide data for daily forecast
 * Created by swapnil on 16/03/2017.
 */

public class DailyForecastListDataProvider implements ForecastSummaryInterface.presenterToModel{
    private ForecastSummaryInterface.modelToPresenter modelToPresenter;

    public DailyForecastListDataProvider(ForecastSummaryInterface.modelToPresenter modelToPresenter) {
        this.modelToPresenter = modelToPresenter;
    }

    @Override
    public void provideData() {
        WeatherApiResponsePojo apiWeatherData = WeatherDataSingleton.getInstance().getApiWeatherData();
        if(null != apiWeatherData.getDaily()){
            DailyPojo daily = apiWeatherData.getDaily();
            if(null != daily.getData()){
                List<ForecastListData> dataList = populateDailyData(daily);
                modelToPresenter.taskCompleted(dataList);
            }
        }
    }

    private List<ForecastListData> populateDailyData(DailyPojo daily) {
        List<ForecastListData> dataList = new ArrayList<>();
        for (DailyDataPojo dailyData:daily.getData()) {
            ForecastListData data = new ForecastListData();
            if(null != dailyData.getSummary()){
                data.setSummary(Utils.getQuotesFreeString(dailyData.getSummary()));
            }else{
                data.setSummary("");
            }
            if(null != dailyData.getIcon()){
                data.setIconTitle(Utils.getQuotesFreeString(dailyData.getIcon()));
            }else {
                data.setIconTitle("");
            }
            if(null != dailyData.getTime()){
                data.setTime(Utils.convertToDailyFormat(dailyData.getTime()));
            }else{
                data.setTime("");
            }
            dataList.add(data);
        }

        return dataList;
    }
}

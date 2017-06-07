package com.tcs.android.weatherforecastapp.model;

import com.tcs.android.weatherforecastapp.model.constants.Utils;
import com.tcs.android.weatherforecastapp.model.pojos.MainScreen;
import com.tcs.android.weatherforecastapp.model.singleton.WeatherDataSingleton;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.CurrentlyPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.DailyPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.HourlyPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.WeatherApiResponsePojo;
import com.tcs.android.weatherforecastapp.view.interfaces.MainScreenInterface;

import java.io.File;

/**
 * Created by swapnil on 15/03/2017.
 */

public class MainScreenDataProvider implements MainScreenInterface.presenterToModel {

    private MainScreenInterface.modelToPresenter modelToPresenter;

    public MainScreenDataProvider(MainScreenInterface.modelToPresenter modelToPresenter) {
        this.modelToPresenter = modelToPresenter;
    }

    @Override
    public void provideData() {
        MainScreen mainScreen = new MainScreen();
        WeatherApiResponsePojo apiWeatherData = WeatherDataSingleton.getInstance().getApiWeatherData();
        if (null != apiWeatherData) {
            mainScreen.setCoordinates(getCoordinates(apiWeatherData));
            mainScreen.setCurrentIcon(getCurrentIcon(apiWeatherData));
            mainScreen.setDailyIcon(getDailyIcon(apiWeatherData));
            mainScreen.setDailySummary(getDailySummary(apiWeatherData));
            mainScreen.setTime(getCurrentTime(apiWeatherData));
            mainScreen.setHourlyIcon(getHourlyIcon(apiWeatherData));
            mainScreen.setHourlySummary(getHourlySummary(apiWeatherData));
            mainScreen.setTemperature(getCurrentTemp(apiWeatherData));
            mainScreen.setSummary(getCurrentSummary(apiWeatherData));
            modelToPresenter.taskCompleted(mainScreen);
        } else {
            modelToPresenter.showMessage("Weather data could not be fetched, please try again.");
        }


    }

    /**
     * method to get current summary
     *
     * @param apiWeatherData data source to fetch from
     * @return current summary value
     */
    private String getCurrentSummary(WeatherApiResponsePojo apiWeatherData) {
        String currentSummary = "";
        if (null != apiWeatherData.getCurrently()) {
            CurrentlyPojo current = apiWeatherData.getCurrently();
            if (null != current.getSummary()) {
                currentSummary = current.getSummary();
                currentSummary = Utils.getQuotesFreeString(currentSummary);
            }
        }
        return currentSummary;
    }

    /**
     * method to get current temperature
     *
     * @param apiWeatherData data source to fetch from
     * @return current temperature value
     */
    private String getCurrentTemp(WeatherApiResponsePojo apiWeatherData) {
        String currentTemp = "";
        if (null != apiWeatherData.getCurrently()) {
            CurrentlyPojo currently = apiWeatherData.getCurrently();
            if (null != currently.getTemperature()) {
                currentTemp = Utils.addSuffix(currently.getTemperature());
            }
        }
        return currentTemp;
    }

    /**
     * method to get hourly summary
     *
     * @param apiWeatherData data source to fetch from
     * @return hourly summary value
     */
    private String getHourlySummary(WeatherApiResponsePojo apiWeatherData) {
        String hourlySummary = "";
        if (null != apiWeatherData.getHourly()) {
            HourlyPojo hourly = apiWeatherData.getHourly();
            if (null != hourly.getSummary()) {
                hourlySummary = hourly.getSummary();
                hourlySummary = Utils.getQuotesFreeString(hourlySummary);
            }
        }
        return hourlySummary;
    }

    /**
     * method to fetch hourly icon value
     *
     * @param apiWeatherData data source to fetch from
     * @return hourly icon value
     */
    private String getHourlyIcon(WeatherApiResponsePojo apiWeatherData) {
        String iconTitle = "";
        if (null != apiWeatherData.getHourly()) {
            HourlyPojo hourly = apiWeatherData.getHourly();
            if (null != hourly.getIcon()) {
                iconTitle = hourly.getIcon();
                iconTitle = Utils.getQuotesFreeString(iconTitle);
            }
        }
        return iconTitle;
    }

    /**
     * method to get current summary
     *
     * @param apiWeatherData data source to fetch from
     * @return current time value
     */
    private String getCurrentTime(WeatherApiResponsePojo apiWeatherData) {
        String currentTime = "";
        if (null != apiWeatherData.getCurrently()) {
            CurrentlyPojo currently = apiWeatherData.getCurrently();
            if (null != currently.getTime()) {
                currentTime = Utils.convertToDailyFormat(currently.getTime());
            }
        }
        return currentTime;
    }

    /**
     * method to get daily summary
     *
     * @param apiWeatherData data source to fetch from
     * @return daily summary value
     */
    private String getDailySummary(WeatherApiResponsePojo apiWeatherData) {
        String dailySummary = "";
        if (null != apiWeatherData.getDaily()) {
            DailyPojo daily = apiWeatherData.getDaily();
            if (null != daily.getSummary()) {
                dailySummary = daily.getSummary();
                dailySummary = Utils.getQuotesFreeString(dailySummary);
            }
        }
        return dailySummary;
    }

    /**
     * method to fetch current icon value
     *
     * @param apiWeatherData data source to fetch from
     * @return daily icon value
     */
    private String getDailyIcon(WeatherApiResponsePojo apiWeatherData) {
        String iconTitle = "";
        if (null != apiWeatherData.getDaily()) {
            DailyPojo daily = apiWeatherData.getDaily();
            if (null != daily.getIcon()) {
                iconTitle = daily.getIcon();
                iconTitle = Utils.getQuotesFreeString(iconTitle);
            }
        }
        return iconTitle;
    }


    /**
     * method to fetch current icon value
     *
     * @param apiWeatherData data source to fetch from
     * @return current icon value
     */
    private String getCurrentIcon(WeatherApiResponsePojo apiWeatherData) {
        String iconTitle = "";
        if (null != apiWeatherData.getCurrently()) {
            CurrentlyPojo currently = apiWeatherData.getCurrently();
            if (null != currently.getIcon()) {
                iconTitle = currently.getIcon();
                iconTitle = Utils.getQuotesFreeString(iconTitle);
            }
        }
        return iconTitle;
    }


    /**
     * method to fetch lat and lon values
     *
     * @param apiWeatherData data source to fetch from
     * @return concatenated string of coordinate
     */
    private String getCoordinates(WeatherApiResponsePojo apiWeatherData) {
        double latitude = 0;
        if (null != apiWeatherData.getLatitude()) {
            latitude = apiWeatherData.getLatitude();
        }
        double longitude = 0;
        if (null != apiWeatherData.getLongitude()) {
            longitude = apiWeatherData.getLongitude();
        }
        return (String.valueOf(Utils.decimalFormat.format(latitude)) + File.separator + String.valueOf(Utils.decimalFormat.format(longitude)));
    }
}

package com.tcs.android.weatherforecastapp.model.pojos;

/**
 * Created by swapnil on 15/03/2017.
 */

public class MainScreen {
    private String time;
    private String summary;
    private String coordinates;
    private String temperature;
    private String hourlySummary;
    private String dailySummary;
    private String currentIcon;
    private String dailyIcon;
    private String hourlyIcon;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHourlySummary() {
        return hourlySummary;
    }

    public void setHourlySummary(String hourlySummary) {
        this.hourlySummary = hourlySummary;
    }

    public String getDailySummary() {
        return dailySummary;
    }

    public void setDailySummary(String dailySummary) {
        this.dailySummary = dailySummary;
    }

    public String getCurrentIcon() {
        return currentIcon;
    }

    public void setCurrentIcon(String currentIcon) {
        this.currentIcon = currentIcon;
    }

    public String getDailyIcon() {
        return dailyIcon;
    }

    public void setDailyIcon(String dailyIcon) {
        this.dailyIcon = dailyIcon;
    }

    public String getHourlyIcon() {
        return hourlyIcon;
    }

    public void setHourlyIcon(String hourlyIcon) {
        this.hourlyIcon = hourlyIcon;
    }
}

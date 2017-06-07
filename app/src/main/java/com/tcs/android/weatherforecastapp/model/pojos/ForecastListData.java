package com.tcs.android.weatherforecastapp.model.pojos;

/**
 * Created by swapnil on 15/03/2017.
 */

public class ForecastListData {
    private String time;
    private String summary;
    private String iconTitle;

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

    public String getIconTitle() {
        return iconTitle;
    }

    public void setIconTitle(String iconTitle) {
        this.iconTitle = iconTitle;
    }
}

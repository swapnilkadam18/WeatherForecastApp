package com.tcs.android.weatherforecastapp.model.singleton.singletonpojos;

import java.util.List;

/**
 * Created by swapnil on 15/03/2017.
 */

public class HourlyPojo {
    private String summary;
    private String icon;
    private List<HourlyDataPojo> data = null;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<HourlyDataPojo> getData() {
        return data;
    }

    public void setData(List<HourlyDataPojo> data) {
        this.data = data;
    }
}

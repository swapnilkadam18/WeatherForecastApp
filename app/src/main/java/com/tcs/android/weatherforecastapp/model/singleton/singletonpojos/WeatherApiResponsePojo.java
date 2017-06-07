package com.tcs.android.weatherforecastapp.model.singleton.singletonpojos;

/**
 * Created by swapnil on 15/03/2017.
 */

public class WeatherApiResponsePojo {
    private Double latitude;
    private Double longitude;
    private String timezone;
    private Double offset;
    private CurrentlyPojo currently;
    private HourlyPojo hourly;
    private DailyPojo daily;
    private FlagsPojo flags;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Double getOffset() {
        return offset;
    }

    public void setOffset(Double offset) {
        this.offset = offset;
    }

    public CurrentlyPojo getCurrently() {
        return currently;
    }

    public void setCurrently(CurrentlyPojo currently) {
        this.currently = currently;
    }

    public HourlyPojo getHourly() {
        return hourly;
    }

    public void setHourly(HourlyPojo hourly) {
        this.hourly = hourly;
    }

    public DailyPojo getDaily() {
        return daily;
    }

    public void setDaily(DailyPojo daily) {
        this.daily = daily;
    }

    public FlagsPojo getFlags() {
        return flags;
    }

    public void setFlags(FlagsPojo flags) {
        this.flags = flags;
    }
}

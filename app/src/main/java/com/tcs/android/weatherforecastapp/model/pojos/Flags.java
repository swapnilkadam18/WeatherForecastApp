package com.tcs.android.weatherforecastapp.model.pojos;

import java.util.List;

/**
 * Created by swapnil on 15/03/2017.
 */

public class Flags {
    //to store the value of sources
    private List<String> sources;
    //to store the value of stations
    private List<String> isdStations;
    //to store the value of units
    private String units;

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public List<String> getIsdStations() {
        return isdStations;
    }

    public void setIsdStations(List<String> isdStations) {
        this.isdStations = isdStations;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}

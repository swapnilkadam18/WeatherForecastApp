package com.tcs.android.weatherforecastapp.model.singleton.singletonpojos;

import java.util.List;

/**
 * Created by swapnil on 15/03/2017.
 */

public class FlagsPojo {
    private List<String> sources = null;
    private List<String> isdStations = null;
    private List<String> madisStations = null;
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

    public List<String> getMadisStations() {
        return madisStations;
    }

    public void setMadisStations(List<String> madisStations) {
        this.madisStations = madisStations;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}

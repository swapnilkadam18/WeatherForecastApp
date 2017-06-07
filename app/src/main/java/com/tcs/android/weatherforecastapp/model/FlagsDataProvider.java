package com.tcs.android.weatherforecastapp.model;

import com.tcs.android.weatherforecastapp.model.pojos.Flags;
import com.tcs.android.weatherforecastapp.model.singleton.WeatherDataSingleton;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.FlagsPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.WeatherApiResponsePojo;
import com.tcs.android.weatherforecastapp.view.interfaces.FlagInterface;

/**
 * Created by swapnil on 16/03/2017.
 */

public class FlagsDataProvider implements FlagInterface.presenterToModel{
    private FlagInterface.modelToPresenter modelToPresenter;
    public FlagsDataProvider(FlagInterface.modelToPresenter modelToPresenter) {
        this.modelToPresenter = modelToPresenter;
    }

    @Override
    public void provideData() {
        WeatherApiResponsePojo apiWeatherData = WeatherDataSingleton.getInstance().getApiWeatherData();
        Flags flagData = new Flags();
        if(null != apiWeatherData.getFlags()){
            FlagsPojo flags = apiWeatherData.getFlags();

            if(null != flags.getIsdStations()){
                flagData.setIsdStations(flags.getIsdStations());
            }
            if(null != flags.getSources()){
                flagData.setSources(flags.getSources());
            }
            if(null != flags.getUnits()){
                flagData.setUnits(flags.getUnits());
            }
        }
        modelToPresenter.taskCompleted(flagData);
    }
}

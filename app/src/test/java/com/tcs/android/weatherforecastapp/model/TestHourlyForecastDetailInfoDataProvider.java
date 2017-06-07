package com.tcs.android.weatherforecastapp.model;

import com.tcs.android.weatherforecastapp.model.CurrentMoreInfoDataProvider;
import com.tcs.android.weatherforecastapp.model.DailyForecastDetailInfoDataProvider;
import com.tcs.android.weatherforecastapp.model.HourlyForecastDetailInfoDataProvider;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.DailyDataPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.DailyPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.HourlyDataPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.HourlyPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.WeatherApiResponsePojo;
import com.tcs.android.weatherforecastapp.view.interfaces.ForecastDetailInfoInterface;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapnil on 17/03/2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(HourlyForecastDetailInfoDataProvider.class)
public class TestHourlyForecastDetailInfoDataProvider {
    private HourlyForecastDetailInfoDataProvider dataProvider;
    @Mock
    private ForecastDetailInfoInterface.modelToPresenter modelToPresenter;

    private List<HourlyDataPojo> hourlyDataPojo = new ArrayList<>();

    private HourlyDataPojo pojo = new HourlyDataPojo();

    private HourlyPojo hourlyPojo = new HourlyPojo();

    @Test
    public void testPopulateWeatherData() {

        WeatherApiResponsePojo apiResponsePojo = new WeatherApiResponsePojo();
        apiResponsePojo.setHourly(hourlyPojo);
        apiResponsePojo.getHourly().setData(hourlyDataPojo);

        dataProvider = PowerMockito.spy(new HourlyForecastDetailInfoDataProvider(modelToPresenter));
        try {
            List<String> moreInfoList = Whitebox.invokeMethod(dataProvider, "populateWeatherData", pojo);
            Assert.assertEquals(10, moreInfoList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

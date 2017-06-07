package com.tcs.android.weatherforecastapp.model;

import com.tcs.android.weatherforecastapp.model.CurrentMoreInfoDataProvider;
import com.tcs.android.weatherforecastapp.model.DailyForecastDetailInfoDataProvider;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.CurrentlyPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.DailyDataPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.DailyPojo;
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
 * to test daily more info class
 * Created by swapnil on 17/03/2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(DailyForecastDetailInfoDataProvider.class)
public class TestDailyForecastDetailInfoDataProvider {

    private DailyForecastDetailInfoDataProvider dataProvider;
    @Mock
    private ForecastDetailInfoInterface.modelToPresenter modelToPresenter;

    private List<DailyDataPojo> dailyDataPojo = new ArrayList<>();

    private DailyDataPojo pojo = new DailyDataPojo();

    private DailyPojo dailyPojo = new DailyPojo();

    @Test
    public void testPopulateWeatherData() {

        WeatherApiResponsePojo apiResponsePojo = new WeatherApiResponsePojo();
        apiResponsePojo.setDaily(dailyPojo);
        apiResponsePojo.getDaily().setData(dailyDataPojo);

        dataProvider = PowerMockito.spy(new DailyForecastDetailInfoDataProvider(modelToPresenter));
        try {
            List<String> moreInfoList = Whitebox.invokeMethod(dataProvider, "populateWeatherData", pojo);
            Assert.assertEquals(18, moreInfoList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

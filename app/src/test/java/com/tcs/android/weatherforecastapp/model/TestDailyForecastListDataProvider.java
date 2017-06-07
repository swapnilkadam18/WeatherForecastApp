package com.tcs.android.weatherforecastapp.model;

import com.tcs.android.weatherforecastapp.model.CurrentMoreInfoDataProvider;
import com.tcs.android.weatherforecastapp.model.DailyForecastListDataProvider;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.DailyDataPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.DailyPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.WeatherApiResponsePojo;
import com.tcs.android.weatherforecastapp.view.interfaces.ForecastSummaryInterface;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.List;

/**
 * Created by swapnil on 16/03/2017.
 */


@RunWith(PowerMockRunner.class)
@PrepareForTest(DailyForecastListDataProvider.class)
public class TestDailyForecastListDataProvider {

    private DailyForecastListDataProvider dataProvider;
    @Mock
    private ForecastSummaryInterface.modelToPresenter modelToPresenter;
    private DailyPojo dailyPojo = new DailyPojo();
    @Mock
    private List<DailyDataPojo> dataList;

    @Test
    public void testPopulateDailyData(){

        WeatherApiResponsePojo apiResponsePojo = new WeatherApiResponsePojo();
        dailyPojo.setData(dataList);
        apiResponsePojo.setDaily(dailyPojo);

        dataProvider = PowerMockito.spy(new DailyForecastListDataProvider(modelToPresenter));
        try {
            List<String> moreInfoList = Whitebox.invokeMethod(dataProvider,"populateDailyData",dailyPojo);
            Assert.assertEquals(3, moreInfoList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

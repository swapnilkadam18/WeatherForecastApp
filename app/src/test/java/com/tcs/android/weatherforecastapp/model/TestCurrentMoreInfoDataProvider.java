package com.tcs.android.weatherforecastapp.model;

import com.tcs.android.weatherforecastapp.model.CurrentMoreInfoDataProvider;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.CurrentlyPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.WeatherApiResponsePojo;
import com.tcs.android.weatherforecastapp.view.interfaces.CurrentMoreInfoInterface;

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
 * Test class to test Current info data
 * Created by swapnil on 16/03/2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(CurrentMoreInfoDataProvider.class)
public class TestCurrentMoreInfoDataProvider {

    private CurrentMoreInfoDataProvider dataProvider;
    @Mock
    private CurrentMoreInfoInterface.modelToPresenter modelToPresenter;

    private CurrentlyPojo currentlyPojo = new CurrentlyPojo();
    @Test
    public void testPopulateWeatherData(){

        WeatherApiResponsePojo apiResponsePojo = new WeatherApiResponsePojo();
        apiResponsePojo.setCurrently(currentlyPojo);

        dataProvider = PowerMockito.spy(new CurrentMoreInfoDataProvider(modelToPresenter));
        try {
            List<String> moreInfoList = Whitebox.invokeMethod(dataProvider,"populateWeatherData",currentlyPojo);
            Assert.assertEquals(10, moreInfoList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

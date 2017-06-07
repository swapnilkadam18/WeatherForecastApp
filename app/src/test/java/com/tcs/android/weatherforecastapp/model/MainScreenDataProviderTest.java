package com.tcs.android.weatherforecastapp.model;

import com.tcs.android.weatherforecastapp.model.MainScreenDataProvider;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.CurrentlyPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.DailyPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.HourlyPojo;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.WeatherApiResponsePojo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Test cases to test MainScreenDataProvider class
 * Created by swapnil on 16/03/2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(MainScreenDataProvider.class)
public class MainScreenDataProviderTest {

    private WeatherApiResponsePojo apiResponsePojo = new WeatherApiResponsePojo();

    @Test
    public void testCurrentTemp(){
        CurrentlyPojo currentlyPojo = new CurrentlyPojo();
        currentlyPojo.setTemperature(88.01);
        MainScreenDataProvider mainScreenDataProvider = Mockito.mock(MainScreenDataProvider.class);
        apiResponsePojo.setCurrently(currentlyPojo);
        try{
            String val = org.powermock.reflect.Whitebox.invokeMethod(mainScreenDataProvider, "getCurrentTemp", apiResponsePojo);
            Assert.assertEquals("88.01"+" \u2109",val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDailyIconTitles(){
        DailyPojo dailyPojo = new DailyPojo();
        dailyPojo.setIcon("clear-sky");
        MainScreenDataProvider mainScreenDataProvider = Mockito.mock(MainScreenDataProvider.class);
        apiResponsePojo.setDaily(dailyPojo);
        try{
            String val = org.powermock.reflect.Whitebox.invokeMethod(mainScreenDataProvider, "getDailyIcon", apiResponsePojo);
            Assert.assertEquals("clear-sky",val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDailySummary(){
        DailyPojo dailyPojo = new DailyPojo();
        dailyPojo.setSummary("clear day");
        MainScreenDataProvider mainScreenDataProvider = Mockito.mock(MainScreenDataProvider.class);
        apiResponsePojo.setDaily(dailyPojo);
        try{
            String val = org.powermock.reflect.Whitebox.invokeMethod(mainScreenDataProvider, "getDailySummary", apiResponsePojo);
            Assert.assertEquals("clear day",val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHourlyIconTitles(){
        HourlyPojo hourlyPojo = new HourlyPojo();
        hourlyPojo.setIcon("clear-sky");
        MainScreenDataProvider mainScreenDataProvider = Mockito.mock(MainScreenDataProvider.class);
        apiResponsePojo.setHourly(hourlyPojo);
        try{
            String val = org.powermock.reflect.Whitebox.invokeMethod(mainScreenDataProvider, "getHourlyIcon", apiResponsePojo);
            Assert.assertEquals("clear-sky",val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHourlySummary(){
        HourlyPojo hourlyPojo = new HourlyPojo();
        hourlyPojo.setSummary("cloudy day");
        MainScreenDataProvider mainScreenDataProvider = Mockito.mock(MainScreenDataProvider.class);
        apiResponsePojo.setHourly(hourlyPojo);
        try{
            String val = org.powermock.reflect.Whitebox.invokeMethod(mainScreenDataProvider, "getHourlySummary", apiResponsePojo);
            Assert.assertNotEquals("clear-sky",val);
            Assert.assertEquals("cloudy day",val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCurrentIconTitles(){
        CurrentlyPojo currentlyPojo = new CurrentlyPojo();
        currentlyPojo.setIcon("clear-sky");
        MainScreenDataProvider mainScreenDataProvider = Mockito.mock(MainScreenDataProvider.class);
        apiResponsePojo.setCurrently(currentlyPojo);
        try{
            String val = org.powermock.reflect.Whitebox.invokeMethod(mainScreenDataProvider, "getCurrentIcon", apiResponsePojo);
            Assert.assertEquals("clear-sky",val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCurrentSummary(){
        CurrentlyPojo currentlyPojo = new CurrentlyPojo();
        currentlyPojo.setSummary("windy");
        MainScreenDataProvider mainScreenDataProvider = Mockito.mock(MainScreenDataProvider.class);
        apiResponsePojo.setCurrently(currentlyPojo);
        try{
            String val = org.powermock.reflect.Whitebox.invokeMethod(mainScreenDataProvider, "getCurrentSummary", apiResponsePojo);
            Assert.assertEquals("windy",val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

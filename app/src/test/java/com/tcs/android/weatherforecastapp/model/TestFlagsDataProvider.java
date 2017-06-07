package com.tcs.android.weatherforecastapp.model;

import com.tcs.android.weatherforecastapp.model.FlagsDataProvider;
import com.tcs.android.weatherforecastapp.view.interfaces.FlagInterface;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by swapnil on 16/03/2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(FlagsDataProvider.class)
public class TestFlagsDataProvider {
    private FlagsDataProvider dataProvider;

    @Mock
    private FlagInterface.modelToPresenter modelToPresenter;

    @Test
    public void testProvideData(){

        dataProvider = PowerMockito.spy(new FlagsDataProvider(modelToPresenter));
        dataProvider.provideData();
        Mockito.verify(dataProvider);
    }
}

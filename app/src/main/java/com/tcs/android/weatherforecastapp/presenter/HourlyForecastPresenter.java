package com.tcs.android.weatherforecastapp.presenter;

import com.tcs.android.weatherforecastapp.model.HourForecastListDataProvider;
import com.tcs.android.weatherforecastapp.model.pojos.ForecastListData;
import com.tcs.android.weatherforecastapp.view.interfaces.ForecastSummaryInterface;

import java.util.List;

/**
 * Created by swapnil on 15/03/2017.
 */

public class HourlyForecastPresenter implements ForecastSummaryInterface.viewToPresenter,ForecastSummaryInterface.modelToPresenter{

    private ForecastSummaryInterface.presenterToView presenterToView;

    public HourlyForecastPresenter(ForecastSummaryInterface.presenterToView presenterToView){
        this.presenterToView = presenterToView;
    }

    @Override
    public void fetchRelevantData() {
        if(null != presenterToView){
            presenterToView.showProgress();
            startDataFetch();
        }
    }

    private void startDataFetch() {
        ForecastSummaryInterface.presenterToModel dataProvider = new HourForecastListDataProvider(this);
        dataProvider.provideData();
    }


    @Override
    public void taskCompleted(List<ForecastListData> forecastListData) {
        if(null != presenterToView){
            presenterToView.hideProgress();
            presenterToView.populateData(forecastListData);
        }
    }
}

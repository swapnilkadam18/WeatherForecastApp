package com.tcs.android.weatherforecastapp.presenter;

import com.tcs.android.weatherforecastapp.model.DailyForecastListDataProvider;
import com.tcs.android.weatherforecastapp.model.pojos.ForecastListData;
import com.tcs.android.weatherforecastapp.view.interfaces.ForecastSummaryInterface;

import java.util.List;

/**
 * Created by swapnil on 16/03/2017.
 */

public class DailyForecastPresenter  implements ForecastSummaryInterface.viewToPresenter,ForecastSummaryInterface.modelToPresenter {
    private ForecastSummaryInterface.presenterToView presenterToView;
    public DailyForecastPresenter(ForecastSummaryInterface.presenterToView presenterToView) {
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
        ForecastSummaryInterface.presenterToModel dataProvider = new DailyForecastListDataProvider(this);
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

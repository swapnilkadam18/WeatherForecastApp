package com.tcs.android.weatherforecastapp.presenter;

import com.tcs.android.weatherforecastapp.model.CurrentMoreInfoDataProvider;
import com.tcs.android.weatherforecastapp.model.DailyForecastDetailInfoDataProvider;
import com.tcs.android.weatherforecastapp.view.interfaces.CurrentMoreInfoInterface;
import com.tcs.android.weatherforecastapp.view.interfaces.ForecastDetailInfoInterface;

import java.util.List;

/**
 * Created by swapnil on 16/03/2017.
 */

public class CurrentInfoPresenter implements CurrentMoreInfoInterface.viewToPresenter, CurrentMoreInfoInterface.modelToPresenter {

    private CurrentMoreInfoInterface.presenterToView presenterToView;

    public CurrentInfoPresenter(CurrentMoreInfoInterface.presenterToView presenterToView) {
        this.presenterToView = presenterToView;
    }

    @Override
    public void fetchRelevantData() {
        if (null != presenterToView) {
            presenterToView.showProgress();
            CurrentMoreInfoInterface.presenterToModel presenterToModel = new CurrentMoreInfoDataProvider(this);
            presenterToModel.provideData();
        }
    }

    @Override
    public void taskCompleted(List<String> moreInfoList) {
        if (null != presenterToView) {
            presenterToView.hideProgress();
            presenterToView.populateData(moreInfoList);
        }
    }
}

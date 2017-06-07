package com.tcs.android.weatherforecastapp.presenter;

import com.tcs.android.weatherforecastapp.model.HourlyForecastDetailInfoDataProvider;
import com.tcs.android.weatherforecastapp.view.interfaces.ForecastDetailInfoInterface;

import java.util.List;

/**
 * Created by swapnil on 16/03/2017.
 */

public class HourlyForecastDetailInfoPresenter implements ForecastDetailInfoInterface.viewToPresenter, ForecastDetailInfoInterface.modelToPresenter {

    private ForecastDetailInfoInterface.presenterToView presenterToView;

    public HourlyForecastDetailInfoPresenter(ForecastDetailInfoInterface.presenterToView presenterToView) {
        this.presenterToView = presenterToView;
    }

    @Override
    public void fetchRelevantData(int position) {
        if (null != presenterToView) {
            presenterToView.showProgress();
            getData(position);
        }
    }

    private void getData(int position) {
        ForecastDetailInfoInterface.presenterToModel presenterToModel = new HourlyForecastDetailInfoDataProvider(this);
        presenterToModel.provideData(position);
    }

    @Override
    public void taskCompleted(List<String> moreInfoList) {
        if (null != presenterToView) {
            presenterToView.hideProgress();
            presenterToView.populateData(moreInfoList);
        }
    }


}

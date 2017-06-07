package com.tcs.android.weatherforecastapp.view.interfaces;

import com.tcs.android.weatherforecastapp.model.pojos.ForecastListData;

import java.util.List;

/**
 * forecast summary interface
 * Created by swapnil on 15/03/2017.
 */

public interface ForecastSummaryInterface {
    interface viewToPresenter {
        void fetchRelevantData();
    }

    interface presenterToModel {
        void provideData();
    }

    interface modelToPresenter{
        void taskCompleted(List<ForecastListData> forecastListData);
    }

    interface presenterToView {
        void showProgress();
        void hideProgress();
        void populateData(List<ForecastListData> forecastListData);
    }
}

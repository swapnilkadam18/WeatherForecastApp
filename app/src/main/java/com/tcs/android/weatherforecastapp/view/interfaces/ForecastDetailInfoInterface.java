package com.tcs.android.weatherforecastapp.view.interfaces;

import java.util.List;

/**
 * forecast detail interface
 * Created by swapnil on 16/03/2017.
 */

public interface ForecastDetailInfoInterface{

    interface viewToPresenter {
        void fetchRelevantData(int position);
    }

    interface presenterToModel {
        void provideData(int position);
    }

    interface modelToPresenter{
        void taskCompleted(List<String> moreInfoList);
    }

    interface presenterToView {
        void showProgress();
        void hideProgress();
        void populateData(List<String> moreInfoList);
    }
}

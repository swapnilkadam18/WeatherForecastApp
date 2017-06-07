package com.tcs.android.weatherforecastapp.view.interfaces;

import java.util.List;

/**
 * more info interface
 * Created by swapnil on 16/03/2017.
 */

public interface CurrentMoreInfoInterface {

    interface viewToPresenter {
        void fetchRelevantData();
    }

    interface presenterToModel {
        void provideData();
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

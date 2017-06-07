package com.tcs.android.weatherforecastapp.view.interfaces;

import com.tcs.android.weatherforecastapp.model.pojos.Flags;

/**
 * flags interface
 * Created by swapnil on 16/03/2017.
 */

public interface FlagInterface {
    interface viewToPresenter {
        void fetchRelevantData();
    }

    interface presenterToModel {
        void provideData();
    }

    interface modelToPresenter{
        void taskCompleted(Flags flagsData);
    }

    interface presenterToView {
        void showProgress();
        void hideProgress();
        void populateData(Flags flagsData);
    }
}

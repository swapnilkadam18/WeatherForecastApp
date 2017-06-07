package com.tcs.android.weatherforecastapp.view.interfaces;

import java.util.List;

/**
 * Created by swapnil on 15/03/2017.
 */

public interface MainActivityInterface {

    interface viewToPresenter {
        void onResume(double lat, double lon);

        void onDestroy();

    }

    interface presenterToModel {
        void provideData();
    }

    interface modelToPresenter{
        void showMessage(String message);
        void taskCompleted();
    }

    interface presenterToView {
        void showProgress();
        void hideProgress();
        void showMessage(String message);
        void proceedToNextScreen();
    }
}

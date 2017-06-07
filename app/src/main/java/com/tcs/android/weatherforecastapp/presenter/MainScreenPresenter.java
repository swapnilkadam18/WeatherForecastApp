package com.tcs.android.weatherforecastapp.presenter;

import com.tcs.android.weatherforecastapp.model.MainScreenDataProvider;
import com.tcs.android.weatherforecastapp.model.pojos.MainScreen;
import com.tcs.android.weatherforecastapp.view.interfaces.MainScreenInterface;

/**
 * Created by swapnil on 15/03/2017.
 */

public class MainScreenPresenter implements MainScreenInterface.viewToPresenter, MainScreenInterface.modelToPresenter {

    private MainScreenInterface.presenterToView presenterToView;
    public MainScreenPresenter(MainScreenInterface.presenterToView presenterToView) {
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
        MainScreenInterface.presenterToModel presenterToModel = new MainScreenDataProvider(this);
        presenterToModel.provideData();
    }

    @Override
    public void showMessage(String message) {
        if(null != presenterToView){
            presenterToView.hideProgress();
            presenterToView.showMessage(message);
        }
    }

    @Override
    public void taskCompleted(MainScreen mainScreen) {
        if(null != presenterToView){
            presenterToView.hideProgress();
            presenterToView.populateData(mainScreen);
        }
    }


}

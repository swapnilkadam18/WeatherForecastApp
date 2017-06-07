package com.tcs.android.weatherforecastapp.presenter;

import com.tcs.android.weatherforecastapp.model.WeatherForecastRealTimeDataProvider;
import com.tcs.android.weatherforecastapp.view.interfaces.MainActivityInterface;

/**
 * Created by swapnil on 15/03/2017.
 */

public class MainActivityPresenter implements MainActivityInterface.viewToPresenter, MainActivityInterface.modelToPresenter {

    private MainActivityInterface.presenterToView mPresenterToView;
    private MainActivityInterface.presenterToModel presenterToModel;

    public MainActivityPresenter(MainActivityInterface.presenterToView presenterToView) {
        mPresenterToView = presenterToView;
    }

    @Override
    public void onResume(double lat, double lon) {

        if(null != mPresenterToView){
            mPresenterToView.showProgress();
            fetchData(lat,lon);
        }else{
            mPresenterToView.hideProgress();
            mPresenterToView.showMessage("Please check internet connection.");
        }

    }

    @Override
    public void onDestroy() {
        mPresenterToView = null;
    }

    public void fetchData(double lat, double lon) {
        WeatherForecastRealTimeDataProvider realTimeDataProvider = new WeatherForecastRealTimeDataProvider(this,lat,lon);
        realTimeDataProvider.execute();
    }

    @Override
    public void showMessage(String message) {
        if(null != mPresenterToView){
            mPresenterToView.hideProgress();
            mPresenterToView.showMessage(message);
        }
    }

    @Override
    public void taskCompleted() {
        if(null != mPresenterToView){
            mPresenterToView.hideProgress();
            mPresenterToView.proceedToNextScreen();
        }
    }
}

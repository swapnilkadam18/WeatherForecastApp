package com.tcs.android.weatherforecastapp.presenter;

import com.tcs.android.weatherforecastapp.model.FlagsDataProvider;
import com.tcs.android.weatherforecastapp.model.pojos.Flags;
import com.tcs.android.weatherforecastapp.view.interfaces.FlagInterface;

/**
 * Created by swapnil on 16/03/2017.
 */

public class FlagsPresenter implements FlagInterface.viewToPresenter,FlagInterface.modelToPresenter {

    private FlagInterface.presenterToView presenterToView;

    public FlagsPresenter(FlagInterface.presenterToView presenterToView) {
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
        FlagInterface.presenterToModel dataProvider = new FlagsDataProvider(this);
        dataProvider.provideData();
    }

    @Override
    public void taskCompleted(Flags flagsData) {
        if(null != presenterToView){
            presenterToView.hideProgress();
            presenterToView.populateData(flagsData);
        }
    }
}

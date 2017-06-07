package com.tcs.android.weatherforecastapp.view.interfaces;
/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.tcs.android.weatherforecastapp.model.pojos.MainScreen;

/**
 * interface between main screen and its other architectural elements
 * Created by swapnil on 15/03/2017.
 */

public interface MainScreenInterface {

    interface viewToPresenter {
        void fetchRelevantData();
    }

    interface presenterToModel {
        void provideData();
    }

    interface modelToPresenter {
        void showMessage(String message);

        void taskCompleted(MainScreen mainScreen);
    }

    interface presenterToView {
        void showProgress();

        void hideProgress();

        void populateData(MainScreen mainScreen);

        void showMessage(String message);
    }
}

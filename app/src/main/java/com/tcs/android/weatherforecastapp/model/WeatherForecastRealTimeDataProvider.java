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
package com.tcs.android.weatherforecastapp.model;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.tcs.android.weatherforecastapp.model.network.NetworkConstants;
import com.tcs.android.weatherforecastapp.model.singleton.WeatherDataSingleton;
import com.tcs.android.weatherforecastapp.model.singleton.singletonpojos.WeatherApiResponsePojo;
import com.tcs.android.weatherforecastapp.view.interfaces.MainActivityInterface;

import java.io.File;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * background thread will use OKHttp to fetch data from the network
 * and provide the expected output.
 *
 * @author swapnil
 */
public class WeatherForecastRealTimeDataProvider extends AsyncTask<Void, Void, String> {

    private MainActivityInterface.modelToPresenter modelToPresenter;
    private double latitude;
    private double longitude;

    public WeatherForecastRealTimeDataProvider(MainActivityInterface.modelToPresenter mModelToPresenter, double lat, double lon) {
        modelToPresenter = mModelToPresenter;
        latitude = lat;
        longitude = lon;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String response;
        try {
            response = getResponse();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response = e.getMessage();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        parseJsonResponse(result);
    }

    /**
     * this method will parse json response
     * using GSON library
     *
     * @param result response received
     */
    private void parseJsonResponse(String result) {
        Gson gson = new Gson();
        WeatherApiResponsePojo response = gson.fromJson(result, WeatherApiResponsePojo.class);
        if(null != response){
            WeatherDataSingleton.getInstance().setApiWeatherData(response);
            modelToPresenter.taskCompleted();
        }else{
            modelToPresenter.showMessage("Data could not be fetched from internet, please try again.");
        }
    }

    /**
     * this method is responsible to build the URL and get the response
     * from the api
     *
     * @return json response
     * @author swapnil
     */
    private String getResponse() {
        try {
            OkHttpClient client = new OkHttpClient();
            String url = NetworkConstants.BASE_URL + File.separator
                    + NetworkConstants.APP_KEY + File.separator
                    + String.valueOf(latitude)+","+String.valueOf(longitude);
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

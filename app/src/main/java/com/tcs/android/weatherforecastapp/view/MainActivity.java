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
package com.tcs.android.weatherforecastapp.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.tcs.android.weatherforecastapp.R;
import com.tcs.android.weatherforecastapp.presenter.MainActivityPresenter;
import com.tcs.android.weatherforecastapp.view.constants.Utilities;
import com.tcs.android.weatherforecastapp.view.constants.ViewRequested;
import com.tcs.android.weatherforecastapp.view.fragments.CurrentWeatherMoreInfoFragment;
import com.tcs.android.weatherforecastapp.view.fragments.DailyForecastFragment;
import com.tcs.android.weatherforecastapp.view.fragments.DailyForecastMoreInfoFragment;
import com.tcs.android.weatherforecastapp.view.fragments.FlagsFragment;
import com.tcs.android.weatherforecastapp.view.fragments.HourlyForecastFragment;
import com.tcs.android.weatherforecastapp.view.fragments.HourlyForecastMoreInfoFragment;
import com.tcs.android.weatherforecastapp.view.fragments.MainScreenFragment;
import com.tcs.android.weatherforecastapp.view.interfaces.CallBackToMainActivity;
import com.tcs.android.weatherforecastapp.view.interfaces.MainActivityInterface;

public class MainActivity extends FragmentActivity implements CallBackToMainActivity, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, MainActivityInterface.presenterToView {

    private static final int PERMISSION_ACCESS_COARSE_LOCATION = 1;
    private GoogleApiClient googleApiClient;

    private FragmentManager fragmentManager;

    private ProgressBar progressBar;

    private MainActivityInterface.viewToPresenter viewToPresenter;

    private Bundle savedInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        fragmentManager = getSupportFragmentManager();
        savedInstance = savedInstanceState;

        //Since we need GoogleApiClient to use location services
        //we need to first check for internet connection,
        //if internet is present then we should check for gps coordinates
        // and move further
        checkForInternet();
    }

    @Override
    public void changeFragment(ViewRequested viewRequested, int position) {
        Fragment selectedFragment = null;
        if (viewRequested == ViewRequested.DAILY_FORECAST) {
            selectedFragment = DailyForecastFragment.newInstance();
        } else if (viewRequested == ViewRequested.HOURLY_FORECAST) {
            selectedFragment = HourlyForecastFragment.newInstance();
        } else if (viewRequested == ViewRequested.FLAGS) {
            selectedFragment = FlagsFragment.newInstance();
        } else if (viewRequested == ViewRequested.CURRENT_WEATHER_MORE_INFO) {
            selectedFragment = CurrentWeatherMoreInfoFragment.newInstance();
        } else if (viewRequested == ViewRequested.DAILY_FORECAST_MORE_INFO) {
            selectedFragment = DailyForecastMoreInfoFragment.newInstance(position);
        } else if (viewRequested == ViewRequested.HOURLY_FORECAST_MORE_INFO) {
            selectedFragment = HourlyForecastMoreInfoFragment.newInstance(position);
        }
        if (null != selectedFragment) {
            drawFragment(selectedFragment);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ACCESS_COARSE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // All good!
                getLastKnownLocation();
            } else {
                showMessage(Utilities.showAccessDeniedError);
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 23) {
            // Marshmallow+
            checkForLocation();
        } else {
            // get location here
            getLastKnownLocation();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //do nothing
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //do nothing
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        if(googleApiClient != null){
            googleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (null != viewToPresenter) {
            viewToPresenter.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        lockScreenOrientation();
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        unlockScreenOrientation();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void proceedToNextScreen() {
        if (null == savedInstance) {
            drawDefaultFragment();
        }

    }

    private void checkForInternet() {
        if (isNetworkAvailable()) {
            googleApiClient = new GoogleApiClient
                    .Builder(this, this, this)
                    .addApi(LocationServices.API)
                    .build();
        } else {
            showMessage("Internet not connected");
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * this method will request permission if not provided
     */
    private void checkForLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_ACCESS_COARSE_LOCATION);
        } else {
            //permission already granted
            getLastKnownLocation();
        }
    }

    /**
     * default fragment loader in the frameLayout,
     * since we don't need to view empty frameLayout
     * we are not adding transaction in the backStack
     */
    private void drawDefaultFragment() {
        // loading the MainScreen fragment without backStack
        Fragment mainScreenFragment = MainScreenFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, mainScreenFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    /**
     * Generic method to load new fragment in the frameLayout
     *
     * @param displayFragment provide the fragment which you want to display
     */
    private void drawFragment(Fragment displayFragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, displayFragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    /**
     * this method will provide last known location of the user
     */
    private void getLastKnownLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

            double lat = lastLocation.getLatitude();
            double lon = lastLocation.getLongitude();
            //if location is provided then only we can provide the data related to it.
            viewToPresenter = new MainActivityPresenter(this);
            viewToPresenter.onResume(lat, lon);
        }
    }

    /**
     * to prevent crash on screen orientation in middle of network operation
     * we will be locking orientation of the screen temporarily.
     *
     * @author swapnil
     */
    private void lockScreenOrientation() {
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    /**
     * to prevent crash on screen orientation in middle of network operation
     * we will be unlocking orientation after locking of screen was done.
     *
     * @author swapnil
     */
    private void unlockScreenOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }
}

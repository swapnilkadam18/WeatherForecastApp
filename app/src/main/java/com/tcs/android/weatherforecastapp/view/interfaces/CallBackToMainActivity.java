package com.tcs.android.weatherforecastapp.view.interfaces;

import com.tcs.android.weatherforecastapp.view.constants.ViewRequested;

/**
 * call back to main activity
 * Created by swapnil on 14/03/2017.
 */

public interface CallBackToMainActivity {
    void changeFragment(ViewRequested viewRequested, int position);
}

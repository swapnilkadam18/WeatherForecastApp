package com.tcs.android.weatherforecastapp.view.constants;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author swapnil
 *         this class is responsible to hold all the common code
 *         which will be used often, to reduce the code duplicacy and
 *         easier management.
 */

public class Utilities {

    private static final String CLEAR_SKY = "clear-day";
    private static final String FOG = "fog";
    private static final String RAIN = "rain";
    private static final String LIGHT_CLOUDS = "partly-cloudy--day";
    private static final String CLOUDS = "cloudy";
    //to provide key to the bundle while fragment communications
    public static final String POSITION = "position";
    public static final String showAccessDeniedError = "Permission allows us to access location data." +
            "\n Please allow for additional functionality.";
    public static final String interfaceImplError = " must implement CallBackToMainActivity";

    /**
     * @param context      to determine which fragment is calling
     * @param recyclerList provide list which needs styling
     *                     <p>
     *                     this method is responsible to show
     *                     data in recycler view and separating
     *                     items within them vertically.
     * @author swapnil
     */
    public static void initRecyclerView(Context context, RecyclerView recyclerList) {
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerList.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerList.getContext(),
                linearLayoutManager.getOrientation());
        recyclerList.addItemDecoration(dividerItemDecoration);
    }

    /**
     * since we have only this many icons only this conditions will have
     * different icons rest it will show default icon
     *
     * @param currentIcon value of current icon
     * @param context     to access getResources
     * @return id of the drawable
     */
    public static int getIcon(String currentIcon, Context context) {
        //default
        int resId = context.getResources().getIdentifier("com.tcs.android.weatherforecastapp:drawable/" + "art_clear", null, null);
        if (!currentIcon.isEmpty()) {
            if (currentIcon.equalsIgnoreCase(FOG)) {
                resId = context.getResources().getIdentifier("com.tcs.android.weatherforecastapp:drawable/" + "art_fog", null, null);
            } else if (currentIcon.equalsIgnoreCase(CLEAR_SKY)) {
                resId = context.getResources().getIdentifier("com.tcs.android.weatherforecastapp:drawable/" + "art_clear", null, null);
            } else if (currentIcon.equalsIgnoreCase(RAIN)) {
                resId = context.getResources().getIdentifier("com.tcs.android.weatherforecastapp:drawable/" + "art_rain", null, null);
            } else if (currentIcon.equalsIgnoreCase(LIGHT_CLOUDS)) {
                resId = context.getResources().getIdentifier("com.tcs.android.weatherforecastapp:drawable/" + "art_light_clouds", null, null);
            } else if (currentIcon.equalsIgnoreCase(CLOUDS)) {
                resId = context.getResources().getIdentifier("com.tcs.android.weatherforecastapp:drawable/" + "art_clouds", null, null);
            }
        }
        return resId;
    }
}

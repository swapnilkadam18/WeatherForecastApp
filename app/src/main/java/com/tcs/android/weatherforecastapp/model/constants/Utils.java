package com.tcs.android.weatherforecastapp.model.constants;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author swapnil
 *         this class will hold all the generic methods required by model package
 */

public class Utils {

    public static final String APPARENT_TEMP_MAX_TIME = "Temp max time: ";
    public static final String APPARENT_TEMP_MIN_TIME = "Temp min time: ";
    public static final String APPARENT_TEMP = "Apparent temp: ";
    public static final String WIND_SPEED = "Wind Speed: ";
    public static final String notAvailable = " Not Available";
    public static final String APPARENT_TEMP_MAX = "Apparent max temp: ";
    public static final String APPARENT_TEMP_MIN = "Apparent min temp: ";
    public static final String CLOUD_COVER = "Cloud cover: ";
    public static final String DEW_POINT = "Dew point: ";
    public static final String HUMIDITY = "Humidity: ";
    public static final String MOON_PHASE = "Moon phase: ";
    public static final String OZONE = "Ozone: ";
    public static final String PRECIP_INTENSITY = "Precip intensity: ";
    public static final String PRECIP_INTENSITY_MAX = "Precip max intensity: ";
    public static final String PRECIP_INTENSITY_MAX_TIME = "Precip max time intensity: ";
    public static final String PRECIP_PROBABILITY = "Precip probability: ";
    public static final String TEMPERATURE = "Temperature: ";
    public static final String WIND_BEARING = "Wind bearing: ";

    public static DecimalFormat decimalFormat = new DecimalFormat("##.###");
    private static SimpleDateFormat hourlyFormat = new SimpleDateFormat("h:mm a", Locale.US);
    private static SimpleDateFormat dailyFormat = new SimpleDateFormat("EEE, MMM d", Locale.US);

    /**
     * generic method to remove quotes
     *
     * @param quoteString provide the string with quotes
     * @return quotes free string
     */
    public static String getQuotesFreeString(String quoteString) {
        return quoteString.replaceAll("^\"|\"$", "");
    }

    /**
     * The cast to (long) is very important: without it the integer overflows.
     * Multiply by 1000, since java is expecting milliseconds
     * @param unixTimeStamp unix time stamp
     * @return formatted time
     */
    public static String convertToDailyFormat(Integer unixTimeStamp) {
        java.util.Date time = new java.util.Date((long) unixTimeStamp * 1000);
        return dailyFormat.format(time);
    }

    /**
     * The cast to (long) is very important: without it the integer overflows.
     * Multiply by 1000, since java is expecting milliseconds
     * @param unixTimeStamp unix time stamp
     * @return formatted time
     */
    public static String convertToHourlyFormat(Integer unixTimeStamp) {
        java.util.Date time = new java.util.Date((long) unixTimeStamp * 1000);
        return hourlyFormat.format(time);
    }

    /**
     * add F as suffix
     * @param temperature value requesting suffix
     * @return temp with F
     */
    public static String addSuffix(Double temperature) {
        String fahrenheit = " \u2109";
        return String.valueOf(temperature) + fahrenheit;
    }
}

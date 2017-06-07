package com.tcs.android.weatherforecastapp.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tcs.android.weatherforecastapp.R;
import com.tcs.android.weatherforecastapp.model.pojos.MainScreen;
import com.tcs.android.weatherforecastapp.presenter.MainScreenPresenter;
import com.tcs.android.weatherforecastapp.view.constants.Utilities;
import com.tcs.android.weatherforecastapp.view.constants.ViewRequested;
import com.tcs.android.weatherforecastapp.view.interfaces.CallBackToMainActivity;
import com.tcs.android.weatherforecastapp.view.interfaces.MainScreenInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainScreenFragment extends Fragment implements View.OnClickListener, MainScreenInterface.presenterToView {

    private View mainScreenHolder;
    private View dailyForecast;
    private View hourlyForecast;
    private TextView dailyForecastTitle;
    private TextView hourlyForecastTitle;
    private TextView dailyForecastSummary;
    private TextView hourlyForecastSummary;
    private ImageView dailyForecastIcon;
    private ImageView hourlyForecastIcon;
    private TextView currentWeatherTime;
    private TextView currentWeatherSummary;
    private TextView currentWeatherTemperature;
    private TextView currentWeatherCoordinates;
    private TextView currentWeatherMoreInfo;
    private ImageView currentWeatherIcon;
    private TextView flags;
    private ProgressBar progressBar;

    private CallBackToMainActivity callBackToMainActivityListener;

    private MainScreenInterface.viewToPresenter viewToPresenter;

    public MainScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callBackToMainActivityListener = (CallBackToMainActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+ Utilities.interfaceImplError);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewToPresenter = new MainScreenPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_screen_holder, container, Boolean.FALSE);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewToPresenter.fetchRelevantData();
        initClickListeners();
    }

    @Override
    public void onClick(View view) {
        if (view == dailyForecast) {
            callBackToMainActivityListener.changeFragment(ViewRequested.DAILY_FORECAST, 0);
        } else if (view == hourlyForecast) {
            callBackToMainActivityListener.changeFragment(ViewRequested.HOURLY_FORECAST, 0);
        } else if (view == flags) {
            callBackToMainActivityListener.changeFragment(ViewRequested.FLAGS, 0);
        } else if (view == currentWeatherMoreInfo) {
            callBackToMainActivityListener.changeFragment(ViewRequested.CURRENT_WEATHER_MORE_INFO, 0);
        }
    }

    private void initClickListeners() {
        dailyForecast.setOnClickListener(this);
        hourlyForecast.setOnClickListener(this);
        flags.setOnClickListener(this);
        currentWeatherMoreInfo.setOnClickListener(this);
    }

    public static Fragment newInstance() {
        return new MainScreenFragment();
    }

    /**
     * to initialize all the views
     *
     * @param rootView provide view which holds all views
     */
    private void initViews(View rootView) {
        mainScreenHolder = rootView.findViewById(R.id.mainScreenHolder);
        currentWeatherTime = (TextView) rootView.findViewById(R.id.tv_current_time);
        currentWeatherSummary = (TextView) rootView.findViewById(R.id.tv_current_summary);
        currentWeatherTemperature = (TextView) rootView.findViewById(R.id.tv_current_temp);
        currentWeatherCoordinates = (TextView) rootView.findViewById(R.id.tv_current_coordinates);
        currentWeatherMoreInfo = (TextView) rootView.findViewById(R.id.tv_current_more_info);
        currentWeatherIcon = (ImageView) rootView.findViewById(R.id.img_current_weather_icon);
        flags = (TextView) rootView.findViewById(R.id.tv_flags);
        dailyForecast = rootView.findViewById(R.id.daily_forecast_view_container);
        hourlyForecast = rootView.findViewById(R.id.hourly_forecast_view_container);
        dailyForecastTitle = (TextView) dailyForecast.findViewById(R.id.tv_container_title);
        hourlyForecastTitle = (TextView) hourlyForecast.findViewById(R.id.tv_container_title);
        dailyForecastSummary = (TextView) dailyForecast.findViewById(R.id.tv_container_summary);
        hourlyForecastSummary = (TextView) hourlyForecast.findViewById(R.id.tv_container_summary);
        dailyForecastIcon = (ImageView) dailyForecast.findViewById(R.id.img_weather_icon);
        hourlyForecastIcon = (ImageView) hourlyForecast.findViewById(R.id.img_weather_icon);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress);
    }

    @Override
    public void showProgress() {
        if (View.VISIBLE == mainScreenHolder.getVisibility()) {
            mainScreenHolder.setVisibility(View.INVISIBLE);
        }
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        mainScreenHolder.setVisibility(View.VISIBLE);
    }

    @Override
    public void populateData(MainScreen mainScreen) {
        currentWeatherTime.setText(mainScreen.getTime());
        currentWeatherSummary.setText(mainScreen.getSummary());
        currentWeatherTemperature.setText(mainScreen.getTemperature());
        currentWeatherCoordinates.setText(mainScreen.getCoordinates());
        dailyForecastTitle.setText(getResources().getString(R.string.strDailyForecastTitle));
        dailyForecastSummary.setText(mainScreen.getDailySummary());
        hourlyForecastTitle.setText(getResources().getString(R.string.strHourlyForecastTitle));
        hourlyForecastSummary.setText(mainScreen.getHourlySummary());
        currentWeatherIcon.setImageResource(Utilities.getIcon(mainScreen.getCurrentIcon(), getActivity()));
        dailyForecastIcon.setImageResource(Utilities.getIcon(mainScreen.getDailyIcon(), getActivity()));
        hourlyForecastIcon.setImageResource(Utilities.getIcon(mainScreen.getHourlyIcon(), getActivity()));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}

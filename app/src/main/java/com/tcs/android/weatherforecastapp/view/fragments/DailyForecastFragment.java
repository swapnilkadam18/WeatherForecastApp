package com.tcs.android.weatherforecastapp.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.tcs.android.weatherforecastapp.R;
import com.tcs.android.weatherforecastapp.model.pojos.ForecastListData;
import com.tcs.android.weatherforecastapp.presenter.DailyForecastPresenter;
import com.tcs.android.weatherforecastapp.presenter.HourlyForecastPresenter;
import com.tcs.android.weatherforecastapp.view.adapters.DailyForecastListAdapter;
import com.tcs.android.weatherforecastapp.view.adapters.HourlyForecastListAdapter;
import com.tcs.android.weatherforecastapp.view.constants.RecyclerTouchListener;
import com.tcs.android.weatherforecastapp.view.constants.Utilities;
import com.tcs.android.weatherforecastapp.view.constants.ViewRequested;
import com.tcs.android.weatherforecastapp.view.interfaces.CallBackToMainActivity;
import com.tcs.android.weatherforecastapp.view.interfaces.ClickListener;
import com.tcs.android.weatherforecastapp.view.interfaces.ForecastSummaryInterface;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyForecastFragment extends Fragment implements ForecastSummaryInterface.presenterToView {

    private View rootView;
    private CallBackToMainActivity callBackToMainActivityListener;
    private RecyclerView dailyForecastList;
    private DailyForecastListAdapter dailyForecastListAdapter;
    private ProgressBar progressBar;
    private ForecastSummaryInterface.viewToPresenter viewToPresenter;

    public DailyForecastFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callBackToMainActivityListener = (CallBackToMainActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement CallBackToMainActivity");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewToPresenter = new DailyForecastPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_recycler_view_container, container, Boolean.FALSE);
        dailyForecastList = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewToPresenter.fetchRelevantData();
    }

    public static DailyForecastFragment newInstance() {
        return new DailyForecastFragment();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void populateData(List<ForecastListData> forecastListData) {
        populateList(forecastListData);
    }

    private void populateList(List<ForecastListData> forecastListData) {
        dailyForecastListAdapter = new DailyForecastListAdapter(forecastListData, getActivity());
        dailyForecastList.setAdapter(dailyForecastListAdapter);
        Utilities.initRecyclerView(getActivity(), dailyForecastList);
        dailyForecastList.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), dailyForecastList, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                callBackToMainActivityListener.changeFragment(ViewRequested.DAILY_FORECAST_MORE_INFO, position);
            }
        }));
    }
}

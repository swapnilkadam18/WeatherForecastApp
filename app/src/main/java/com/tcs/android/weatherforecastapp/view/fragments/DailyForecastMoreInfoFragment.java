package com.tcs.android.weatherforecastapp.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.tcs.android.weatherforecastapp.R;
import com.tcs.android.weatherforecastapp.presenter.DailyForecastDetailInfoPresenter;
import com.tcs.android.weatherforecastapp.view.adapters.CurrentWeatherMoreInfoListAdapter;
import com.tcs.android.weatherforecastapp.view.constants.Utilities;
import com.tcs.android.weatherforecastapp.view.interfaces.ForecastDetailInfoInterface;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyForecastMoreInfoFragment extends Fragment implements ForecastDetailInfoInterface.presenterToView {

    private int selectedPosition;
    private RecyclerView moreInfoRecylerView;
    private ProgressBar progressBar;
    private ForecastDetailInfoInterface.viewToPresenter viewToPresenter;

    public DailyForecastMoreInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        selectedPosition = bundle.getInt(Utilities.POSITION);
        viewToPresenter = new DailyForecastDetailInfoPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler_view_container, container, Boolean.FALSE);
        moreInfoRecylerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewToPresenter.fetchRelevantData(selectedPosition);
    }

    /**
     * This method will create a instance of the fragment and
     * position will enable to show appropriate data
     *
     * @param position to fetch the data at the requested position
     * @return fragment
     */
    public static Fragment newInstance(int position) {
        DailyForecastMoreInfoFragment dailyForecastMoreInfoFragment = new DailyForecastMoreInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Utilities.POSITION, position);
        dailyForecastMoreInfoFragment.setArguments(bundle);
        return dailyForecastMoreInfoFragment;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void populateData(List<String> moreInfoList) {
        CurrentWeatherMoreInfoListAdapter currentForecastListAdapter = new CurrentWeatherMoreInfoListAdapter(moreInfoList);
        moreInfoRecylerView.setAdapter(currentForecastListAdapter);
        Utilities.initRecyclerView(getActivity(), moreInfoRecylerView);
    }

}

package com.tcs.android.weatherforecastapp.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tcs.android.weatherforecastapp.R;
import com.tcs.android.weatherforecastapp.model.pojos.Flags;
import com.tcs.android.weatherforecastapp.presenter.FlagsPresenter;
import com.tcs.android.weatherforecastapp.view.interfaces.FlagInterface;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlagsFragment extends Fragment implements FlagInterface.presenterToView{

    private View rootView;
    private TextView sourcesTextView;
    private TextView stationsTextView;
    private TextView unitsTextView;
    private ProgressBar progressBar;
    private FlagInterface.viewToPresenter viewToPresenter;

    public FlagsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewToPresenter = new FlagsPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_flags,container,Boolean.FALSE);
        sourcesTextView = (TextView) rootView.findViewById(R.id.tv_flags_sources);
        stationsTextView = (TextView) rootView.findViewById(R.id.tv_flags_stations);
        unitsTextView = (TextView) rootView.findViewById(R.id.tv_flags_units);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewToPresenter.fetchRelevantData();
    }

    public static Fragment newInstance() {
        return new FlagsFragment();
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
    public void populateData(Flags flagsData) {
        unitsTextView.setText(flagsData.getUnits());
        if(null != flagsData.getSources() && !flagsData.getSources().isEmpty()){
            for(String sourcesData : flagsData.getSources()){
                sourcesTextView.append("Sources : "+sourcesData + "\n");
            }
        }
        if(null != flagsData.getIsdStations() && !flagsData.getIsdStations().isEmpty()){
            for(String isdStationsData : flagsData.getIsdStations()){
                sourcesTextView.append("isdStations : "+isdStationsData + "\n");
            }
        }
    }
}

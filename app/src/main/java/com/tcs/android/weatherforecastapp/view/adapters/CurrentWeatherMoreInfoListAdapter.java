package com.tcs.android.weatherforecastapp.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tcs.android.weatherforecastapp.R;

import java.util.List;

/**
 * Created by swapnil on 15/03/2017.
 */

public class CurrentWeatherMoreInfoListAdapter extends RecyclerView.Adapter<CurrentWeatherMoreInfoListAdapter.CurrentWeatherMoreInfoViewHolder> {

    private List<String> mForecastListData;
    public CurrentWeatherMoreInfoListAdapter(List<String> forecastListData) {
        mForecastListData = forecastListData;
    }


    @Override
    public CurrentWeatherMoreInfoListAdapter.CurrentWeatherMoreInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_weather_more_info_list_item, parent, Boolean.FALSE);
        return new CurrentWeatherMoreInfoListAdapter.CurrentWeatherMoreInfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CurrentWeatherMoreInfoListAdapter.CurrentWeatherMoreInfoViewHolder holder, int position) {
        String infoData = mForecastListData.get(position);
        holder.infoTextView.setText(infoData);
    }

    @Override
    public int getItemCount() {
        return mForecastListData.size();
    }

    public class CurrentWeatherMoreInfoViewHolder extends RecyclerView.ViewHolder {
        private TextView infoTextView;

        private CurrentWeatherMoreInfoViewHolder(View itemView) {
            super(itemView);
            infoTextView = (TextView) itemView.findViewById(R.id.tv_current_more_info_list_item);
        }
    }
}

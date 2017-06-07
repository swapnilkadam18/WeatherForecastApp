package com.tcs.android.weatherforecastapp.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tcs.android.weatherforecastapp.R;
import com.tcs.android.weatherforecastapp.model.pojos.ForecastListData;
import com.tcs.android.weatherforecastapp.view.constants.Utilities;

import java.util.List;

/**
 * Created by swapnil on 15/03/2017.
 */

public class DailyForecastListAdapter extends RecyclerView.Adapter<DailyForecastListAdapter.DailyForecastViewHolder> {

    private List<ForecastListData> mForecastListData;
    private Context mContext;

    public DailyForecastListAdapter(List<ForecastListData> forecastListData, Context context) {
        mForecastListData = forecastListData;
        mContext = context;
    }

    @Override
    public DailyForecastListAdapter.DailyForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_summary_view, parent, Boolean.FALSE);
        return new DailyForecastListAdapter.DailyForecastViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DailyForecastListAdapter.DailyForecastViewHolder holder, int position) {
        ForecastListData forecastListData = mForecastListData.get(position);
        holder.hourlyWeatherIcon.setImageResource(Utilities.getIcon(forecastListData.getIconTitle(), mContext));
        holder.summaryTextView.setText(forecastListData.getSummary());
        holder.hourTextView.setText(forecastListData.getTime());
    }

    @Override
    public int getItemCount() {
        return mForecastListData.size();
    }

    public class DailyForecastViewHolder extends RecyclerView.ViewHolder {
        private TextView hourTextView;
        private TextView summaryTextView;
        private ImageView hourlyWeatherIcon;

        private DailyForecastViewHolder(View itemView) {
            super(itemView);
            hourTextView = (TextView) itemView.findViewById(R.id.tv_container_title);
            summaryTextView = (TextView) itemView.findViewById(R.id.tv_container_summary);
            hourlyWeatherIcon = (ImageView) itemView.findViewById(R.id.img_weather_icon);
        }
    }
}

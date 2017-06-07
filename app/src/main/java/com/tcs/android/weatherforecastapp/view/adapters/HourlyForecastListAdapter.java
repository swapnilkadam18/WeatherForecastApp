package com.tcs.android.weatherforecastapp.view.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tcs.android.weatherforecastapp.R;
import com.tcs.android.weatherforecastapp.model.constants.Utils;
import com.tcs.android.weatherforecastapp.model.pojos.ForecastListData;
import com.tcs.android.weatherforecastapp.view.constants.Utilities;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by swapnil on 15/03/2017.
 */

public class HourlyForecastListAdapter extends RecyclerView.Adapter<HourlyForecastListAdapter.HourlyForecastViewHolder>  {

    private List<ForecastListData> mForecastListData;
    private Context mContext;
    public HourlyForecastListAdapter(List<ForecastListData> forecastListData, Context context){
        mForecastListData = forecastListData;
        mContext = context;
    }

    @Override
    public HourlyForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_summary_view, parent, Boolean.FALSE);
        return new HourlyForecastViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HourlyForecastListAdapter.HourlyForecastViewHolder holder, int position) {
        ForecastListData forecastListData = mForecastListData.get(position);
        holder.hourlyWeatherIcon.setImageResource(Utilities.getIcon(forecastListData.getIconTitle(),mContext));
        holder.summaryTextView.setText(forecastListData.getSummary());
        holder.hourTextView.setText(forecastListData.getTime());
    }

    @Override
    public int getItemCount() {
        return mForecastListData.size();
    }

    public class HourlyForecastViewHolder extends RecyclerView.ViewHolder {
        private TextView hourTextView;
        private TextView summaryTextView;
        private ImageView hourlyWeatherIcon;

        private HourlyForecastViewHolder(View itemView) {
            super(itemView);
            hourTextView = (TextView) itemView.findViewById(R.id.tv_container_title);
            summaryTextView = (TextView) itemView.findViewById(R.id.tv_container_summary);
            hourlyWeatherIcon = (ImageView) itemView.findViewById(R.id.img_weather_icon);
        }
    }
}

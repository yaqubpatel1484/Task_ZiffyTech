package com.myproject.ziffytech.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myproject.ziffytech.R;
import com.myproject.ziffytech.models.List;
import com.myproject.ziffytech.utils.UtilMethods;

import androidx.fragment.app.Fragment;


public class DetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.details_fragment, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View rootView) {

        if(getArguments() != null) {
            int position = getArguments().getInt(HomeFragment.POSITION);
            String mCityName = getArguments().getString(HomeFragment.CITY_NAME);
            List mItem = getArguments().getParcelable(HomeFragment.ITEM);

            TextView cityName = rootView.findViewById(R.id.details_cityName);
            TextView dateTime = rootView.findViewById(R.id.details_dateTime);
            TextView pressure = rootView.findViewById(R.id.details_pressure);
            TextView humidity = rootView.findViewById(R.id.details_humidity);
            TextView whether = rootView.findViewById(R.id.details_weather);
            TextView speed = rootView.findViewById(R.id.details_speed);
            TextView degree = rootView.findViewById(R.id.details_degree);
            TextView clouds = rootView.findViewById(R.id.details_clouds);
            TextView snow = rootView.findViewById(R.id.details_rain);

            TextView day = rootView.findViewById(R.id.details_day);
            TextView min = rootView.findViewById(R.id.details_min);
            TextView max = rootView.findViewById(R.id.details_max);
            TextView night = rootView.findViewById(R.id.details_night);
            TextView evening = rootView.findViewById(R.id.details_evening);
            TextView morning = rootView.findViewById(R.id.details_morning);

            cityName.setText(mCityName);



            dateTime.setText(UtilMethods.convertDate(mItem.getDt()));
            pressure.setText(String.valueOf(mItem.getPressure()));
            humidity.setText(String.valueOf(mItem.getHumidity()));
            whether.setText(String.valueOf(mItem.getWeather().get(0).getDescription()));
            speed.setText(String.valueOf(mItem.getSpeed()));
            degree.setText(String.valueOf(mItem.getSpeed()));
            clouds.setText(String.valueOf(mItem.getClouds()));
            snow.setText(String.valueOf(mItem.getRain()));


            day.setText(String.valueOf(mItem.getTemp().getDay()));
            min.setText(String.valueOf(mItem.getTemp().getMin()));
            max.setText(String.valueOf(mItem.getTemp().getMax()));
            night.setText(String.valueOf(mItem.getTemp().getNight()));
            evening.setText(String.valueOf(mItem.getTemp().getEve()));
            morning.setText(String.valueOf(mItem.getTemp().getMorn()));

        }else{
            UtilMethods.mt(getActivity(),"Error");
        }
    }




}
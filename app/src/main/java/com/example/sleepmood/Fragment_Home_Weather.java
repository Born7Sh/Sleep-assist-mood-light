package com.example.sleepmood;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class Fragment_Home_Weather extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment__home__weather, container, false);

        ImageView home_9_Img = (ImageView)view.findViewById(R.id.home_9_Img);
        TextView home_9_Temp = (TextView)view.findViewById(R.id.home_9_Temp);
        TextView home_9_Hum = (TextView)view.findViewById(R.id.home_9_Hum);

        ImageView home_12_Img = (ImageView)view.findViewById(R.id.home_12_Img);
        TextView home_12_Temp = (TextView)view.findViewById(R.id.home_12_Temp);
        TextView home_12_Hum = (TextView)view.findViewById(R.id.home_12_Hum);

        ImageView home_15_Img = (ImageView)view.findViewById(R.id.home_15_Img);
        TextView home_15_Temp = (TextView)view.findViewById(R.id.home_15_Temp);
        TextView home_15_Hum = (TextView)view.findViewById(R.id.home_15_Hum);

        ImageView home_18_Img = (ImageView)view.findViewById(R.id.home_18_Img);
        TextView home_18_Temp = (TextView)view.findViewById(R.id.home_18_Temp);
        TextView home_18_Hum = (TextView)view.findViewById(R.id.home_18_Hum);

        ImageView home_21_Img = (ImageView)view.findViewById(R.id.home_21_Img);
        TextView home_21_Temp = (TextView)view.findViewById(R.id.home_21_Temp);
        TextView home_21_Hum = (TextView)view.findViewById(R.id.home_21_Hum);


        ImageView location_9_Img = (ImageView)view.findViewById(R.id.location_9_Img);
        TextView location_9_Temp = (TextView)view.findViewById(R.id.location_9_Temp);
        TextView location_9_Hum = (TextView)view.findViewById(R.id.location_9_Hum);

        ImageView location_12_Img = (ImageView)view.findViewById(R.id.location_12_Img);
        TextView location_12_Temp = (TextView)view.findViewById(R.id.location_12_Temp);
        TextView location_12_Hum = (TextView)view.findViewById(R.id.location_12_Hum);

        ImageView location_15_Img = (ImageView)view.findViewById(R.id.location_15_Img);
        TextView location_15_Temp = (TextView)view.findViewById(R.id.location_15_Temp);
        TextView location_15_Hum = (TextView)view.findViewById(R.id.location_15_Hum);

        ImageView location_18_Img = (ImageView)view.findViewById(R.id.location_18_Img);
        TextView location_18_Temp = (TextView)view.findViewById(R.id.location_18_Temp);
        TextView location_18_Hum = (TextView)view.findViewById(R.id.location_18_Hum);

        ImageView location_21_Img = (ImageView)view.findViewById(R.id.location_21_Img);
        TextView location_21_Temp = (TextView)view.findViewById(R.id.location_21_Temp);
        TextView location_21_Hum = (TextView)view.findViewById(R.id.location_21_Hum);


        return inflater.inflate(R.layout.fragment__home__weather, container, false);
    }
}
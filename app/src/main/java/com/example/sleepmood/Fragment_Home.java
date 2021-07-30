package com.example.sleepmood;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class Fragment_Home extends Fragment {
    MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity)getActivity();
    }

    public void onDetach(){
        super.onDetach();
        activity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment__home, container, false);

        ImageView weatherInfo = (ImageView) rootView.findViewById(R.id.weatherInfo);
        ImageView tema = (ImageView) rootView.findViewById(R.id.tema);
        ImageView alramSetting = (ImageView) rootView.findViewById(R.id.alramSetting);

        Button sleepReady = (Button) rootView.findViewById(R.id.sleepReady);
        Button sleepStart = (Button) rootView.findViewById(R.id.sleepStart);

        sleepStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChange("alarmAdd");
            }
        });

        return rootView;
    }
}
package com.example.sleepmood;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Fragment_Home_SleepStart extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment__home__sleep_start, container, false);

        ImageView water = (ImageView) rootView.findViewById(R.id.water);
        ImageView awake = (ImageView) rootView.findViewById(R.id.awake);
        ImageView toilet = (ImageView) rootView.findViewById(R.id.toilet);

        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "TextView 입니다.", Toast.LENGTH_SHORT).show();

            }
        });


        return inflater.inflate(R.layout.fragment__home__sleep_start, container, false);



    }
}
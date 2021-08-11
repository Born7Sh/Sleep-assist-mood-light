package com.example.sleepmood;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;


public class Fragment_Home extends Fragment {
    MainActivity activity;

    // 사용자의 모든 정보 21.08.01
    // 앱 켜지자 마자 데이터 DB에서 다 받아오면 좋을거 같은데
    // 여기서 다 받아와지게 하자
    private SharedViewModel sharedViewModel;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment__home, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        ArrayList<Integer> alarmWeek = new ArrayList<Integer>(0);

        AlarmData a = new AlarmData("ho", "ho", alarmWeek);
        AlarmData b = new AlarmData("no", "no", alarmWeek);
        sharedViewModel.addLiveAlarmData(a);
        sharedViewModel.addLiveAlarmData(b);

        ImageView weatherInfo = (ImageView) view.findViewById(R.id.weatherInfo);
        ImageView tema = (ImageView) view.findViewById(R.id.tema);
        ImageView alramSetting = (ImageView) view.findViewById(R.id.alramSetting);

        Button sleepReady = (Button) view.findViewById(R.id.sleepReady);
        Button sleepStart = (Button) view.findViewById(R.id.sleepStart);

        alramSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ArrayList<Integer> alarmWeek = new ArrayList<Integer>(0);
                AlarmData a = new AlarmData("ho", "ho", alarmWeek);
//                AlarmData b = new AlarmData("no","no", alarmWeek);
                sharedViewModel.addLiveAlarmData(a);
//                sharedViewModel.addLiveAlarmData(b);

                activity.onFragmentChange("alarmList");
            }
        });

        weatherInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    activity.onFragmentChange("weatherInfo");
            }
        });

        tema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    activity.onFragmentChange("tema");
            }
        });



        sleepReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChange("sleepReady");
            }
        });

        sleepStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChange("sleepStart");
            }
        });
    }
}
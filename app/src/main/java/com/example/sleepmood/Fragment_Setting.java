package com.example.sleepmood;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

public class Fragment_Setting extends Fragment {

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
        return inflater.inflate(R.layout.fragment__setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       TextView setting_User_Edit = (TextView) view.findViewById(R.id.setting_User_Edit);
       TextView setting_User_Drop = (TextView) view.findViewById(R.id.setting_User_Drop);

       TextView setting_AppInfo = (TextView) view.findViewById(R.id.setting_AppInfo);
       TextView setting_Help = (TextView) view.findViewById(R.id.setting_Help);
       TextView setting_Question = (TextView) view.findViewById(R.id.setting_Question);

       setting_User_Edit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               activity.onFragmentChange("setting_User_Edit");
           }
       });

        setting_User_Drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChange("setting_User_Drop");
            }
        });

        setting_AppInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChange("setting_AppInfo");
            }
        });

        setting_Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChange("setting_Help");
            }
        });

        setting_Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { activity.onFragmentChange("setting_Question"); }
        });

    }
}
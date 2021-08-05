package com.example.sleepmood;

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

    }
}
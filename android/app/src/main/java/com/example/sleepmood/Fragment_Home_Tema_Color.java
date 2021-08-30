package com.example.sleepmood;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;

import petrov.kristiyan.colorpicker.ColorPicker;


import static android.graphics.Color.colorSpace;
import static android.graphics.Color.parseColor;

public class Fragment_Home_Tema_Color extends Fragment {

    private static final String SAVED_STATE_KEY_COLOR = "saved_state_key_color";
    private static final int INITIAL_COLOR = 0xFFFF8000;
    View pickedColor;

    // 21.08.09
    // https://www.geeksforgeeks.org/how-to-create-a-color-picker-tool-in-android-using-color-wheel-and-slider/
    // picker 이용해서 그리기


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__home__tema__color, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn = view.findViewById(R.id.colorDialog);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPicker colorPicker = new ColorPicker(getActivity());

                ArrayList<String> colors = new ArrayList<>();  // Color 넣어줄 list

                colors.add("#FF0000"); // 빨강
                colors.add("#F48FB1"); // 오랜지
                colors.add("#FFFF00"); // 노란색
                colors.add("#008000"); // 초록색
                colors.add("#0000FF"); // 파란색
                colors.add("#800080"); // 보라색

                colorPicker.setColors(colors);
                colorPicker.setColumns(3);
                colorPicker.setRoundColorButton(true);

                colorPicker.show();
                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position,int color) {
                        // put code
                        // 여기서 색깔 코드 보내면 됨
                        btn.setBackgroundColor(color);
                    }

                    @Override
                    public void onCancel(){
                        // put code
                    }
                });
            }
        });

    }

}

package com.example.sleepmood;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Light extends Fragment {
    private int color = 1;
    ImageView mood_State;

    private SharedPreferences pref;
    private String checkFirst;
    private SensorData sd;

    private TextView temperature;
    private TextView humidity;
    private TextView bright;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_light, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mood_State = (ImageView) view.findViewById(R.id.mood_State);

        pref = getActivity().getSharedPreferences("token", Activity.MODE_PRIVATE);
        checkFirst = pref.getString("token", "NULL");

        temperature = view.findViewById(R.id.light_temperature);
        humidity = view.findViewById(R.id.light_humidity);
        bright = view.findViewById(R.id.light_bright);

        getSensorData();

        mood_State.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color++;
                if (color == 6) {
                    color = 1;
                }

                switch (color) {
                    case 1:
                        mood_State.setImageResource(R.drawable.mood_red);
                        break;
                    case 2:
                        mood_State.setImageResource(R.drawable.mood_yellow);
                        break;
                    case 3:
                        mood_State.setImageResource(R.drawable.mood_green);
                        break;
                    case 4:
                        mood_State.setImageResource(R.drawable.mood_blue);
                        break;
                    case 5:
                        mood_State.setImageResource(R.drawable.mood_purple);
                        break;
                }
            }
        });

        setMoodState();

    }

    void setMoodState() {
        switch (color) {
            case 1:
                mood_State.setImageResource(R.drawable.mood_red);
                break;
            case 2:
                mood_State.setImageResource(R.drawable.mood_yellow);
                break;
            case 3:
                mood_State.setImageResource(R.drawable.mood_green);
                break;
            case 4:
                mood_State.setImageResource(R.drawable.mood_blue);
                break;
            case 5:
                mood_State.setImageResource(R.drawable.mood_purple);
                break;
        }
    }

    public void getSensorData() {
        RetroBuilder retro = new RetroBuilder();
        Call<SensorData> call2 = retro.service.getSensor("born7sh@gmail.com", "Bearer " + checkFirst);
        call2.enqueue(new Callback<SensorData>() {
            @Override
            public void onResponse(Call<SensorData> call, Response<SensorData> response) {
                if (response.isSuccessful()) {
                    Log.v("알림", "드가자");
                    sd = response.body();

                    Log.v("알림", String.valueOf(sd.temperature));
                    Log.v("알림", String.valueOf(sd.humidity));
                    Log.v("알림", String.valueOf(sd.bright));

                    temperature.setText(String.valueOf(sd.temperature) + "℃");
                    humidity.setText(String.valueOf(sd.humidity) + "%");
                    bright.setText(String.valueOf(sd.bright));

                }

            }

            @Override
            public void onFailure(Call<SensorData> call, Throwable t) {
                Log.e("알림", "실패" + t.getMessage());

            }
        });
    }

}
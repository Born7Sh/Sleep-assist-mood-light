package com.example.sleepmood;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;


public class Fragment_Home_Weather extends Fragment {

    ImageView home_9_Img;
    TextView home_9_Temp;
    TextView home_9_Hum;

    ImageView home_12_Img;
    TextView home_12_Temp;
    TextView home_12_Hum;

    ImageView home_15_Img;
    TextView home_15_Temp;
    TextView home_15_Hum;

    ImageView home_18_Img;
    TextView home_18_Temp;
    TextView home_18_Hum;

    ImageView home_21_Img;
    TextView home_21_Temp;
    TextView home_21_Hum;

    ImageView location_9_Img;
    TextView location_9_Temp;
    TextView location_9_Hum;

    ImageView location_12_Img;
    TextView location_12_Temp;
    TextView location_12_Hum;

    ImageView location_15_Img;
    TextView location_15_Temp;
    TextView location_15_Hum;

    ImageView location_18_Img;
    TextView location_18_Temp;
    TextView location_18_Hum;

    ImageView location_21_Img;
    TextView location_21_Temp;
    TextView location_21_Hum;

    ImageView home_dust;
    ImageView home_Very_dust;
    TextView home_dust_String;
    TextView home_dust_Number;
    TextView home_Very_dust_String;
    TextView home_Very_dust_Number;


    ImageView location_dust;
    ImageView location_Very_dust;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment__home__weather, container, false);


        return inflater.inflate(R.layout.fragment__home__weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        home_9_Img = (ImageView) view.findViewById(R.id.home_9_Img);
        home_9_Temp = (TextView) view.findViewById(R.id.home_9_Temp);
        home_9_Hum = (TextView) view.findViewById(R.id.home_9_Hum);

        home_12_Img = (ImageView) view.findViewById(R.id.home_12_Img);
        home_12_Temp = (TextView) view.findViewById(R.id.home_12_Temp);
        home_12_Hum = (TextView) view.findViewById(R.id.home_12_Hum);

        home_15_Img = (ImageView) view.findViewById(R.id.home_15_Img);
        home_15_Temp = (TextView) view.findViewById(R.id.home_15_Temp);
        home_15_Hum = (TextView) view.findViewById(R.id.home_15_Hum);

        home_18_Img = (ImageView) view.findViewById(R.id.home_18_Img);
        home_18_Temp = (TextView) view.findViewById(R.id.home_18_Temp);
        home_18_Hum = (TextView) view.findViewById(R.id.home_18_Hum);

        home_21_Img = (ImageView) view.findViewById(R.id.home_21_Img);
        home_21_Temp = (TextView) view.findViewById(R.id.home_21_Temp);
        home_21_Hum = (TextView) view.findViewById(R.id.home_21_Hum);


        location_9_Img = (ImageView) view.findViewById(R.id.location_9_Img);
        location_9_Temp = (TextView) view.findViewById(R.id.location_9_Temp);
        location_9_Hum = (TextView) view.findViewById(R.id.location_9_Hum);

        location_12_Img = (ImageView) view.findViewById(R.id.location_12_Img);
        location_12_Temp = (TextView) view.findViewById(R.id.location_12_Temp);
        location_12_Hum = (TextView) view.findViewById(R.id.location_12_Hum);

        location_15_Img = (ImageView) view.findViewById(R.id.location_15_Img);
        location_15_Temp = (TextView) view.findViewById(R.id.location_15_Temp);
        location_15_Hum = (TextView) view.findViewById(R.id.location_15_Hum);

        location_18_Img = (ImageView) view.findViewById(R.id.location_18_Img);
        location_18_Temp = (TextView) view.findViewById(R.id.location_18_Temp);
        location_18_Hum = (TextView) view.findViewById(R.id.location_18_Hum);

        location_21_Img = (ImageView) view.findViewById(R.id.location_21_Img);
        location_21_Temp = (TextView) view.findViewById(R.id.location_21_Temp);
        location_21_Hum = (TextView) view.findViewById(R.id.location_21_Hum);

        home_dust = (ImageView) view.findViewById(R.id.home_dust);
        home_dust_String = (TextView) view.findViewById(R.id.home_dust_String);
        home_dust_Number = (TextView) view.findViewById(R.id.home_dust_Number);

        home_Very_dust = (ImageView) view.findViewById(R.id.home_Very_dust);
        home_Very_dust_String = (TextView) view.findViewById(R.id.home_Very_dust_String);
        home_Very_dust_Number = (TextView) view.findViewById(R.id.home_Very_dust_Number);

        setWeatherImg(home_9_Img, 2);
        setWeatherImg(home_12_Img, 2);
        setWeatherImg(home_15_Img, 3);
        setWeatherImg(home_18_Img, 3);
        setWeatherImg(home_21_Img, 2);

        setDustImg(home_dust, 2);
        setDustImg(home_Very_dust, 4);

    }

    public void setWeatherImg(ImageView imageView, int weather) {
        // 값들이 들오오면 (weather) 이라고 가정 / 그 weather이 정수라고 가정 img를 바꿔주는 함수
        // 값들마다 달라져야함

        switch (weather) {
            case 1:
                imageView.setImageResource(R.drawable.weather_sunny);
                break;
            case 2:
                imageView.setImageResource(R.drawable.weather_cloudy);
                break;
            case 3:
                imageView.setImageResource(R.drawable.weather_humity);
                break;
            case 4:
                imageView.setImageResource(R.drawable.weather_rain);
                break;
            case 5:
                imageView.setImageResource(R.drawable.weather_snowing);
                break;
            case 6:
                imageView.setImageResource(R.drawable.weather_strongwind);
                break;
            case 7:
                imageView.setImageResource(R.drawable.weather_sunandcloud);
                break;
            case 8:
                imageView.setImageResource(R.drawable.weather_thunder);
                break;
            default:
                imageView.setImageResource(R.drawable.weather_sunny);
                break;
        }


    }

    void setDustImg(ImageView imageView, int score) {
        switch (score) {
            case 1:
                imageView.setImageResource(R.drawable.bar_0);
                break;
            case 2:
                imageView.setImageResource(R.drawable.bar_25);
                break;
            case 3:
                imageView.setImageResource(R.drawable.bar_50);
                break;
            case 4:
                imageView.setImageResource(R.drawable.bar_75);
                break;
            case 5:
                imageView.setImageResource(R.drawable.bar_100);
                break;
        }
    }

    public void setWeatherTemp(TextView textView, String text) {

        textView.setText(text);

    }

    public void setWeatherHum(TextView textView, String text) {
        textView.setText(text);
    }

    public void setWeatherDustString(TextView textView, String text) {
        textView.setText(text);
    }

    public void setWeatherDustNumber(TextView textView, String text) {
        textView.setText(text);
    }


}
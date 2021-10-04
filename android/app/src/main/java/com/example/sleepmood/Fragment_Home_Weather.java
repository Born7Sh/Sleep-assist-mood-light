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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

    ImageView home_now_Image;
    TextView home_now_Temp;
    TextView home_now_Hum;
    ImageView home_dust;
    ImageView home_Very_dust;
    TextView home_dust_String;
    TextView home_dust_Number;
    TextView home_Very_dust_String;
    TextView home_Very_dust_Number;

    ImageView location_dust;
    ImageView location_Very_dust;

    private SharedPreferences pref;
    private String checkFirst;
    private WeatherData cwd;
    private List<WeatherData> lwd;

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

        pref = getActivity().getSharedPreferences("token", Activity.MODE_PRIVATE);
        checkFirst = pref.getString("token", "NULL");

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

        home_now_Image = (ImageView) view.findViewById(R.id.home_now_Img);
        home_now_Temp = (TextView) view.findViewById(R.id.home_now_Temp);
        home_now_Hum = (TextView) view.findViewById(R.id.home_now_Hum);

        setWeatherImg(home_9_Img, 2);
        setWeatherImg(home_12_Img, 2);
        setWeatherImg(home_15_Img, 3);
        setWeatherImg(home_18_Img, 3);
        setWeatherImg(home_21_Img, 2);

        setDustImg(home_dust, 2);
        setDustImg(home_Very_dust, 4);

        callWeatherData();
        callForecastData();


    }

    public void setCurrentWeather() {
        home_now_Temp.setText("" + cwd.temperature.toString() + "℃");
        home_now_Hum.setText(String.valueOf(cwd.humidity) + "%");
        home_dust_String.setText("" + cwd.fine_dust2_5 + "μg/㎥");
        home_Very_dust_String.setText("" + cwd.fine_dust10 + "μg/㎥");

        setDustImg(home_dust, cwd.fine_dust2_5);
        setDustImg(home_Very_dust, cwd.fine_dust10);
        setWeatherImg(home_now_Image, cwd.precipitation_type);

    }

    public void setForecast() throws ParseException {
        setForecastData();


    }

    public void setForecastData() throws ParseException {

        if (lwd != null) {
            for (int i = 0; i < lwd.size(); i++) {
                if (lwd.get(i).datetime == null) {
                    continue;
                }
                String time = lwd.get(i).datetime.substring(11, 19);

                if (time.equals("12:00:00")) {
                    home_12_Temp.setText(lwd.get(i).temperature.toString() + "℃");
                    home_12_Hum.setText(Integer.toString(lwd.get(i).humidity) + "%");
                    setWeatherImg(home_12_Img, lwd.get(i).precipitation_type);
                    Log.v("알림", lwd.get(i).datetime);
                } else if (time.equals("15:00:00")) {
                    home_15_Temp.setText(lwd.get(i).temperature.toString() + "℃");
                    home_15_Hum.setText(Integer.toString(lwd.get(i).humidity) + "%");
                    setWeatherImg(home_15_Img, lwd.get(i).precipitation_type);
                    Log.v("알림", lwd.get(i).datetime);
                } else if (time.equals("18:00:00")) {
                    home_18_Temp.setText(lwd.get(i).temperature.toString() + "℃");
                    home_18_Hum.setText(Integer.toString(lwd.get(i).humidity) + "%");
                    setWeatherImg(home_18_Img, lwd.get(i).precipitation_type);
                    Log.v("알림", lwd.get(i).datetime);
                } else if (time.equals("21:00:00")) {
                    home_21_Temp.setText(lwd.get(i).temperature.toString() + "℃");
                    home_21_Hum.setText(Integer.toString(lwd.get(i).humidity) + "%");
                    setWeatherImg(home_21_Img, lwd.get(i).precipitation_type);
                    Log.v("알림", lwd.get(i).datetime);
                } else if (time.equals("07:00:00")) {
                    home_9_Temp.setText(lwd.get(i).temperature.toString() + "℃");
                    home_9_Hum.setText(Integer.toString(lwd.get(i).humidity) + "%");
                    setWeatherImg(home_9_Img, lwd.get(i).precipitation_type);
                    Log.v("알림", lwd.get(i).datetime);
                } else {
                    continue;
                }
            }
        }
    }

    public void setWeatherImg(ImageView imageView, int weather) {
        // 값들이 들오오면 (weather) 이라고 가정 / 그 weather이 정수라고 가정 img를 바꿔주는 함수
        // 값들마다 달라져야함

        switch (weather) {
            case 0:
                imageView.setImageResource(R.drawable.weather_sunny);
                break;
            case 1:
                imageView.setImageResource(R.drawable.weather_rain);
                break;
            case 2:
                imageView.setImageResource(R.drawable.weather_cloudy);
                break;
            case 3:
                imageView.setImageResource(R.drawable.weather_humity);
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

        if (score < 15) {
            home_dust_String.setText("매우 좋음");
            home_Very_dust_String.setText("매우 좋음");
            imageView.setImageResource(R.drawable.bar_0);
        } else if (score < 35) {
            home_dust_String.setText("좋음");
            home_Very_dust_String.setText("좋음");
            imageView.setImageResource(R.drawable.bar_25);
        } else if (score < 65) {
            home_dust_String.setText("보통");
            home_Very_dust_String.setText("보통");
            imageView.setImageResource(R.drawable.bar_50);
        } else if (score < 85) {
            home_dust_String.setText("나쁨");
            home_Very_dust_String.setText("나쁨");
            imageView.setImageResource(R.drawable.bar_75);
        } else if (score <= 150) {
            home_dust_String.setText("매우 나쁨");
            home_Very_dust_String.setText("매우 나쁨");
            imageView.setImageResource(R.drawable.bar_100);
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


    public void callWeatherData() {

        RetroBuilder retro = new RetroBuilder();
        Call<WeatherData> call = retro.service.getWeatherNow("Bearer " + checkFirst);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (response.isSuccessful()) {
                    cwd = response.body();
                    Log.v("알림", "시간 : " + cwd.datetime);
                    Log.v("알림", "온도 : " + cwd.temperature);
                    Log.v("알림", "미세먼지 : " + cwd.humidity);
                    Log.v("알림", "type : " + cwd.precipitation_type);
                    Log.v("알림", "미세먼지 2 : " + cwd.fine_dust2_5);
                    Log.v("알림", "미세먼지 10 : " + cwd.fine_dust10);

                } else {
                    Log.v("알림", "오류! 데이터를 못받아옴");
                    cwd = new WeatherData("0", (float) 0.0, 0, 0, 0, 0);
                }
                setCurrentWeather();
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.v("알림", "안됨1");
            }
        });


    }

    public void callForecastData() {

        RetroBuilder retro2 = new RetroBuilder();
        Call<List<WeatherData>> call2 = retro2.service.getWeatherForecast("Bearer " + checkFirst);
        call2.enqueue(new Callback<List<WeatherData>>() {
            @Override
            public void onResponse(Call<List<WeatherData>> call, Response<List<WeatherData>> response) {
                if (response.isSuccessful()) {
                    lwd = response.body();

                    Log.v("알림", "시간 : " + lwd.get(0).datetime);
                    Log.v("알림", "온도 : " + lwd.get(0).temperature);
                    Log.v("알림", "습도 : " + lwd.get(0).humidity);
                    Log.v("알림", "type : " + lwd.get(0).precipitation_type);
                    Log.v("알림", "lwd 크기 : " + lwd.size());

                } else {
                    Log.v("알림", "오류! 데이터를 못받아옴");
                    cwd = new WeatherData("0", (float) 0.0, 0, 0, 0, 0);
                }
                try {
                    setForecast();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<WeatherData>> call, Throwable t) {
                Log.v("알림", "안됨1");
            }
        });


    }

}
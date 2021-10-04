package com.example.sleepmood;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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

public class Fragment_Home_SleepReady extends Fragment {

    MainActivity activity;

    private SharedPreferences pref;
    private String checkFirst;
    private SharedPreferences pref_id;
    private String user_id;
    private SharedPreferences pref_element;


    private SensorData sd;
    private List<WeatherData> lwd;

    TextView current_temperature;
    TextView current_humidity;

    TextView recommend_temperature;
    TextView recommend_humidity;
    TextView recommend_Text;

    ImageView weather;
    TextView tomorrow_temperature;
    TextView tomorrow_humidity;
    TextView tomorrow_weather;

    Button sleep_start;
    CheckBox checkBox_cold;
    CheckBox checkBox_smoke;
    CheckBox checkBox_not_home;

    int checkBox_num;

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


        return inflater.inflate(R.layout.fragment__home__sleep_ready, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pref = getActivity().getSharedPreferences("token", Activity.MODE_PRIVATE);
        checkFirst = pref.getString("token", "NULL");

        pref_id = getActivity().getSharedPreferences("id", Activity.MODE_PRIVATE);
        user_id = pref_id.getString("id", "NULL");

        pref_element = getActivity().getSharedPreferences("element", Activity.MODE_PRIVATE);

        current_temperature = view.findViewById(R.id.currentTemp);
        current_humidity = view.findViewById(R.id.currentHum);
        recommend_temperature = view.findViewById(R.id.recommendTemp);
        recommend_humidity = view.findViewById(R.id.recommendHum);

        recommend_Text = view.findViewById(R.id.recommendText);
        weather = view.findViewById(R.id.weather);

        tomorrow_humidity = view.findViewById(R.id.tomorrow_hum);
        tomorrow_temperature = view.findViewById(R.id.tomorrow_temp);
        tomorrow_weather = view.findViewById(R.id.tomorrow_weather);

        checkBox_cold = view.findViewById(R.id.check_element_cold);
        checkBox_smoke = view.findViewById(R.id.check_element_smoke);
        checkBox_not_home= view.findViewById(R.id.check_element_isNotHome);

        sleep_start = view.findViewById(R.id.sleepStart);

        sleep_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox_cold.isChecked()){
                    checkBox_num = 100 + checkBox_num;
                }
                if(checkBox_smoke.isChecked()){
                    checkBox_num = 20 + checkBox_num;
                }
                if(checkBox_not_home.isChecked()){
                    checkBox_num = 3 +checkBox_num;
                }
                Log.v("알림" ,"checkbox 값 : " + checkBox_num);

                SharedPreferences.Editor editor = pref_element.edit();
                editor.putInt("element", checkBox_num);
                editor.commit();

                activity.onFragmentChange("sleepStart");

            }
        });

        getSensorData();

    }



    void functionSensor() {

    }

    void functionWeather() {

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

                    current_temperature.setText(String.valueOf(sd.temperature) + "℃");
                    current_humidity.setText(String.valueOf(sd.humidity) + "%");
                    setRecommendData();
                }

            }

            @Override
            public void onFailure(Call<SensorData> call, Throwable t) {
                Log.e("알림", "실패" + t.getMessage());

            }
        });
    }

    void setRecommendData() {

        // 여름철 적정 온도는 25 - 28 도
        // 여름철 적정 습도는 40 - 60
        // 겨울철 적정 온도는 18 - 20 도
        // 겨울철 적정 온도는 40 - 70

        Date curDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        int a = Integer.parseInt(dateFormat.format(curDate).substring(5, 7));

        if (5 <= a && a <= 9) {
            Log.v("알림", "여름 이였다.");
            recommend_temperature.setText("19℃");
            recommend_humidity.setText("60%");

            if (sd.temperature >= 18 && sd.temperature <= 20) {
                if (sd.humidity >= 40 && sd.humidity <= 70) {
                    recommend_Text.setText("현재 방은 적정 온도,습도입니다.");
                } else {
                    recommend_Text.setText("현재 방은 적정 온도입니다만, \n 습도는 조정 바랍니다.");
                }
            } else {
                if (sd.humidity >= 40 && sd.humidity <= 70) {
                    recommend_Text.setText("현재 방은 적정 습도입니다만, \n 온도는 조정 바랍니다.");
                } else {
                    recommend_Text.setText("현재 방의 온도와 습도 모두 조정 바랍니다.");
                }
            }

        } else {
            Log.v("알림", "겨울 이였다.");
            recommend_temperature.setText("25℃");
            recommend_humidity.setText("50%");

            if (sd.temperature >= 25 && sd.temperature <= 28) {
                if (sd.humidity >= 40 && sd.humidity <= 60) {
                    recommend_Text.setText("현재 방은 적정 온도,습도입니다.");
                } else {
                    recommend_Text.setText("현재 방은 적정 온도입니다만, 습도는 조정 바랍니다.");
                }
            } else {
                if (sd.humidity >= 40 && sd.humidity <= 60) {
                    recommend_Text.setText("현재 방은 적정 습도입니다만, 온도는 조정 바랍니다.");
                } else {
                    recommend_Text.setText("현재 방의 온도와 습도 모두 조정 바랍니다.");
                }
            }
        }
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

                }else {
                    Log.v("알림", "오류! 데이터를 못받아옴");
                    }
                setForecastData();
            }

            @Override
            public void onFailure(Call<List<WeatherData>> call, Throwable t) {
                Log.v("알림", "안됨1");
            }
        });



    }

    public void setForecastData(){
        for (int i = 0 ; i<lwd.size(); i ++ ){
            String time = lwd.get(i).datetime.substring(11,19);

           if(time.equals("07:00:00")){
                tomorrow_temperature.setText(lwd.get(i).temperature.toString()+ "℃");
                tomorrow_humidity.setText(Integer.toString(lwd.get(i).humidity)+ "%");
                setWeatherImg(weather,lwd.get(i).precipitation_type);
                if(lwd.get(i).precipitation_type == 1){
                    tomorrow_weather.setText("내일 비오니깐 우산 챙겨오세요!");
                }
                Log.v("알림", lwd.get(i).datetime);
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

}
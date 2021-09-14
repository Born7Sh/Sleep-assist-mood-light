package com.example.sleepmood;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class Fragment_Home extends Fragment {
    MainActivity activity;

    // 사용자의 모든 정보 21.08.01
    // 앱 켜지자 마자 데이터 DB에서 다 받아오면 좋을거 같은데
    // 여기서 다 받아와지게 하자
    ArrayList<AlarmData> alarmData = new ArrayList<AlarmData>();
    SharedPreferences sharedPreferences;
    private Gson gson = new GsonBuilder().create();
    private String json;
    List<DiaryData> result;

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

        ArrayList<Integer> alarmWeek = new ArrayList<Integer>(0);
        SharedPreferences pref = getActivity().getSharedPreferences("token", Activity.MODE_PRIVATE);
        String checkFirst = pref.getString("token","NULL");
//        AlarmData a = new AlarmData(1,"ho", "ho", alarmWeek);
//        AlarmData b = new AlarmData(2,"no", "no", alarmWeek);
//        sharedViewModel.addLiveAlarmData(a);
//        sharedViewModel.addLiveAlarmData(b);

//        AlarmData a = new AlarmData(3, "ho", "ho", alarmWeek);
//        alarmData.add(a);
//
//
//        // 데이터 넣기
//        sharedPreferences = getContext().getSharedPreferences( "AlarmData", MODE_PRIVATE );
//        Gson gson = new Gson();
//        json = gson.toJson(a, AlarmData.class);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(String.valueOf(0), json);
//        editor.apply();

        ImageView weatherInfo = (ImageView) view.findViewById(R.id.weatherInfo);
        ImageView tema = (ImageView) view.findViewById(R.id.tema);
        ImageView alramSetting = (ImageView) view.findViewById(R.id.alramSetting);

        Button sleepReady = (Button) view.findViewById(R.id.sleepReady);
        Button sleepStart = (Button) view.findViewById(R.id.sleepStart);



//        RetroBuilder retro = new RetroBuilder();
//        Call<ReportData> call = retro.service.getReportToday("born7sh@gmail.com");
//        call.enqueue(new Callback<ReportData>() {
//            @Override
//            public void onResponse(Call<ReportData> call, Response<ReportData> response) {
//                if(response.isSuccessful()){
//                    ReportData rd1 = response.body();
//                    Log.v("알림",Integer.toString(rd1.getScore()));
//                    Log.v("알림", "일단 들어는옴");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ReportData> call, Throwable t) {
//                Log.v("알림", "안됨1");
//            }
//        });
//
//        Call<List<ReportData>> call2 = retro.service.getReportAll("born7sh@gmail.com");
//        call2.enqueue(new Callback<List<ReportData>>() {
//            @Override
//            public void onResponse(Call<List<ReportData>> call, Response<List<ReportData>> response) {
//                if(response.isSuccessful()){
//                    List<ReportData> reportL = response.body();
//                    Log.v("알림", Integer.toString(reportL.get(0).getScore()));
//                    Log.v("알림", "일단 들어는옴2");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<ReportData>> call, Throwable t) {
//                Log.v("알림", "안됨2");
//            }
//
//        });

        /*
        List<String> l1 = new ArrayList<>();
        RetroBuilderSleep retrosleep = new RetroBuilderSleep();
        l1.add("흡연");
        l1.add("마구 움직임");
        SleepData s2 = new SleepData(7,5,3,"test","born7sh@gmail.com","2021-08-25 15:12:10");
        Call<SleepData> call = retrosleep.service.provideSleepData(s2);
        call.enqueue(new Callback<SleepData>() {
            @Override
            public void onResponse(Call<SleepData> call, Response<SleepData> response) {
                Log.v("테스트","확인");
                if(response.isSuccessful()){
                    Log.v("성공여부", "성공");
                }
            }

            @Override
            public void onFailure(Call<SleepData> call, Throwable t) {

            }
        });



/////////////////////////////////////////////////////////여기 부터 주석 삭제하면 됨
//        */
//        RetroBuilder retro = new RetroBuilder();
//        DiaryData d2 = new DiaryData("born7sh@gmail.com","2021-09-14","test");
//      //  Call<DiaryData2> call = retro.service.provideDiaryDay("json","born7sh@gmail.com","2021-08-26","posttestright?");
////        Call<DiaryData> call = retro.service.provideDiaryDay(d2,"Bearer " +checkFirst);
//        Call<DiaryData> call = retro.service.provideDiaryDay(d2,"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpbmhvMjE2IiwiZXhwIjoxNjMxNTU3NDc2LCJpYXQiOjE2MzE1MjE0NzZ9.pEhPbnoBaouNWd5tYUqTJAKeczUgorfbazLbJS_70xA");
//
//        call.enqueue(new Callback<DiaryData>() {
//                         @Override
//                         public void onResponse(Call<DiaryData> call, Response<DiaryData> response) {
//                                 Log.v("알림","확인");
//                             if(response.isSuccessful()){
//                                 Log.v("알림","성공1");
//                             }
//                         }
//
//                         @Override
//                         public void onFailure(Call<DiaryData> call, Throwable t) {
//                             Log.v("알림","실패1");
//                         }
//                     }
//        );



//        Call<List<DiaryData>> call2 = retro.service.getDiaryToday("born7sh@gmail.com","all");
//        call2.enqueue(new Callback<List<DiaryData>>() {
//            @Override
//            public void onResponse(Call<List<DiaryData>> call, Response<List<DiaryData>> response) {
//                Log.e(TAG,"onResponse 그리고");
//                if(response.isSuccessful()){
//                    Log.e(TAG,"성공");
//                    result = response.body();
//                    Log.e(TAG,result.get(0).getDescription());
//                }
//                else{
//                    Log.e(TAG,"실패");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DiaryData>> call, Throwable t) {
//                Log.e(TAG,"실패" + t.getMessage());
//            }
//        });



        alramSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  String a = result.get(0).getDescription();
//                ArrayList<Integer> alarmWeek = new ArrayList<Integer>(0);

//                AlarmData b = new AlarmData("no","no", alarmWeek);
//                sharedViewModel.addLiveAlarmData(b);
                activity.onFragmentChange("alarmList");
//                Bundle bundle = new Bundle();
//                bundle.putString("text",text); //fragment1로 번들 전달
//                fragment1.setArguments(bundle);


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
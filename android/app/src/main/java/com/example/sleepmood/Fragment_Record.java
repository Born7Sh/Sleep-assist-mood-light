package com.example.sleepmood;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_Record extends Fragment {

    MainActivity activity;

    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;

    private Fragment_Record_Entire fragment_record_entire;
    private Fragment_Record_Day fragment_record_day;
    private Fragment_Record_Week fragment_record_week;
    private Fragment_Record_Month fragment_record_month;
    private Fragment_Record_Calendar fragment_record_calendar;
    private Fragment_Record_Diary fragment_record_diary;

    ViewGroup view;

    private ReportData dayData;
    private List<ReportData> rd;

    private SharedPreferences pref;
    private String checkFirst;

    private SharedPreferences pref_id;
    private String user_id;

    private SharedPreferences pref_reportAll;
    private String json_All;

    private SharedPreferences pref_reportDay;
    private String json_Day;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = (ViewGroup) inflater.inflate(R.layout.fragment__record, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pref = getActivity().getSharedPreferences("token", Activity.MODE_PRIVATE);
        checkFirst = pref.getString("token", "NULL");

        pref_id = getActivity().getSharedPreferences("id", Activity.MODE_PRIVATE);
        user_id = pref_id.getString("id", "NULL");

//        getReportAll();
//        getReportDataDay();
        createFragment();
        createViewpager();
        settingTabLayout();

    }

    //fragment 생성
    public void createFragment() {
        fragment_record_entire = new Fragment_Record_Entire();
        fragment_record_day = new Fragment_Record_Day();
//        fragment_record_week = new Fragment_Record_Week();
//        fragment_record_month = new Fragment_Record_Month();
        fragment_record_calendar = new Fragment_Record_Calendar();
        fragment_record_diary = new Fragment_Record_Diary();

    }

    //viewpager 및 어댑터 생성
    public void createViewpager() {
        viewPager = (ViewPager2) view.findViewById(R.id.viewpager_control);

        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), getLifecycle());

        viewPagerAdapter.addFragment(fragment_record_entire);
        viewPagerAdapter.addFragment(fragment_record_day);
//        viewPagerAdapter.addFragment(fragment_record_week);
//        viewPagerAdapter.addFragment(fragment_record_month);
        viewPagerAdapter.addFragment(fragment_record_calendar);
        viewPagerAdapter.addFragment(fragment_record_diary);

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setUserInputEnabled(false);//터치 스크롤 막음
        getReportAll();

    }

    //tablayout - viewpager 연결
    public void settingTabLayout() {
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_control);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();

                switch (pos) {
                    case 0:
                        viewPager.setCurrentItem(0);

                        break;
                    case 1:
                        viewPager.setCurrentItem(1);

                        break;
                    case 2:
                        viewPager.setCurrentItem(2);

                        break;
                    case 3:
                        viewPager.setCurrentItem(3);
                        break;
//                    case 4:
//                        viewPager.setCurrentItem(4);
//                        break;
//                    case 5:
//                        viewPager.setCurrentItem(5);
//                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

            public void refresh() {
                viewPagerAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getReportAll() {
        RetroBuilder retro = new RetroBuilder();
        Call<List<ReportData>> call2 = retro.service.getReportAll(user_id, "Bearer " + checkFirst);
        call2.enqueue(new Callback<List<ReportData>>() {
            @Override
            public void onResponse(Call<List<ReportData>> call, Response<List<ReportData>> response) {
                Log.v("알림", "일단 응답옴");
                if (response.isSuccessful()) {


                    Log.v("알림", "성공");
                    rd = response.body();
                    Log.v("알림", "크기: " + rd.size());
                    Log.v("알림", "스코어: " + rd.get(0).getScore());
                    Log.v("알림", "수면 시간: " + rd.get(0).getSleeping_time());
                    Log.v("알림", "element: " + rd.get(0).getElements());

                    pref_reportAll = getContext().getSharedPreferences("reportAll", MODE_PRIVATE);
                    Gson gson = new Gson();

                    for (int i = 0; i < rd.size(); i++) {

                        json_All = gson.toJson(rd.get(i), ReportData.class);
                        SharedPreferences.Editor editor = pref_reportAll.edit();
                        editor.putString(String.valueOf(i), json_All);
                        editor.apply();

                    }
                    getReportDataDay();

                } else {
                    Log.v("알림", "onsponse에서의 실패");
                }
            }

            @Override
            public void onFailure(Call<List<ReportData>> call, Throwable t) {
                Log.e("알림", "진짜 실패");

            }
        });
    }


    public void getReportDataDay() {
        RetroBuilder retro = new RetroBuilder();
        Call<ReportData> call2 = retro.service.getReportToday(user_id, "Bearer " + checkFirst);
        call2.enqueue(new Callback<ReportData>() {
            @Override
            public void onResponse(Call<ReportData> call, Response<ReportData> response) {

                if (!response.isSuccessful()) {
                    Log.v("알림", "response : " + response.code());
                    return;
                }


                if (response.isSuccessful()) {
                    Log.v("알림", "드가자");
                    dayData = response.body();
                    Log.v("알림", "dayTime : " + dayData.getSleeping_time());
                    Log.v("알림", "스코어 : " + dayData.getScore());
                    Log.v("알림", "ID  : " + dayData.getSleepid());

                    pref_reportDay = getContext().getSharedPreferences("reportDay", MODE_PRIVATE);
                    Gson gson = new Gson();
                    json_Day = gson.toJson(dayData, ReportData.class);
                    SharedPreferences.Editor editor = pref_reportDay.edit();
                    editor.putString(String.valueOf(0), json_Day);
                    editor.apply();

                }

            }

            @Override
            public void onFailure(Call<ReportData> call, Throwable t) {
                Log.e("알림", "실패" + t.getMessage());

            }
        });
    }

}
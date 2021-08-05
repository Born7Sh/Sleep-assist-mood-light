package com.example.sleepmood;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Fragment_Record extends Fragment {

    MainActivity activity;

    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private Fragment_Record_Day fragment_record_day;
    private Fragment_Record_Week fragment_record_week;
    private Fragment_Record_Month fragment_record_month;
    private Fragment_Record_Calendar fragment_record_calendar;
    private Fragment_Record_Diary fragment_record_diary;

    ViewGroup view;


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
        createFragment();
        createViewpager();
        settingTabLayout();
        return view;
    }

    //fragment 생성
    public void createFragment()
    {
        fragment_record_day = new Fragment_Record_Day();
        fragment_record_week = new Fragment_Record_Week();
        fragment_record_month = new Fragment_Record_Month();
        fragment_record_calendar = new Fragment_Record_Calendar();
        fragment_record_diary = new Fragment_Record_Diary();

    }

    //viewpager 및 어댑터 생성
    public void createViewpager()
    {
        viewPager = (ViewPager2) view.findViewById(R.id.viewpager_control);

        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), getLifecycle());
        viewPagerAdapter.addFragment(fragment_record_day);
        viewPagerAdapter.addFragment(fragment_record_week);
        viewPagerAdapter.addFragment(fragment_record_month);
        viewPagerAdapter.addFragment(fragment_record_calendar);
        viewPagerAdapter.addFragment(fragment_record_diary);

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setUserInputEnabled(false);//터치 스크롤 막음
    }

    //tablayout - viewpager 연결
    public void settingTabLayout()
    {
        tabLayout = (TabLayout)view.findViewById(R.id.tablayout_control);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();

                switch (pos)
                {
                    case 0 :
                        viewPager.setCurrentItem(0);
                        break;
                    case 1 :
                        viewPager.setCurrentItem(1);
                        break;
                    case 2 :
                        viewPager.setCurrentItem(2);
                        break;
                    case 3 :
                        viewPager.setCurrentItem(3);
                        break;
                    case 4 :
                        viewPager.setCurrentItem(4);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
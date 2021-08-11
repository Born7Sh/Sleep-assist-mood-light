package com.example.sleepmood;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

public class Fragment_Home_Tema extends Fragment {

    MainActivity activity;

    private ViewPager2 viewPager;
    private ViewPagerAdapter tema_viewPagerAdapter;
    private TabLayout tabLayout;

    private Fragment_Home_Tema_Color fragment_home_tema_color;
    private Fragment_Home_Tema_Song fragment_home_tema_song;

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
        view = (ViewGroup) inflater.inflate(R.layout.fragment__home__tema, container, false);

        createFragment();
        createViewpager();
        settingTabLayout();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void createFragment()
    {
        fragment_home_tema_color = new Fragment_Home_Tema_Color();
        fragment_home_tema_song = new Fragment_Home_Tema_Song();
    }

    public void createViewpager()
    {
        viewPager = (ViewPager2) view.findViewById(R.id.viewpager_control_tema);

        tema_viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), getLifecycle());

        tema_viewPagerAdapter.addFragment(fragment_home_tema_color);
        tema_viewPagerAdapter.addFragment(fragment_home_tema_song);
        viewPager.setAdapter(tema_viewPagerAdapter);

        viewPager.setUserInputEnabled(false);//터치 스크롤 막음
    }

    //tablayout - viewpager 연결
    public void settingTabLayout()
    {
        tabLayout = (TabLayout)view.findViewById(R.id.tablayout_control_tema);
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
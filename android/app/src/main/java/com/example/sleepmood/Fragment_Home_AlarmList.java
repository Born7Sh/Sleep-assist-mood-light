package com.example.sleepmood;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collection;


public class Fragment_Home_AlarmList extends Fragment {

    private Context mContext;
    private RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    AlarmAdapter adapter;

    private ArrayList<AlarmData> alarmData = new ArrayList<AlarmData>();
    private ArrayList<Integer> alarmWeek = new ArrayList<Integer>(6);

    private int i = 0;

    MainActivity activity;

    // 데이터 공유 용도
    private SharedViewModel sharedViewModel;


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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment__home__alarm_list, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//
//        alarmData = sharedViewModel.getLiveAlarmData().getValue();

        ArrayList<AlarmData> items = new ArrayList<>();
        SharedPreferences sp = getActivity().
                getSharedPreferences("AlarmData", Context.MODE_PRIVATE);
        Gson gson = new GsonBuilder().create();
        Collection<?> values = sp.getAll().values();

        for (Object value : values) {
            String json = (String) value;
            items.add(gson.fromJson(json, AlarmData.class));
        }

        mContext = view.getContext();
        recyclerView = view.findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(mContext);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

//        alarmWeek.add(0);
//        AlarmData a = new AlarmData("ho", "ho", alarmWeek);
//        AlarmData b = new AlarmData("no", "no", alarmWeek);
//        alarmData.add(a);
//        alarmData.add(b);


        adapter = new AlarmAdapter(mContext, items, activity, Fragment_Home_AlarmList.this);
        recyclerView.setAdapter(adapter);

//        rv.setHasFixedSize(true);
//        rv.setLayoutManager(llm);

        ImageView alarmAdd = view.findViewById(R.id.alarmAdd);
        alarmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChange("alarmAdd");
            }
        });

    }

    public void refreshWishlistAdapter() {

        ArrayList<AlarmData> items = new ArrayList<>();
        items.clear();

        SharedPreferences sp = getActivity().getSharedPreferences("AlarmData", Context.MODE_PRIVATE);
        Gson gson = new GsonBuilder().create();
        Collection<?> values = sp.getAll().values();

        for (Object value : values) {
            String json = (String) value;
            items.add(gson.fromJson(json, AlarmData.class));
        };
        adapter.notifyDataSetChanged();
    }

}
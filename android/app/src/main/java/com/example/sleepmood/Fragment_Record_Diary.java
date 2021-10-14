package com.example.sleepmood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class Fragment_Record_Diary extends Fragment {

    private Context mContext;
    private RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    private ArrayList<DiaryData> diaryData = new ArrayList<>();
    private List<DiaryData> result;
    private SharedPreferences sharedPreferences;

    MainActivity activity;
    // 데이터 공유 용도
    private SharedViewModel sharedViewModel;

    private SharedPreferences pref;
    private String checkFirst;

    private SharedPreferences pref_id;
    private String user_id;

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
        return inflater.inflate(R.layout.fragment__record__diary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        pref = getActivity().getSharedPreferences("token", Activity.MODE_PRIVATE);
        checkFirst = pref.getString("token", "NULL");

        pref_id = getActivity().getSharedPreferences("id", Activity.MODE_PRIVATE);
        user_id = pref_id.getString("id", "NULL");


        // 디이어리 데이터 가져오는 부분.
//        RetroBuilder retro = new RetroBuilder();
//        Call<List<DiaryData>> call2 = retro.service.getDiaryToday("born7sh@gmail.com");
//        call2.enqueue(new Callback<List<DiaryData>>() {
//            @Override
//            public void onResponse(Call<List<DiaryData>> call, Response<List<DiaryData>> response) {
//                if(response.isSuccessful()){
//                    List<DiaryData> reportL = response.body();
//                    Log.v("알림", "일단 들어는옴2");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DiaryData>> call, Throwable t) {
//                Log.v("알림", "안됨2");
//            }
//
//        });
        mContext = view.getContext();
        recyclerView = view.findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(mContext);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

//        LinearLayoutManager manager = new LinearLayoutManager(mContext);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);


        ImageView diaryAdd = (ImageView) view.findViewById(R.id.diaryAdd);
        diaryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_Diary_add.class);
                startActivity(intent);
            }
        });

    }

    private void callDiaryData() {
        RetroBuilder retro = new RetroBuilder();
        Call<List<DiaryData>> call2 = retro.service.getDiaryAll(user_id, "Bearer " + checkFirst);
        call2.enqueue(new Callback<List<DiaryData>>() {
            @Override
            public void onResponse(Call<List<DiaryData>> call, Response<List<DiaryData>> response) {
                Log.v("알림", "일단 응답옴");
                if (response.isSuccessful()) {
                    if (result != null) {
                        result.clear();
                    }
                    Log.v("알림", "성공");
                    result = response.body();
                    diaryData.addAll(result);

                    DiaryAdapter adapter = new DiaryAdapter(mContext, diaryData);
                    recyclerView.setAdapter(adapter);

                } else {
                    Log.v("알림", "onsponse에서의 실패");
                }
            }

            @Override
            public void onFailure(Call<List<DiaryData>> call, Throwable t) {
                Log.e("알림", "진짜 실패");

            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {
                diaryData.clear();
                callDiaryData();
            }
        }, 200);



    }
}
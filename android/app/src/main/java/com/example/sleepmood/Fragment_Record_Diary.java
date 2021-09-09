package com.example.sleepmood;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Fragment_Record_Diary extends Fragment {

    private Context mContext;
    private RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    private ArrayList<DiaryData> diaryData = new ArrayList<DiaryData>();

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__record__diary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


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



        DiaryData a = new DiaryData("1","21/08/24","오늘은 수면을 취했다");
        DiaryData b = new DiaryData("2","21/08/25","오늘은 밥을 먹었다");
        DiaryData c = new DiaryData("3","21/08/26","오늘은 코딩을 했다");
        DiaryData d = new DiaryData("4","21/08/27","내일은 라면을 먹을까?");

        diaryData.add(a);
        diaryData.add(b);
        diaryData.add(c);
        diaryData.add(d);

        sharedViewModel.setAllLiveDiaryData(diaryData);

        diaryData = sharedViewModel.getLiveDiaryData().getValue();

        mContext = view.getContext();
        recyclerView = view.findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(mContext);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

//        LinearLayoutManager manager = new LinearLayoutManager(mContext);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        DiaryAdapter adapter = new DiaryAdapter(mContext, diaryData);
        recyclerView.setAdapter(adapter);

        ImageView diaryAdd = (ImageView)view.findViewById(R.id.diaryAdd);
        diaryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
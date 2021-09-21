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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Record_Diary_Add extends Fragment {

    EditText diary_description;

    Button diary_cancel;
    Button diary_register;

    private SharedPreferences pref;
    private String checkFirst;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__record__diary__add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pref = getActivity().getSharedPreferences("token", Activity.MODE_PRIVATE);
        checkFirst = pref.getString("token", "NULL");

        diary_description = (EditText) view.findViewById(R.id.diary_description);
        diary_cancel = (Button) view.findViewById(R.id.diaryCancel);
        diary_register = (Button) view.findViewById(R.id.diaryRegister);

        diary_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date curDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.format(curDate);

                RetroBuilder retro = new RetroBuilder();
                DiaryData d2 = new DiaryData("born7sh@gmail.com", dateFormat.format(curDate), diary_description.getText().toString());

                Log.v("알림", "날짜 : " + dateFormat.format(curDate));
                Log.v("알림", "데이터 날라가기 : " + diary_description.getText().toString());

                Call<DiaryData> call = retro.service.provideDiaryDay(d2, "Bearer " + checkFirst);

                call.enqueue(new Callback<DiaryData>() {
                                 @Override
                                 public void onResponse(Call<DiaryData> call, Response<DiaryData> response) {
                                     Log.v("알림", "확인");
                                     if (response.isSuccessful()) {
                                         Log.v("알림", "성공1");
                                     }
                                 }

                                 @Override
                                 public void onFailure(Call<DiaryData> call, Throwable t) {
                                     Log.v("알림", "실패1");
                                 }
                             }
                );
            }
        });

    }
}
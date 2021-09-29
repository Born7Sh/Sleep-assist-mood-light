package com.example.sleepmood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Diary_add extends AppCompatActivity {
    EditText diary_description;

    Button diary_cancel;
    Button diary_register;

    private SharedPreferences pref;
    private String checkFirst;

    private SharedPreferences pref_id;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_add);

        pref = getSharedPreferences("token", Activity.MODE_PRIVATE);
        checkFirst = pref.getString("token", "NULL");

        pref_id = getSharedPreferences("id", Activity.MODE_PRIVATE);
        user_id = pref_id.getString("id","NULL");

        diary_description = (EditText) findViewById(R.id.diary_description);
        diary_cancel = (Button) findViewById(R.id.diaryCancel);
        diary_register = (Button) findViewById(R.id.diaryRegister);

        diary_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        diary_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDiaryData();
                finish();
            }
        });

    }

    void postDiaryData(){
        Date curDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.format(curDate);

        RetroBuilder retro = new RetroBuilder();
        DiaryData d2 = new DiaryData(user_id, dateFormat.format(curDate), diary_description.getText().toString());

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
}
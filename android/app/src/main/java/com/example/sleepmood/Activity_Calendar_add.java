package com.example.sleepmood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Calendar_add extends AppCompatActivity {
    private EditText calendar_title;
    private EditText calendar_start;
    private EditText calendar_end;
    private EditText calendar_description;

    private Button calendar_cancel;
    private Button calendar_register;

    private SharedPreferences pref;
    private String checkFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = getSharedPreferences("token", Activity.MODE_PRIVATE);
        checkFirst = pref.getString("token", "NULL");


        setContentView(R.layout.activity_calendar_add);
        calendar_title = findViewById(R.id.calendar_title);
        calendar_start = findViewById(R.id.calendar_start);
        calendar_end = findViewById(R.id.calendar_end);
        calendar_description = findViewById(R.id.calendar_description);

        calendar_cancel = findViewById(R.id.calendarCancel);
        calendar_register = findViewById(R.id.calendarRegister);

        calendar_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        calendar_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postCalendarData();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Record_Calendar()).addToBackStack(null).commit();
                finish();

            }
        });

    }

    void postCalendarData() {
        //CalendarData cd = new CalendarData(calendar_start.getText().toString(), calendar_title.getText().toString(), calendar_description.getText().toString(), calendar_end.getText().toString(), "born7sh@gmail.com");
        CalendarData cd = new CalendarData("2021-09-22 15:30:00", calendar_title.getText().toString(), calendar_description.getText().toString(), "2021-09-22 17:30:00", "born7sh@gmail.com");
        RetroBuilder retro = new RetroBuilder();
        Call<CalendarData> call = retro.service.provideCalendarData(cd, "Bearer " + checkFirst);

        call.enqueue(new Callback<CalendarData>() {
                         @Override
                         public void onResponse(Call<CalendarData> call, Response<CalendarData> response) {
                             Log.v("알림", "확인");
                             if (response.isSuccessful()) {
                                 Log.v("알림", "성공1");
                             }
                         }

                         @Override
                         public void onFailure(Call<CalendarData> call, Throwable t) {
                             Log.v("알림", "실패1");
                         }
                     }
        );
    }
}
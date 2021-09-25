package com.example.sleepmood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Calendar_add extends AppCompatActivity {
    private EditText calendar_title;
    private TimePicker calendar_start;
    private TimePicker calendar_end;
    private EditText calendar_description;

    private Button calendar_cancel;
    private Button calendar_register;

    private SharedPreferences pref;
    private String checkFirst;

    private String calendar_today;
    private String calendar_start_string;
    private String calendar_end_string;

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

        // 오늘 날짜 설정
        Date curDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        calendar_today = dateFormat.format(curDate);


        calendar_cancel = findViewById(R.id.calendarCancel);
        calendar_register = findViewById(R.id.calendarRegister);

        // 초기 날짜 설정
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);

        calendar_start.setCurrentHour(hour);
        calendar_start.setCurrentMinute(min);
        calendar_end.setCurrentHour(hour+2);
        calendar_end.setCurrentMinute(min);


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
                finish();

            }
        });

        calendar_start.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                calendar_start_string = calendar_today +" "+ Integer.toString(hourOfDay) +":"+ Integer.toString(minute) + ":00";
                Log.v("알림", calendar_start_string);
            }
        });

        calendar_end.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                calendar_end_string = calendar_today +" "+ Integer.toString(hourOfDay) +":"+ Integer.toString(minute) + ":00";
                Log.v("알림", calendar_end_string);
            }
        });

    }

    void postCalendarData() {
        //CalendarData cd = new CalendarData(calendar_start.getText().toString(), calendar_title.getText().toString(), calendar_description.getText().toString(), calendar_end.getText().toString(), "born7sh@gmail.com");
        CalendarData cd = new CalendarData(calendar_start_string, calendar_title.getText().toString(), calendar_description.getText().toString(), calendar_end_string, "born7sh@gmail.com");
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
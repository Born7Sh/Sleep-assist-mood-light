package com.example.sleepmood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Record_Calendar extends Fragment {
    MaterialCalendarView materialCalendarView;
    List<CalendarData> ld;
    private ArrayList<CalendarData> cd = new ArrayList<>();
    List<CalendarDay> dates = new ArrayList<>();

    Button calendar_add;

    TextView calendar_list;

    private SharedPreferences pref;
    private String checkFirst;

    // 리사이클러뷰 용도
    private Context mContext;
    private RecyclerView recyclerView;
    LinearLayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getCalendarData();
        return inflater.inflate(R.layout.fragment__record__calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pref = getActivity().getSharedPreferences("token", Activity.MODE_PRIVATE);
        checkFirst = pref.getString("token", "NULL");

        materialCalendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        calendar_add = (Button) view.findViewById(R.id.calendar_add);

        calendar_list = (TextView) view.findViewById(R.id.calendar_list);

        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2021, 8, 1)) // 달력의 시작
                .setMaximumDate(CalendarDay.from(2022, 11, 31)) // 달력의 끝
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        getCalendarData();




        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();

                String shot_Day = Year + "-" + Month + "-" + Day;
                if (Month < 10) {
                    shot_Day = Year + "-0" + Month + "-" + Day;
                }
                for (int i = 0; i < cd.size(); i++) {
                    String a = cd.get(i).start.substring(0, 10);
                    if (a.equals(shot_Day)) {
                        Log.i("알림", "이떄 할일은? " + cd.get(i).description);
                    }
                }


                Toast.makeText(getContext(), shot_Day, Toast.LENGTH_SHORT).show();
            }
        });

        calendar_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_Calendar_add.class);
                startActivity(intent);
            }
        });
    }

    void getCalendarData() {

//        CalendarData c = new CalendarData("2021-09-26 15:30:30", "일정1", "일해야지", "2021-09-21", "ddd@gmail.com");
//        CalendarData c2 = new CalendarData("2021-09-23 16:30:30", "일정2", "잠자야지", "2021-09-21", "ddd@gmail.com");
//
//        cd.add(c);
//        cd.add(c2);
        // Call 들어갈꺼
        RetroBuilder retro = new RetroBuilder();
        Call<List<CalendarData>> call2 = retro.service.getCalendarAll("born7sh@gmail.com", "Bearer " + checkFirst);
        call2.enqueue(new Callback<List<CalendarData>>() {
            @Override
            public void onResponse(Call<List<CalendarData>> call, Response<List<CalendarData>> response) {
                if (response.isSuccessful()) {
                    Log.v("알림", "드가자");
                    ld = response.body();
                    cd.addAll(ld);

                    Log.e("알림", "들어온거 확실하냐? " + cd.size());

                    setCalendarList();
                    addCalendarDot();
                    materialCalendarView.addDecorators(
                            new EventDecorator(Color.RED, dates),
                            new SundayDecorator(),
                            new SaturdayDecorator(),
                            new OneDayDecorator());

                }

            }

            @Override
            public void onFailure(Call<List<CalendarData>> call, Throwable t) {
                Log.e("알림", "실패" + t.getMessage());
            }
        });

    }

    void setCalendarList() {
//        for (int i = 0; i < cd.size(); i++) {
//
//            Log.i("알림", "문자열 : " +  cd.get(i).title);
//
//        }
    }

    void addCalendarDot() {
        for (int i = 0; i < cd.size(); i++) {
            String[] array = cd.get(i).start.split("-");
            Log.i("알림", "날짜 : " +  String.valueOf(Integer.parseInt(array[0]))+  String.valueOf(Integer.parseInt(array[1])-1)+  String.valueOf(Integer.parseInt(array[2].substring(0,2))));
            dates.add(CalendarDay.from(Integer.parseInt(array[0]), Integer.parseInt(array[1]) - 1, Integer.parseInt(array[2].substring(0, 2))));


        }

    }

    @Override
    public void onResume() {
        super.onResume();
        cd.clear();
        getCalendarData();
    }


}

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
import androidx.recyclerview.widget.GridLayoutManager;
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

    private ArrayList<CalendarData> today_cd = new ArrayList<>();

    Button calendar_add;

    private SharedPreferences pref;
    private String checkFirst;

    // 리사이클러뷰 용도
    private Context mContext;
    private RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    // 오늘 날짜 보내기용
    String shot_Day;

    private SharedPreferences pref_id;
    private String user_id;

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

        pref_id = getActivity().getSharedPreferences("id", Activity.MODE_PRIVATE);
        user_id = pref_id.getString("id","NULL");

        materialCalendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        calendar_add = (Button) view.findViewById(R.id.calendar_add);


        mContext = view.getContext();
        recyclerView = view.findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(mContext);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

//        LinearLayoutManager manager = new LinearLayoutManager(mContext);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);


        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2021, 8, 1)) // 달력의 시작
                .setMaximumDate(CalendarDay.from(2022, 11, 31)) // 달력의 끝
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();


        Date mDate = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        shot_Day = simpleDate.format(mDate);

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                today_cd.clear();

                String Year = Integer.toString(date.getYear());
                String Month = "-"+Integer.toString(date.getMonth() + 1);
                String Day = "-"+Integer.toString(date.getDay());


                if (date.getMonth() + 1 < 10) {
                    Month = "-0"+Integer.toString(date.getMonth() + 1);
                }
                if(date.getDay() < 10){
                    Day = "-0"+Integer.toString(date.getDay());
                }

                shot_Day = Year + Month + Day;
                Log.i("알림", "이떄 할일은? " + shot_Day);

                for (int i = 0; i < cd.size(); i++) {
                    String a = cd.get(i).start.substring(0, 10);
                    Log.i("알림", "a = " + a);
                    if (a.equals(shot_Day)) {
                        today_cd.add(cd.get(i));
                        Log.i("알림", "이떄 할일은? " + cd.get(i).description);
                    }
                }

                CalendarAdapter adapter = new CalendarAdapter(mContext, today_cd);
                recyclerView.setAdapter(adapter);
                Toast.makeText(getContext(), shot_Day, Toast.LENGTH_SHORT).show();
            }
        });

        calendar_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Activity_Calendar_add.class);
                intent.putExtra("shot_Day", shot_Day);
                startActivity(intent);
            }
        });
    }

    void getCalendarData() {

        RetroBuilder retro = new RetroBuilder();
        Call<List<CalendarData>> call2 = retro.service.getCalendarAll(user_id, "Bearer " + checkFirst);
        call2.enqueue(new Callback<List<CalendarData>>() {
            @Override
            public void onResponse(Call<List<CalendarData>> call, Response<List<CalendarData>> response) {
                if (response.isSuccessful()) {
                    Log.v("알림", "드가자");
                    ld = response.body();
                    cd.addAll(ld);
                    setCalendarList();
                    Log.e("알림", "들어온거 확실하냐? " + cd.size());

                }

            }

            @Override
            public void onFailure(Call<List<CalendarData>> call, Throwable t) {
                Log.e("알림", "실패" + t.getMessage());
                setCalendarList();

            }
        });


    }

    void setCalendarList() {
        addCalendarDot();
        materialCalendarView.addDecorators(
                new EventDecorator(Color.RED, dates),
                new SundayDecorator(),
                new SaturdayDecorator(),
                new OneDayDecorator());
    }

    void addCalendarDot() {
        for (int i = 0; i < cd.size(); i++) {
            String[] array = cd.get(i).start.split("-");
            Log.i("알림", "날짜 : " + String.valueOf(Integer.parseInt(array[0])) + String.valueOf(Integer.parseInt(array[1]) - 1) + String.valueOf(Integer.parseInt(array[2].substring(0, 2))));
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

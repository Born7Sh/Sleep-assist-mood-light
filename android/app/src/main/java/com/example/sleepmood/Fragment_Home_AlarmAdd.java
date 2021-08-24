package com.example.sleepmood;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class Fragment_Home_AlarmAdd extends Fragment {

    MainActivity activity;
    Calendar c = Calendar.getInstance();
    // 데이터 공유 용도
    ArrayList<AlarmData> alarmData = new ArrayList<AlarmData>();
    SharedPreferences sharedPreferences;
    private Gson gson = new GsonBuilder().create();
    private String json;
    int alarmNum;

    //확인 눌렀을 때 보낼 데이터
    String alarmRegister_Date;
    String alarmRegsiter_Time;
    ArrayList<Integer> alarmRegister_Week = new ArrayList<Integer>();

    // 알람 설정용
    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    Context context;
    PendingIntent pendingIntent;

    private int alarmIdx;
    private int editIdx;
    private int alarmFixNum; // Main 에서 넘어온 수정해야할 index 넘버

    // 캘린더 날짜 설정용
    private int mYear;
    private int mMonth;
    private int mDay;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment__home__alarm_add, container, false);
        return rootView;
    }

    //
    private String getAmPm(int hour) {
        if (hour >= 12) {
            hour = hour - 12;
            return "PM " + Integer.toString(hour);
        } else {
            return "AM " + Integer.toString(hour);
        }
    }

    private String setMinute(int min) {
        if (min >= 10)
            return (Integer.toString(min) + "");
        else
            return ("0" + Integer.toString(min));
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        // '쉐어드이름' 과 동일한 xml 파일을 호출함(없으면 새로 생성함)
//        SharedPreferences sharedPreferences = context.getSharedPreferences("AlarmData", MODE_PRIVATE);
//        // SharedPreferences 저장을 위한 Editor 생성하기
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        // Gson 사용을 위한 Gson 객체 선언
//        Gson gson = new Gson();
//        // '저장할_객체'를 '저장할_객체_클래스명'의 형식에 맞추어 String으로 변환함
//        String 스트링_변수명 = gson.toJson(저장할_객체, AlarmData.class );
//        // '쉐어드이름' xml 파일 안에 'String_식별값'이라는 이름으로 '스트링_변수명'의 내용을 저장함
//        editor.putString("String_식별값", 스트링_변수명);
//        editor.commit();


        Bundle bundle = getArguments(); //번들 안의 텍스트 불러오기
        alarmFixNum = bundle.getInt("AlarmNum"); //fragment1의 TextView에 전달 받은 text 띄우기


        ArrayList<AlarmData> items = new ArrayList<>();
        SharedPreferences sp = getActivity().getSharedPreferences("AlarmData", Context.MODE_PRIVATE);
        Gson gson = new GsonBuilder().create();
        Collection<?> values = sp.getAll().values();

        for (Object value : values) {
            String json = (String) value;
            items.add(gson.fromJson(json, AlarmData.class));
        }

        alarmNum = items.size();

        if (alarmFixNum == -1) {
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
        } else { // 넘어온 날짜로 설정
            String[] strArr = items.get(alarmFixNum).getAlarmDate().split("/");
            mYear = Integer.parseInt(strArr[0]);
            mMonth = Integer.parseInt(strArr[1]);
            mDay = Integer.parseInt(strArr[2]);
        }

        Button alarmCancel = (Button) view.findViewById(R.id.alarmCancel);
        Button alarmRegister = (Button) view.findViewById(R.id.alarmRegister);
        TextView alarmSetting_date = (TextView) view.findViewById(R.id.alarmSetting_date);
        ImageView alarmCalendar = (ImageView) view.findViewById(R.id.alarmCalendar);
        TimePicker alarmTime = (TimePicker) view.findViewById(R.id.alarmTime);

        CheckBox checkbox_0 = (CheckBox) view.findViewById(R.id.checkbox_0);
        CheckBox checkbox_1 = (CheckBox) view.findViewById(R.id.checkbox_1);
        CheckBox checkbox_2 = (CheckBox) view.findViewById(R.id.checkbox_2);
        CheckBox checkbox_3 = (CheckBox) view.findViewById(R.id.checkbox_3);
        CheckBox checkbox_4 = (CheckBox) view.findViewById(R.id.checkbox_4);
        CheckBox checkbox_5 = (CheckBox) view.findViewById(R.id.checkbox_5);
        CheckBox checkbox_6 = (CheckBox) view.findViewById(R.id.checkbox_6);

        alarmSetting_date.setText(mYear + "/" + (mMonth + 1) + "/" + mDay);

        alarmRegister_Date = (mYear + "/" + (mMonth + 1) + "/" + mDay);


        alarm_manager = (AlarmManager) activity.getSystemService(ALARM_SERVICE);
        final Intent my_intent = new Intent(getContext(), Alarm_Receiver.class);


        alarmCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getContext(), "취소.", Toast.LENGTH_LONG);
                toast.show();

                pendingIntent = PendingIntent.getBroadcast(getContext(), alarmNum, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarm_manager.cancel(pendingIntent);

            }
        });

        alarmRegister.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (checkbox_0.isChecked()) {
                    alarmRegister_Week.add(1);
                } else {
                    alarmRegister_Week.add(0);
                }
                if (checkbox_1.isChecked()) {
                    alarmRegister_Week.add(1);
                } else {
                    alarmRegister_Week.add(0);
                }
                if (checkbox_2.isChecked()) {
                    alarmRegister_Week.add(1);
                } else {
                    alarmRegister_Week.add(0);
                }
                if (checkbox_3.isChecked()) {
                    alarmRegister_Week.add(1);
                } else {
                    alarmRegister_Week.add(0);
                }
                if (checkbox_4.isChecked()) {
                    alarmRegister_Week.add(1);
                } else {
                    alarmRegister_Week.add(0);
                }
                if (checkbox_5.isChecked()) {
                    alarmRegister_Week.add(1);
                } else {
                    alarmRegister_Week.add(0);
                }
                if (checkbox_6.isChecked()) {
                    alarmRegister_Week.add(1);
                } else {
                    alarmRegister_Week.add(0);
                }

                AlarmData a = new AlarmData(1, alarmRegsiter_Time, alarmRegister_Date, alarmRegister_Week);
                //AlarmData b = new AlarmData("no","no", alarmWeek);

                System.out.println(a.getAlarmTime());
                System.out.println(a.getAlarmRepeatWeek());
                System.out.println(a.getAlarmDate());


                // 이건 그냥 출력 확인용
                int hour = alarmTime.getHour();
                int minute = alarmTime.getMinute();
                Toast.makeText(getContext(), "Alarm 예정 " + hour + "시 " + minute + "분", Toast.LENGTH_SHORT).show();

                // 보내기 용 c에 담는 것
                c.set(Calendar.HOUR_OF_DAY, alarmTime.getHour());
                c.set(Calendar.MINUTE, alarmTime.getMinute());

                if (alarmFixNum == -1) {
                    // 수정하는게 아닐때
                    sharedPreferences = getContext().getSharedPreferences("AlarmData", MODE_PRIVATE);
                    Gson gson = new Gson();
                    json = gson.toJson(a, AlarmData.class);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(String.valueOf(alarmNum + 1), json);
                    editor.apply();


                    // 알람 세팅
                    my_intent.putExtra("state", "alarm on");
                    my_intent.putExtra("requestCode", alarmNum + 1);

                    pendingIntent = PendingIntent.getBroadcast(getContext(), alarmNum, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
                } else {
                    // 수정하는게 맞을때


                    // 기존의 알람을 취소허고
                    pendingIntent = PendingIntent.getBroadcast(getContext(), alarmNum, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarm_manager.cancel(pendingIntent);

                    // 값을 수정하고 알람을 다시 설정
                    sharedPreferences = getContext().getSharedPreferences("AlarmData", MODE_PRIVATE);
                    Gson gson = new Gson();
                    json = gson.toJson(a, AlarmData.class);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(String.valueOf(alarmFixNum + 1), json);
                    editor.apply();

                    my_intent.putExtra("state", "alarm on");
                    my_intent.putExtra("requestCode", alarmFixNum + 1);

                    pendingIntent = PendingIntent.getBroadcast(getContext(), alarmFixNum, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
                }
                // 알람 데이터 설정
                activity.onFragmentChange("alarmList");

            }
        });

        //바뀔 때 값 바꾸기
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                alarmSetting_date.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
                alarmRegister_Date = year + "/" + (month + 1) + "/" + dayOfMonth;
            }
        }, mYear, mMonth, mDay);

        alarmCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        alarmTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hour, int min) {

                String ampmhour = getAmPm(hour);
                String minute = setMinute(min);

                Toast.makeText(getContext(), "ampmhour : " + ampmhour + ", min : " + minute, Toast.LENGTH_LONG).show();
                alarmRegsiter_Time = ampmhour + minute;
            }
        });

    }


}
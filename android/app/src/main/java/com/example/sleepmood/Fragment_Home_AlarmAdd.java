package com.example.sleepmood;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class Fragment_Home_AlarmAdd extends Fragment {

    MainActivity activity;

    // 데이터 공유 용도
    private SharedViewModel sharedViewModel;


    //확인 눌렀을 때 보낼 데이터
    String alarmRegister_Date;
    String alarmRegsiter_Time;
    ArrayList<Integer> alarmRegister_Week = new ArrayList<Integer>();

    // 알람 설정용
    AlarmManager alarm_manager;
    PendingIntent pendingIntent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity)getActivity();
    }

    public void onDetach(){
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
        if (hour >= 12){
            hour = hour-12;
            return "PM " + Integer.toString(hour) ;}
        else {
            return "AM " + Integer.toString(hour);
        }
    }
    private String setMinute(int min) {
        if (min >= 10)
            return (Integer.toString(min) + "") ;
        else
            return ("0" + Integer.toString(min));
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

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

        alarmRegister_Date = (mYear + "/" + (mMonth + 1) + "/" + mDay) ;

        alarm_manager = (AlarmManager) activity.getSystemService(ALARM_SERVICE);
        final Intent my_intent = new Intent(getContext(), Alarm_Reciver.class);


        alarmCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getContext(), "취소.", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        alarmRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkbox_0.isChecked()){ alarmRegister_Week.add(1);} else {alarmRegister_Week.add(0);}
                if(checkbox_1.isChecked()){ alarmRegister_Week.add(1);} else {alarmRegister_Week.add(0);}
                if(checkbox_2.isChecked()){ alarmRegister_Week.add(1);} else {alarmRegister_Week.add(0);}
                if(checkbox_3.isChecked()){ alarmRegister_Week.add(1);} else {alarmRegister_Week.add(0);}
                if(checkbox_4.isChecked()){ alarmRegister_Week.add(1);} else {alarmRegister_Week.add(0);}
                if(checkbox_5.isChecked()){ alarmRegister_Week.add(1);} else {alarmRegister_Week.add(0);}
                if(checkbox_6.isChecked()){ alarmRegister_Week.add(1);} else {alarmRegister_Week.add(0);}
                AlarmData a = new AlarmData(alarmRegsiter_Time, alarmRegister_Date, alarmRegister_Week);
                //AlarmData b = new AlarmData("no","no", alarmWeek);
                System.out.println(a.getAlarmTime());
                System.out.println(a.getAlarmRepeatWeek());
                System.out.println(a.getAlarmDate());

                sharedViewModel.addLiveAlarmData(a);
                activity.onFragmentChange("alarmList");

            }
        });

        //바뀔 때 값 바꾸기
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                alarmSetting_date.setText( year + "/" + (month + 1) + "/" + dayOfMonth);
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
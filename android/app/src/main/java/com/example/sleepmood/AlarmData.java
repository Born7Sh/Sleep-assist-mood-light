package com.example.sleepmood;

import java.util.ArrayList;

public class AlarmData {
    public int alarmCode;
    public String alarmTime;
    public String alarmDate;
    public ArrayList<Integer> alarmRepeatWeek;
    public boolean OnOf;


    public AlarmData(int alarmCode, String alarmTime, String alarmDate, ArrayList<Integer> alarmRepeatWeek) {
        this.alarmCode = alarmCode;
        this.alarmTime = alarmTime;
        this.alarmDate = alarmDate;
        this.alarmRepeatWeek = alarmRepeatWeek;
        this.OnOf=true;
    }


    public String getAlarmTime() {
        return alarmTime;
    }

    public String getAlarmDate() {
        return alarmDate;
    }

    public void setAlarmDate(String alarmDate) {
        this.alarmDate = alarmDate;
    }

    public ArrayList<Integer> getAlarmRepeatWeek() {
        return alarmRepeatWeek;
    }

    public void setAlarmRepeatWeek(ArrayList<Integer> alarmRepeatWeek) {
        this.alarmRepeatWeek = alarmRepeatWeek;

    }

    public int getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(int alarmCode) {
        this.alarmCode = alarmCode;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }



    public boolean isOnOf() {
        return OnOf;
    }

    public void setOnOf(boolean onOf) {
        OnOf = onOf;
    }

}

package com.example.sleepmood;

import java.util.ArrayList;

public class AlarmData {

    public String alarmTime;
    public String alarmDate;
    public ArrayList<Integer> alarmRepeatWeek = new ArrayList<>(6);
    // 0 = 일, 1 = 월 .... 6 = 토

    public AlarmData(String alarmTime, String alarmDate, ArrayList<Integer> alarmRepeatWeek){
        this.alarmTime = alarmTime;
        this.alarmDate = alarmDate;
        this.alarmRepeatWeek = alarmRepeatWeek;
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

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }




}

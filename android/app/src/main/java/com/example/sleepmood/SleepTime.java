package com.example.sleepmood;

public class SleepTime {

    String start;
    String elements;
    String email;
    String alarm_time;

    public SleepTime(String start,String elements,String email){
        this.start = start;
        this.elements = elements;
        this.email = email;
    }

    public void setAlarm_time(String alarm_time) {
        this.alarm_time = alarm_time;
    }
}

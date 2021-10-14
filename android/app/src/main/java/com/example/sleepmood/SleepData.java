package com.example.sleepmood;

public class SleepData {
    private float x;
    private float y;
    private int gyro_sensor;
    private int sleep_id;
    private String time;

    public SleepData(float x, float y, int gyro_sensor, int sleep_id, String time){
        this.x = x;
        this.y = y;
        this.gyro_sensor = gyro_sensor;
        this.sleep_id = sleep_id;
        this.time = time;
    }
}

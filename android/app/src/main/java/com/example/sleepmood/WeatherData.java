package com.example.sleepmood;

public class WeatherData {
    String datetime;
    Float temperature;
    int humidity;
    int precipitation_type;
    int fine_dust10;
    int fine_dust2_5;


    public WeatherData(String datetime, Float temperature, int humidity, int precipitation_type, int fine_dust10, int fine_dust2_5) {
        this.datetime = datetime;
        this.temperature = temperature;
        this.humidity = humidity;
        this.precipitation_type = precipitation_type;
        this.fine_dust10 = fine_dust10;
        this.fine_dust2_5 = fine_dust2_5;
    }

}

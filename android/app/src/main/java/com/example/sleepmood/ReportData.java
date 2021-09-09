package com.example.sleepmood;

import java.util.List;

public class ReportData {
    private String email;
    private int score;
    private double sleeping_time;
    private List<String> element;
    private String date;

    public ReportData(String email, int score, double sleeping_time, List<String> element, String date){
        this.email = email;
        this.score = score;
        this.sleeping_time = sleeping_time;
        this.element = element;
        this.date = date;
    }

    public int getScore(){
        return this.score;
    }
}

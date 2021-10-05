package com.example.sleepmood;

public class ReportData {
    private int score;
    private double sleeping_time;
    private int sleepid;

    public ReportData(int score, double sleeping_time, int sleepid) {
        this.score = score;
        this.sleeping_time = sleeping_time;
        this.sleepid = sleepid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getSleeping_time() {
        return sleeping_time;
    }

    public void setSleeping_time(double sleeping_time) {
        this.sleeping_time = sleeping_time;
    }

    public int getSleepid() {
        return sleepid;
    }

    public void setSleepid(int sleepid) {
        this.sleepid = sleepid;
    }
}

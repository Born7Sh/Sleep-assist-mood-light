package com.example.sleepmood;

public class ReportData {
    private String email;
    private int score;
    private double sleeping_time;
    private String elements;
    private String date;

    public ReportData(String email, int score, double sleeping_time, String elements, String date){
        this.email = email;
        this.score = score;
        this.sleeping_time = sleeping_time;
        this.elements = elements;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getElements() {
        return elements;
    }

    public void setElements(String elements) {
        this.elements = elements;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore(){
        return this.score;
    }
}

package com.example.sleepmood;

public class DiaryData {
    private String email;
    private String date;
    private String description;

    public DiaryData(String email, String date, String description) {
        this.email = email;
        this.date = date;
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

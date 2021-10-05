package com.example.sleepmood;

public class DiaryData {
    private String email;
    private String datetime;
    private String description;

    public DiaryData(String email, String datetime, String description) {
        this.email = email;
        this.datetime = datetime;
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return datetime;
    }

    public void setDate(String date) {
        this.datetime = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.example.sleepmood;

public class DiaryData {
    private String diary_id;
    private String diary_date;
    private String diary_content;

    public DiaryData(String diary_id, String diary_date, String diary_content) {
        this.diary_id = diary_id;
        this.diary_date = diary_date;
        this.diary_content = diary_content;
    }

    public String getDiary_id() {
        return diary_id;
    }

    public void setDiary_id(String diary_id) {
        this.diary_id = diary_id;
    }

    public String getDiary_date() {
        return diary_date;
    }

    public void setDiary_date(String diary_date) {
        this.diary_date = diary_date;
    }

    public String getDiary_content() {
        return diary_content;
    }

    public void setDiary_content(String diary_content) {
        this.diary_content = diary_content;
    }
}

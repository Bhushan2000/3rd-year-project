package com.example.myvenue.Model;

public class BookingsModel {
    private String name;
    private String date_time;

    public BookingsModel(String name, String date_time) {
        this.name = name;
        this.date_time = date_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }
}

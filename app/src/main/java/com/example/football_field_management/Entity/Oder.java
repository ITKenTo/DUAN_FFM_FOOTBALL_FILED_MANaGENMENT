package com.example.football_field_management.Entity;

public class Oder {
      int id;
      String start_time;
      String end_time;
      String pitch_name;
      double price;
      boolean ischeck;

    public Oder(int id, String start_time, String end_time, String pitch_name, double price) {
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.pitch_name = pitch_name;
        this.price = price;
    }

    public Oder() {
    }

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getPitch_name() {
        return pitch_name;
    }

    public void setPitch_name(String pitch_name) {
        this.pitch_name = pitch_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

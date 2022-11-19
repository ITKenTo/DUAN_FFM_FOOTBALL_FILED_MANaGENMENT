package com.example.football_field_management.Entity;

public class Oder {
      int id;
      String yard_song;
      String pitch_name;
      double price;

    public Oder(int id, String yard_song, String pitch_name, double price) {
        this.id = id;
        this.yard_song = yard_song;
        this.pitch_name = pitch_name;
        this.price = price;
    }

    public Oder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYard_song() {
        return yard_song;
    }

    public void setYard_song(String yard_song) {
        this.yard_song = yard_song;
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

package com.example.football_field_management.Entity;

public class Statistical {
    private int id_pitch;
    private double total;
    private int soluong;

    public Statistical(int id_pitch, double total, int soluong) {
        this.id_pitch = id_pitch;
        this.total = total;
        this.soluong = soluong;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getId_pitch() {
        return id_pitch;
    }

    public void setId_pitch(int id_pitch) {
        this.id_pitch = id_pitch;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

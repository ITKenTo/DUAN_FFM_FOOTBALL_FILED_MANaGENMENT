package com.example.football_field_management.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "soccer_refereeEntity")
public class Soccer_refereeEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id_Soccer_referee;
    private String fullname_referee;
    private String price;

    public Soccer_refereeEntity(int id_Soccer_referee, String fullname_referee, String price) {
        this.id_Soccer_referee = id_Soccer_referee;
        this.fullname_referee = fullname_referee;
        this.price = price;
    }

    public Soccer_refereeEntity() {
    }

    public int getId_Soccer_referee() {
        return id_Soccer_referee;
    }

    public void setId_Soccer_referee(int id_Soccer_referee) {
        this.id_Soccer_referee = id_Soccer_referee;
    }

    public String getFullname_referee() {
        return fullname_referee;
    }

    public void setFullname_referee(String fullname_referee) {
        this.fullname_referee = fullname_referee;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

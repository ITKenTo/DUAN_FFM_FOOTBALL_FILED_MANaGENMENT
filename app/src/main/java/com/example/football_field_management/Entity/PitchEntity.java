package com.example.football_field_management.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (foreignKeys = {@ForeignKey(entity = YardTypeEntity.class,
        parentColumns = "id_yardTye",
        childColumns = "id_yardTye",
        onDelete = ForeignKey.CASCADE)
      },tableName = "Pitch" )

public class PitchEntity implements Serializable {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    int id_pitch;
    String pitch_name;
    double price;
    int id_yardTye;
    String status;

    public PitchEntity() {
    }

    public PitchEntity(int id_pitch, String pitch_name, double price, int id_yardTye,String status) {
        this.id_pitch = id_pitch;
        this.pitch_name = pitch_name;
        this.price = price;
        this.id_yardTye = id_yardTye;
        this.status=status;
    }

    public int getId_pitch() {
        return id_pitch;
    }

    public void setId_pitch(int id_pitch) {
        this.id_pitch = id_pitch;
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

    public int getId_yardTye() {
        return id_yardTye;
    }

    public void setId_yardTye(int id_yardTye) {
        this.id_yardTye = id_yardTye;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

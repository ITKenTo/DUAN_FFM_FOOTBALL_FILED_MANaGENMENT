package com.example.football_field_management.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = {
        @ForeignKey(entity = UserEntity.class,
        parentColumns = "Username",
        childColumns = "Username",
        onDelete = ForeignKey.CASCADE),

        @ForeignKey(entity = PitchEntity.class,
                parentColumns = "id_pitch",
                childColumns = "id_pitch",
                onDelete = ForeignKey.CASCADE)

       },tableName = "Order_PitchEntity")

public class Order_PitchEntity {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    private int id_order;
    private String Pitch_name;
    private String Username;
    private String order_time;
    private String start_time;
    private String end_time;
    private int id_pitch;
    private double total;

    public Order_PitchEntity(int id_order, String pitch_name, String Username, String order_time, String start_time, String end_time, int id_pitch, double total) {
        this.id_order = id_order;
        Pitch_name = pitch_name;
        this.Username = Username;
        this.order_time = order_time;
        this.start_time = start_time;
        this.end_time = end_time;
        this.id_pitch = id_pitch;
        this.total = total;
    }

    public Order_PitchEntity() {
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public String getPitch_name() {
        return Pitch_name;
    }

    public void setPitch_name(String pitch_name) {
        Pitch_name = pitch_name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
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

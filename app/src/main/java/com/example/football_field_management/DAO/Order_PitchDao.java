package com.example.football_field_management.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.football_field_management.Entity.Order_PitchEntity;

import java.util.List;

@Dao
public interface Order_PitchDao {
    @Insert
    void insert(Order_PitchEntity order_pitch);

    @Delete
    void delete(Order_PitchEntity order_pitch);

    @Update
    void update(Order_PitchEntity order_pitch);


    @Query("select * from Order_PitchEntity")
    List<Order_PitchEntity> getselect();

    @Query("select * from Order_PitchEntity where Username = :username")
    List<Order_PitchEntity> getselectuser(String username);


}

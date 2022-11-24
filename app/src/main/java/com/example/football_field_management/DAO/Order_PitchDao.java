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

    @Query("select * from Order_PitchEntity where id_pitch =:idpitch")
    List<Order_PitchEntity> getselectpitch(int idpitch);

    @Query("select * from Order_PitchEntity where start_time =:start_time and end_time =:end_time and Pitch_name =:pitchname and order_time =:datetime")
    Order_PitchEntity CheckCa(String start_time, String end_time, String pitchname, String datetime);

    @Query("SELECT SUM(total) as doanhThu FROM order_pitchentity where Username=:username")
    double doanhthu(String username);

    @Query("SELECT COUNT(id_pitch) as doanhThu FROM order_pitchentity where Username=:username")
    int count(String username);

}

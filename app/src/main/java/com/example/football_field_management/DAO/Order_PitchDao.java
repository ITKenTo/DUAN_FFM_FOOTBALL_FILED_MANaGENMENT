package com.example.football_field_management.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.football_field_management.Entity.Order_PitchEntity;
import com.example.football_field_management.Entity.Statistical;

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

    @Query("select * from Order_PitchEntity where status=:status")
    List<Order_PitchEntity> getselectStatus(String status);

    @Query("select * from Order_PitchEntity where Username = :username")
    List<Order_PitchEntity> getselectuser(String username);

    @Query("select * from Order_PitchEntity where id_pitch =:idpitch")
    List<Order_PitchEntity> getselectpitch(int idpitch);

    @Query("select * from Order_PitchEntity where start_time =:start_time and end_time =:end_time and id_pitch =:pitch_id and order_time =:datetime and status=:status")
    Order_PitchEntity CheckCa(String start_time, String end_time, int pitch_id, String datetime,String status);

    @Query("SELECT SUM(total) as doanhThu FROM order_pitchentity where Username=:username and status=:status")
    double doanhthu(String username,String status);

    @Query("SELECT COUNT(id_pitch) as doanhThu FROM order_pitchentity where Username=:username and status=:status")
    int count(String username,String status);

    @Query("SELECT SUM(total) as doanhThu FROM order_pitchentity")
    double doanhthuYard();

    @Query("SELECT SUM(total) as doanhThu FROM order_pitchentity where order_time =:datetime")
    double date(String datetime);

    @Query("SELECT SUM(total) as doanhThu FROM order_pitchentity where order_time=:date")
    double doanhthuYard(String date);
    @Query("SELECT SUM(total) as doanhThu FROM order_pitchentity where order_time between :datefirst and :datelast")
    double datedoanhthu(String datefirst, String datelast);
    @Query("select count(id_order) from order_pitchentity where id_pitch=:idpitch")
    int solandatsan(int idpitch);
    @Query("select sum(total) as doanhthu from order_pitchentity where id_pitch=:idpitch")
    double tongtien(int idpitch);


    @Query("SELECT id_pitch, SUM(total) as total, COUNT(id_pitch) as soluong FROM order_pitchentity where status=:status GROUP BY id_pitch ORDER BY soluong DESC ")
    List<Statistical> getstatistical(String status);
}

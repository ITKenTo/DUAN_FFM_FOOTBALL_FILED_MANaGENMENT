package com.example.football_field_management.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.football_field_management.Entity.YardTypeEntity;

import java.util.List;

@Dao
public interface YardTypeDao {

    @Insert
    void insert(YardTypeEntity yardType);

    @Delete
    void delete(YardTypeEntity yardType);

    @Query("select * from yardType")
    List<YardTypeEntity> getselect();

    @Query("select * from yardType where id_yardTye =:id_yardType")
    YardTypeEntity getselectID(int id_yardType);

    @Query("select filedtypename from yardType Group by filedtypename")
    List<String> getfiledtypename();

    @Query("select * from yardType where filedtypename =:filedtypename")
    YardTypeEntity getfiledtypefillname(String filedtypename);

}

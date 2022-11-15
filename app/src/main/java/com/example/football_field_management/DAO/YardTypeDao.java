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


}

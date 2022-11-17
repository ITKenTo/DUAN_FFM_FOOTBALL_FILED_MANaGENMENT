package com.example.football_field_management.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.football_field_management.Entity.Soccer_refereeEntity;

import java.util.List;

@Dao
public interface Soccer_refereeDao {

    @Insert
    void insert(Soccer_refereeEntity soccer_referee);

    @Delete
    void delete(Soccer_refereeEntity soccer_referee);

    @Update
    void update(Soccer_refereeEntity soccer_referee);


    @Query("select * from soccer_refereeEntity")
    List<Soccer_refereeEntity> getselect();

}

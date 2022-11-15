package com.example.football_field_management.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.football_field_management.Entity.PitchEntity;

import java.util.List;

@Dao
public interface PitchDao {

    @Insert
    void insert(PitchEntity pitch);

    @Delete
    void delete(PitchEntity pitch);

    @Query("select * from pitch")
    List<PitchEntity> getselect();
}

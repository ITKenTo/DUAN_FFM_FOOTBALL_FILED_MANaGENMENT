package com.example.football_field_management.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.football_field_management.Entity.PitchEntity;
import com.example.football_field_management.Entity.YardTypeEntity;

import java.util.List;

@Dao
public interface PitchDao {

    @Insert
    void insert(PitchEntity pitch);

    @Delete
    void delete(PitchEntity pitch);

    @Update
    void update(PitchEntity pitch);


    @Query("select * from pitch")
    List<PitchEntity> getselect();

    @Query("select * from pitch where id_yardTye =:idyardType ")
    List<PitchEntity> getselectpitch(int idyardType );

    @Query("select * from pitch where pitch_name =:pitchname")
    PitchEntity pitchID(String pitchname);

    @Query("select * from pitch where id_pitch =:id_pitch")
    PitchEntity pitchID1(int id_pitch);
}


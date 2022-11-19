package com.example.football_field_management.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.football_field_management.Entity.RegisterEntity;

import java.util.List;

@Dao
public interface RegisterDAO {
    @Insert
    void insert(RegisterEntity register);

    @Delete
    void delete(RegisterEntity register);

    @Update
    void update(RegisterEntity register);


    @Query("select * from register")
    List<RegisterEntity> getselect();

    @Query("select * from register where register.Username = :Username and register.id_order like :id_order limit 1")
    RegisterEntity checkRegisted(String Username, int id_order);
}

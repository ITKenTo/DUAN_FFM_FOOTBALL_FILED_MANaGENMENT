package com.example.football_field_management.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.football_field_management.Entity.UserEntity;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("select * from user")
    List<UserEntity> getSelect();

    @Insert
    void insert(UserEntity user);

    @Update
    void update(UserEntity user);

    @Delete
    void delete(UserEntity user);

    @Query("select * from user where Username = :username and Password = :password limit 1")
    UserEntity getUser(String username, String password);

    @Query(" select * from user where Username = :username ")
    UserEntity getIdUser(String username);
}

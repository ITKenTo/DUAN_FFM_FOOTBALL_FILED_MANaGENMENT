package com.example.football_field_management.DATABASE;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.football_field_management.DAO.UserDAO;
import com.example.football_field_management.Entity.UserEntity;

@Database(entities = {UserEntity.class},version = 1)
public abstract class RoomDatabase_DA extends RoomDatabase {
    private static final String DATABASE="football_field_management1.db";
    private static RoomDatabase_DA intences;

    public static synchronized RoomDatabase_DA getInstance(Context context) {
        if (intences==null){
            intences= Room.databaseBuilder(context.getApplicationContext(),RoomDatabase_DA.class,DATABASE)
                    .allowMainThreadQueries().build();
        }
        return intences;
    }

    public abstract UserDAO userDAO();
}

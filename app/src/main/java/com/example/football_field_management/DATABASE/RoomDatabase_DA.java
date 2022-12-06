package com.example.football_field_management.DATABASE;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.football_field_management.DAO.Order_PitchDao;
import com.example.football_field_management.DAO.PitchDao;
import com.example.football_field_management.DAO.RegisterDAO;
import com.example.football_field_management.DAO.Soccer_refereeDao;
import com.example.football_field_management.DAO.UserDAO;
import com.example.football_field_management.DAO.YardTypeDao;
import com.example.football_field_management.Entity.Order_PitchEntity;
import com.example.football_field_management.Entity.PitchEntity;
import com.example.football_field_management.Entity.RegisterEntity;
import com.example.football_field_management.Entity.Soccer_refereeEntity;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.Entity.YardTypeEntity;

@Database(entities = {UserEntity.class, YardTypeEntity.class, PitchEntity.class, Soccer_refereeEntity.class, Order_PitchEntity.class, RegisterEntity.class},version = 1)
public abstract class RoomDatabase_DA extends RoomDatabase {
    private static final String DATABASE="football_field_management12.db";
    private static RoomDatabase_DA intences;

    public static synchronized RoomDatabase_DA getInstance(Context context) {
        if (intences==null){
            intences= Room.databaseBuilder(context.getApplicationContext(),RoomDatabase_DA.class,DATABASE)
                    .allowMainThreadQueries().build();
        }
        return intences;
    }

    public abstract UserDAO userDAO();
    public abstract YardTypeDao yardTypeDao();
    public abstract PitchDao pitchDao();
    public abstract Order_PitchDao order_pitchDao();
    public abstract RegisterDAO registerDAO();


}

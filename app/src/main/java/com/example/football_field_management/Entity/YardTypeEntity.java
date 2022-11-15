package com.example.football_field_management.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "yardType")
public class YardTypeEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    int id_yardTye ;
    @ColumnInfo (name = "filedtypename")
    String filedtypename;

    public YardTypeEntity(int id_yardTye, String filedtypename) {
        this.id_yardTye = id_yardTye;
        this.filedtypename = filedtypename;
    }

    public int getId_yardTye() {
        return id_yardTye;
    }

    public void setId_yardTye(int id_yardTye) {
        this.id_yardTye = id_yardTye;
    }

    public String getFiledtypename() {
        return filedtypename;
    }

    public void setFiledtypename(String filedtypename) {
        this.filedtypename = filedtypename;
    }
}

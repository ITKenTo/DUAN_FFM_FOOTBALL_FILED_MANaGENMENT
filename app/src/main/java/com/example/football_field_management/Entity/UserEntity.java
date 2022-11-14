package com.example.football_field_management.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "user")
public class UserEntity implements Serializable {

    @PrimaryKey
    @NonNull
    private String Username;
    @ColumnInfo(name = "Fullname")
    private String Fullname;
    @ColumnInfo (name = "Phonenumber")
    private String Phonenumber;
    @ColumnInfo (name = "decentralization")
    private String decentralization;
    @ColumnInfo (name = "Password")
    private String Password;

    public UserEntity(@NonNull String username, String fullname, String phonenumber, String decentralization, String password) {
        Username = username;
        Fullname = fullname;
        Phonenumber = phonenumber;
        this.decentralization = decentralization;
        Password = password;
    }

    public UserEntity() {
    }

    public String getDecentralization() {
        return decentralization;
    }

    public void setDecentralization(String decentralization) {
        this.decentralization = decentralization;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}

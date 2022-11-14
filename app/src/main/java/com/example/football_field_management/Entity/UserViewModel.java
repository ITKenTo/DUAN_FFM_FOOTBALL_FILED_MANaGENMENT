package com.example.football_field_management.Entity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class UserViewModel extends ViewModel {
    
    private final MutableLiveData<UserEntity> user = new MutableLiveData<>();

    public void setUser(UserEntity user){this.user.setValue(user);}

    public LiveData getUser(){return this.user;}
}

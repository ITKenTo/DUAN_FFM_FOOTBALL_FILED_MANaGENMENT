package com.example.football_field_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.Entity.YardTypeEntity;
import com.example.football_field_management.Fragment.HomeYardOwnerFragment;
import com.example.football_field_management.Fragment.PitchFragment;
import com.example.football_field_management.Fragment.SoccerRefereeFragment;
import com.example.football_field_management.Fragment.SupportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class HomeYardOwnerActivity extends AppCompatActivity {
    List<YardTypeEntity> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_yardowner);
        BottomNavigationView nav_botton= findViewById(R.id.botonyardowner);
   

        getSupportFragmentManager().beginTransaction().replace(R.id.framgeryardowner,new HomeYardOwnerFragment()).commit();


        UserEntity user = (UserEntity) getIntent().getSerializableExtra("user");
        Log.d("TAG", user.getUsername());
//        data=user.getUsername();
        nav_botton.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btt_homeyardowner:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framgeryardowner,new HomeYardOwnerFragment()).commit();
                        return true;
                    case R.id.btt_pitch:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framgeryardowner,new PitchFragment()).commit();
                        return true;
                    case R.id.btt_soccerreferee:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framgeryardowner,new SoccerRefereeFragment()).commit();
                        return true;
                    case R.id.btt_supportyardowner:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framgeryardowner,new SupportFragment()).commit();
                        return true;

                }
                return false;
            }
        });
    }
//    public static HomeYardOwnerActivity getActivityInstance()
//    {
//        return INSTANCE;
//    }
//
//    public String getData()
//    {
//        return this.data;
//    }
}
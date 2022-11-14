package com.example.football_field_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.Fragment.HomeYardOwnerFragment;
import com.example.football_field_management.Fragment.PitchFragment;
import com.example.football_field_management.Fragment.SupportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeYardOwnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_yardowner);
        BottomNavigationView nav_botton= findViewById(R.id.botonyardowner);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.darker_gray)));
        getSupportFragmentManager().beginTransaction().replace(R.id.framgeryardowner,new HomeYardOwnerFragment()).commit();

        UserEntity user = (UserEntity) getIntent().getSerializableExtra("user");
        Log.d("TAG", user.getUsername());

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
                    case R.id.btt_supportyardowner:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framgeryardowner,new SupportFragment()).commit();
                        return true;

                }
                return false;
            }
        });
    }
}
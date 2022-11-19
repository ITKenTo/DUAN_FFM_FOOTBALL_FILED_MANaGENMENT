package com.example.football_field_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.Entity.UserViewModel;
import com.example.football_field_management.Fragment.HistoryFragment;
import com.example.football_field_management.Fragment.HomeClientFragment;
import com.example.football_field_management.Fragment.SupportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView nav_botton= findViewById(R.id.boton);
        UserEntity user = (UserEntity) getIntent().getSerializableExtra("user");
        Log.d("TAG", user.getUsername());

        UserViewModel model = new ViewModelProvider(this).get(UserViewModel.class);
        model.setUser(user);

     //   getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.darker_gray)));
        getSupportFragmentManager().beginTransaction().replace(R.id.framger,new HomeClientFragment()).commit();

        nav_botton.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btt_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framger,new HomeClientFragment()).commit();
                        return true;
                    case R.id.btt_history:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framger,new HistoryFragment()).commit();
                        return true;
                    case R.id.btt_support:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framger,new SupportFragment()).commit();
                        return true;
                }
                return false;
            }
        });
    }

}
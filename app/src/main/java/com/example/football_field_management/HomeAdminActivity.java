package com.example.football_field_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.Entity.UserViewModel;
import com.example.football_field_management.Fragment.AccountAdminFragment;
import com.example.football_field_management.Fragment.HomeYardOwnerFragment;
import com.example.football_field_management.Fragment.PitchFragment;
import com.example.football_field_management.Fragment.SupportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //Ẩn tên ứng dụng
//        getSupportActionBar().hide(); // Ẩn luôn thanh tiêu đề
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //bật chế độ toàn màn hình
        setContentView(R.layout.activity_home_admin);
   //     getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.system_accent1_300)));

        UserEntity user = (UserEntity) getIntent().getSerializableExtra("user");

        Log.d("TAG", user.getUsername());

        UserViewModel model = new ViewModelProvider(this).get(UserViewModel.class);
        model.setUser(user);


        BottomNavigationView nav_botton= findViewById(R.id.botonadmin);
        getSupportFragmentManager().beginTransaction().replace(R.id.framgeradmin,new AccountAdminFragment()).commit();


        nav_botton.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btt_account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framgeradmin,new AccountAdminFragment()).commit();
                        return true;
                    case R.id.btt_support_admin:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framgeradmin,new SupportFragment()).commit();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
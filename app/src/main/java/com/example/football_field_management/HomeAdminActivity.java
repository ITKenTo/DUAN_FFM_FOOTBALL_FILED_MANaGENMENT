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
import android.widget.Toast;

import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.Entity.UserViewModel;
import com.example.football_field_management.Fragment.AccountAdminFragment;
import com.example.football_field_management.Fragment.HomeYardOwnerFragment;
import com.example.football_field_management.Fragment.PitchFragment;
import com.example.football_field_management.Fragment.SupportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeAdminActivity extends AppCompatActivity {
    private long backPressedTime;
    private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //Ẩn tên ứng dụng
        setContentView(R.layout.activity_home_admin);
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
        if (backPressedTime+2000>System.currentTimeMillis()) {
//            super.onBackPressed();
            toast.cancel();
            finishAffinity();
            return;
        }else {
            toast= Toast.makeText(this, "Press back again to exit the application", Toast.LENGTH_SHORT);
            toast.show();
        }
        backPressedTime= System.currentTimeMillis();

    }
}
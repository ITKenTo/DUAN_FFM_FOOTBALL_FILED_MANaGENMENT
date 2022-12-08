package com.example.football_field_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.football_field_management.Adapter.AccountAdapter;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.Fragment.SupportFragment;
import com.example.football_field_management.Login_Register.LoginActivity;

import java.util.ArrayList;

public class Information_activity extends AppCompatActivity {
TextView tv_fullname,tv_pq,tv_phone,tv_Pquyen;
ImageView back;
RoomDatabase_DA db;
ArrayList<UserEntity> list;
AccountAdapter adapter;
String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        tv_fullname = findViewById(R.id.tv_fullname);
        tv_pq = findViewById(R.id.tv_pq);
        tv_phone = findViewById(R.id.tv_phone);
        tv_Pquyen = findViewById(R.id.tv_Pquyen);
        back = findViewById(R.id.back);
        username= LoginActivity.getActivityInstance().getData();
        db = RoomDatabase_DA.getInstance(getBaseContext());
        UserEntity user = db.userDAO().getIdUser(username);

        tv_fullname.setText(user.getFullname());
        tv_pq.setText(user.getUsername());
        tv_Pquyen.setText(user.getDecentralization());
        tv_phone.setText(user.getPhonenumber());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
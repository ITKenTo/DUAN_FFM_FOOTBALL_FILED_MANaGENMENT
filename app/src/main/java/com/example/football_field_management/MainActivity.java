package com.example.football_field_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.Login_Register.LoginActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<UserEntity> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //Ẩn tên ứng dụng
//        getSupportActionBar().hide(); // Ẩn luôn thanh tiêu đề
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //bật chế độ toàn màn hình
        setContentView(R.layout.activity_main);
        final Handler handler = new Handler();

        list= RoomDatabase_DA.getInstance(this).userDAO().getSelect();
        if (list.size()==0) {
            RoomDatabase_DA.getInstance(this).userDAO().insert(new UserEntity("admin","Admin","4444","ADMIN","adminadmin"));
        }

//        RoomDatabase_DA.getInstance(this).userDAO().insert(new UserEntity("ph26746","Nguyễn Văn Dũng","0392289601","KH","28052003"));
//        RoomDatabase_DA.getInstance(this).userDAO().insert(new UserEntity("ChuSan","Chủ Sân","4444","Chủ sân","chusan2003"));
//        RoomDatabase_DA.getInstance(this).userDAO().insert(new UserEntity("ph26747","Nguyễn Văn Dung","4444","KH","28052003"));
//        RoomDatabase_DA.getInstance(this).userDAO().insert(new UserEntity("ph26748","Nguyễn Văn Đạt","4444","KH","28052003"));


        ImageView imageView=findViewById(R.id.image);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.zoom);
        imageView.startAnimation(animation);

        TextView textView= findViewById(R.id.textView);
        Animation animation1= AnimationUtils.loadAnimation(this,R.anim.zoom);
        textView.startAnimation(animation1);



        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Công việc sẽ thực hiện sau 5s = 5000ms
                Intent intent= new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }
}
package com.example.football_field_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.DefaultTaskExecutor;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.Entity.UserViewModel;
import com.example.football_field_management.Login_Register.LoginActivity;
import com.example.football_field_management.databinding.ActivityChangePasswordBinding;

public class ChangePasswordActivity extends AppCompatActivity {

    ActivityChangePasswordBinding binding;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //Ẩn tên ứng dụng
        getSupportActionBar().hide(); // Ẩn luôn thanh tiêu đề
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //bật chế độ toàn màn hình
        binding= DataBindingUtil.setContentView(this,R.layout.activity_change_password);

        username=LoginActivity.getActivityInstance().getData();
        Log.i("Username", username);


        binding.backHomeadmin.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.tvLogin.setOnClickListener(view -> {
            startActivity(new Intent(getApplication(), LoginActivity.class));
        });


        binding.btnPassword.setOnClickListener(view -> {
                Validate();

                if (Validate()>0){
                  UserEntity user1= RoomDatabase_DA.getInstance(this).userDAO().getIdUser(username);
                    user1.setPassword(binding.edPasswordnew.getText().toString());
                    RoomDatabase_DA.getInstance(this).userDAO().update(user1);
                    Toast.makeText(this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", user1.getUsername());
//                    binding.tilPasswordold.getEditText().setText("");
//                    binding.tilPasswordrpeat.getEditText().setText("");
//                    binding.tilPasswordnew.getEditText().setText("");
                }

            });

    }
    public int Validate(){
        int check=1;
        int temp=0;
        if (binding.edPasswordold.length()==0) {
            binding.edPasswordold.setError("Không được để trống");
            check=-1;
            temp++;
        } else {
//            binding.edPasswordold.setError("");
        }

        if (binding.edPasswordnew.length()==0) {
            binding.edPasswordnew.setError("Không được để trống");
            check=-1;
            temp++;
        } else {
//            binding.tilPasswordnew.setError("");
        }
        if (binding.edPasswordnewrpeat.length()==0) {
            binding.edPasswordnewrpeat.setError("Không được để trống");
            check=-1;
            temp++;
        } else {
//            binding.tilPasswordrpeat.setError("");
        }

        if (temp==0){
            SharedPreferences preferences=getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String mkc=preferences.getString("PASSWORD","");
            String mkm=binding.edPasswordnew.getText().toString();
            String nlmk=binding.edPasswordnewrpeat.getText().toString();
            if (!mkc.equalsIgnoreCase(binding.edPasswordold.getText().toString())) {
                binding.edPasswordold.setError("Mật Khẩu Cũ Sai");
                check=-1;
            }else
            if (!mkm.equals(nlmk)) {
                Toast.makeText(this, "Mật Khẩu Không Trùng Khớp", Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }else {
            temp=0;
        }
        return check;
    }
}
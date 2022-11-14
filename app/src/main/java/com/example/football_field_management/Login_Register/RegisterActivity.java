package com.example.football_field_management.Login_Register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.R;
import com.example.football_field_management.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    RoomDatabase_DA db;
    int temp=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_register);
        db=RoomDatabase_DA.getInstance(this);
        binding.btnRegister.setOnClickListener(view -> {
           validate();
           if (temp==0) {
               UserEntity user= new UserEntity();
               user.setUsername(binding.tilUsernamedk.getEditText().getText().toString());
               user.setFullname(binding.tilFullname.getEditText().getText().toString());
               user.setPhonenumber(binding.tilPhonenumber.getEditText().getText().toString());
               user.setPassword(binding.tilPassworddk.getEditText().getText().toString());
               db.userDAO().insert(user);
               Toast.makeText(this, "Đăng Kí Thành Công", Toast.LENGTH_SHORT).show();
           }else {
               temp=0;
           }
        });

        binding.tvLogin.setOnClickListener(view -> {
            onBackPressed();
        });
    }
    public void validate() {
        if (binding.tilUsernamedk.getEditText().length()==0) {
            binding.tilUsernamedk.setError("Vui lòng không để trống username");
            temp++;
        }else {
            binding.tilUsernamedk.setError("");
        }

        if (binding.tilFullname.getEditText().length()==0) {
            binding.tilFullname.setError("Vui lòng không để trống Họ Tên");
            temp++;
        }else {
            binding.tilFullname.setError("");
        }

        if (binding.tilPhonenumber.getEditText().length()==0) {
            binding.tilPhonenumber.setError("Vui lòng không để trống Số điện thoại");
            temp++;
        }else {
            binding.tilPhonenumber.setError("");
        }
        if (binding.tilPassworddk.getEditText().length()==0) {
            binding.tilPassworddk.setError("Vui lòng không để trống Mật Khẩu");
            temp++;
        }else

        if (binding.tilPassworddk.getEditText().length()<6) {
            binding.tilPassworddk.setError("Mật khẩu phải lớn hơn 6 kí tự");
            temp++;
        }else
        {
            binding.tilPassworddk.setError("");
        }
    }
}
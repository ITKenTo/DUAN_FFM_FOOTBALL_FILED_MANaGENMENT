package com.example.football_field_management.Login_Register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.HomeActivity;
import com.example.football_field_management.HomeAdminActivity;
import com.example.football_field_management.HomeYardOwnerActivity;
import com.example.football_field_management.R;
import com.example.football_field_management.databinding.ActivityLoginBinding;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    RoomDatabase_DA db;
    static LoginActivity INSTANCE;
    String data="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        INSTANCE=this;
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //Ẩn tên ứng dụng
//        getSupportActionBar().hide(); // Ẩn luôn thanh tiêu đề
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //bật chế độ toàn màn hình

        binding= DataBindingUtil.setContentView(this,R.layout.activity_login);

        db=RoomDatabase_DA.getInstance(this);
//        db.userDAO().insert(new UserEntity("Ph26746","Nguyễn Văn Dũng","0392289601","abc"));
        SharedPreferences preferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        binding.edUsername.setText(preferences.getString("USERNAME",""));
        binding.edPassword.setText(preferences.getString("PASSWORD",""));
        binding.ckbSave.setChecked(preferences.getBoolean("REMEMBER",false));

        binding.btnLogin.setOnClickListener(view -> {
            String username = binding.edUsername.getText().toString();
            String password = binding.edPassword.getText().toString();
            data=username;
//            binding.edUsername.setError("");
//            binding.edPassword.setError("");
            if(username.isEmpty()){
                binding.edUsername.setError("Vui lòng nhập tên đăng nhập");
                return;
            }
            if(password.isEmpty()){
                binding.edPassword.setError("Vui lòng nhập mật khẩu");
                return;
            }


            UserEntity user= db.userDAO().getUser(username,password);
                if (user != null) {
                    rememberUser(username,password, binding.ckbSave.isChecked());
                    if (user.getDecentralization().equals("ADMIN")) {
                        Intent intent2 = new Intent(LoginActivity.this, HomeAdminActivity.class);
                        intent2.putExtra("user", user);
                        startActivity(intent2);
                    }else
                    if (user.getDecentralization().equals("Chủ sân")) {
                        Intent intent1 = new Intent(LoginActivity.this, HomeYardOwnerActivity.class);
                        intent1.putExtra("user", user);
                        startActivity(intent1);
                    }else
                    {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                    }
                } else {
                    binding.edUsername.setError("Tên đăng nhập hoặc mật khẩu không chính xác");
                    binding.edPassword.setError("Tên đăng nhập hoặc mật khẩu không chính xác");
                }
        });

      binding.tvDk.setOnClickListener(view -> {
          Intent intent= new Intent(getApplication(),RegisterActivity.class);
          startActivity(intent);
      });
    }

    private void rememberUser(String strUser, String strPass, boolean checked) {
        SharedPreferences preferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        if(checked){
            edit.putString("USERNAME",strUser);
            edit.putString("PASSWORD",strPass);
            edit.putBoolean("REMEMBER",true);
        }else{
            edit.clear();
        }
        edit.commit();
    }

    public static LoginActivity getActivityInstance()
    {
        return INSTANCE;
    }

    public String getData()
    {
        return this.data;
    }
}
package com.example.football_field_management.Login_Register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.R;
import com.example.football_field_management.databinding.ActivityRegisterBinding;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    com.example.football_field_management.databinding.ActivityRegisterBinding binding;
    RoomDatabase_DA db;
    Spinner spinner;
    ArrayList<String> arrND;
    int temp=0;
    String decentralization;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_register);
        spinner = findViewById(R.id.spinner1);
        arrND = new ArrayList<String>();
        arrND.add("Chủ sân");
        arrND.add("Khách hàng");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrND);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RegisterActivity.this, arrND.get(i)+"", Toast.LENGTH_SHORT).show();
                Log.e("SPN",arrND.get(i) );
                decentralization=arrND.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        db=RoomDatabase_DA.getInstance(this);

        binding.btnRegister.setOnClickListener(view -> {
           validate();
           if (temp==0) {
               UserEntity user= new UserEntity();
               user.setUsername(binding.edUsername.getText().toString());
               user.setFullname(binding.edFullname.getText().toString());
               user.setPhonenumber(binding.edPhonenumber.getText().toString());
               user.setDecentralization(decentralization);
               user.setPassword(binding.edPassword.getText().toString());
               db.userDAO().insert(user);
               Toast.makeText(getApplication(), "Đăng kí thành công", Toast.LENGTH_SHORT).show();
           }else {
               temp=0;
           }
        });

        binding.tvDn.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.backHomelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void validate() {
        if (binding.edUsername.length()==0) {
            binding.edUsername.setError("Vui lòng không để trống username");
            temp++;
        }else {
        }

        if (binding.edFullname.length()==0) {
            binding.edFullname.setError("Vui lòng không để trống Họ Tên");
            temp++;
        }else {

        }

        if (binding.edPhonenumber.length()==0) {
            binding.edPhonenumber.setError("Vui lòng không để trống Số điện thoại");
            temp++;
        }else {

        }
        if (binding.edPassword.length()==0) {
            binding.edPassword.setError("Vui lòng không để trống Mật Khẩu");
            temp++;
        }else
        if (binding.edComfirmpassword.length()==0) {
            binding.edComfirmpassword.setError("Vui lòng không để trống nhập lại Mật Khẩu");
            temp++;
        }else
        if (binding.edPassword.length()<6) {
            binding.edPassword.setError("Mật khẩu phải lớn hơn 6 kí tự");
            temp++;
        }else
        {
        }
    }
}
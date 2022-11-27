package com.example.football_field_management.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.football_field_management.ChangePasswordActivity;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.HomeClientActivity;
import com.example.football_field_management.Login_Register.LoginActivity;
import com.example.football_field_management.Login_Register.RegisterActivity;
import com.example.football_field_management.R;
import com.example.football_field_management.databinding.FragmentSupportYardownerBinding;

public class SupportYardownerFragment extends Fragment {

    FragmentSupportYardownerBinding binding;
    String username;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_support_yardowner,container,false);
        username= LoginActivity.getActivityInstance().getData();

        UserEntity user1= RoomDatabase_DA.getInstance(getActivity()).userDAO().getIdUser(username);

        binding.tvNameyard.setText(user1.getFullname());
        binding.linnerLogoutyard.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });

        binding.linnerDoimkyard.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
        });
        binding.linnerDatsanyard.setOnClickListener(view -> {

            startActivity(new Intent(getActivity(), RegisterActivity.class));
        });

        return binding.getRoot();
    }
}
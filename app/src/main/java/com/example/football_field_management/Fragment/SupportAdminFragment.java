package com.example.football_field_management.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.football_field_management.ChangePasswordActivity;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.Login_Register.LoginActivity;
import com.example.football_field_management.R;
import com.example.football_field_management.databinding.FragmentSupportAdminBinding;
import com.example.football_field_management.databinding.FragmentSupportBinding;


public class SupportAdminFragment extends Fragment {
    RoomDatabase_DA db;
    String username;
    FragmentSupportAdminBinding binding;
    String decentralization="Chủ sân";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_support_admin,container,false);
        FragmentActivity activity = requireActivity();
        username= LoginActivity.getActivityInstance().getData();

        db=RoomDatabase_DA.getInstance(getActivity());
        UserEntity user1= RoomDatabase_DA.getInstance(getActivity()).userDAO().getIdUser(username);
        decentralization=LoginActivity.getActivityInstance().getData();
        binding.tvNameAdmin.setText(user1.getFullname());
        binding.linnerLogoutAdmin.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });

        binding.linnerDoimkAdmin.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
        });
//        double doanhthu=db.order_pitchDao().doanhthu(username);
//        binding.tvPricepitch.setText(doanhthu+" VNĐ");


//        int count= db.order_pitchDao().count(username);
//        binding.tvCountpitch.setText(count+" Sân");
        int count1= db.userDAO().count("Chủ sân");
        binding.tvThenumberofyardowneraccounts.setText(count1+" tài khoản");
        return binding.getRoot();
    }
}
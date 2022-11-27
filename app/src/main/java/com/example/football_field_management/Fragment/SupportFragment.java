package com.example.football_field_management.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.football_field_management.ChangePasswordActivity;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.Login_Register.LoginActivity;
import com.example.football_field_management.R;
import com.example.football_field_management.databinding.FragmentSupportBinding;

import java.util.Locale;

public class SupportFragment extends Fragment {
    RoomDatabase_DA db;
    String username;
    FragmentSupportBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_support,container,false);
        FragmentActivity activity = requireActivity();
        username=LoginActivity.getActivityInstance().getData();

        db=RoomDatabase_DA.getInstance(getActivity());
        UserEntity user1= RoomDatabase_DA.getInstance(getActivity()).userDAO().getIdUser(username);

        binding.tvName.setText(user1.getFullname());
        binding.linnerLogout.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });

        binding.linnerDoimk.setOnClickListener(view1 -> {
             startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
        });
        double doanhthu=db.order_pitchDao().doanhthu(username);
        binding.tvPricepitch.setText( String.format(Locale.US, "%.0f", doanhthu)+" VNĐ");
        //String.format(Locale.US, "%.0f", doanhthu);

        int count= db.order_pitchDao().count(username);
        binding.tvCountpitch.setText(count+" Sân");
        return binding.getRoot();
    }
}
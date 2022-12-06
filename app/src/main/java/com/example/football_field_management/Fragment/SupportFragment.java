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
import android.widget.TextView;

import com.example.football_field_management.ChangePasswordActivity;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.Information_activity;
import com.example.football_field_management.Login_Register.LoginActivity;
import com.example.football_field_management.R;
import com.example.football_field_management.databinding.FragmentSupportBinding;

import java.text.DecimalFormat;
import java.util.Locale;

public class SupportFragment extends Fragment {
    RoomDatabase_DA db;
    String username;
    FragmentSupportBinding binding;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    TextView tv_name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_support,container,false);
        FragmentActivity activity = requireActivity();
        username=LoginActivity.getActivityInstance().getData();

        db=RoomDatabase_DA.getInstance(getActivity());
        UserEntity user1= RoomDatabase_DA.getInstance(getActivity()).userDAO().getIdUser(username);

        binding.tvName.setText(user1.getFullname());
        binding.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Information_activity.class));
            }
        });
        binding.linnerLogout.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });

        binding.linnerDoimk.setOnClickListener(view1 -> {
             startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
        });
        double doanhthu=db.order_pitchDao().doanhthu(username,"ĐT");
        binding.tvPricepitch.setText( formatter.format(doanhthu)+" VNĐ");
        //String.format(Locale.US, "%.0f", doanhthu);

        int count= db.order_pitchDao().count(username,"ĐT");
        binding.tvCountpitch.setText(count+" Sân");
        return binding.getRoot();
    }
}
package com.example.football_field_management.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.Entity.UserViewModel;
import com.example.football_field_management.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeYardOwnerFragment extends Fragment {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c = Calendar.getInstance();
    TextView tv_date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_admin, container, false);
        tv_date=view.findViewById(R.id.tv_date_yard);

        tv_date.setText("Date : "+sdf.format(c.getTime()));
        return view;

    }
}
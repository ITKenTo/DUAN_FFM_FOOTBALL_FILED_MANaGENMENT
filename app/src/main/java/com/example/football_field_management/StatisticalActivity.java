package com.example.football_field_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.databinding.ActivityStatisticalBinding;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class StatisticalActivity extends AppCompatActivity {

    ActivityStatisticalBinding binding;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar lich= Calendar.getInstance();
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    int temp=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_statistical);

        binding.backHomestatifical.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.edDatedau.setOnClickListener(view -> {
            datefirst(view);
        });

        binding.edDatecuoi.setOnClickListener(view -> {
            datelast(view);
        });
        binding.btnStatis.setOnClickListener(view -> {
            String tuNgay = binding.edDatedau.getText().toString();
            String denNgay = binding.edDatecuoi.getText().toString();
            if (tuNgay.isEmpty()||denNgay.isEmpty()){
                Toast.makeText(this, "Không được để trống", Toast.LENGTH_SHORT).show();
                temp++;
            }else {
                String[] temptungay = tuNgay.split("-");
                String[] tempdenngay = denNgay.split("-");

                String newTungay = "";
                String newdenngay = "";

                int inttungay = Integer.parseInt(newTungay.concat(temptungay[0]).concat(temptungay[1]).concat(temptungay[2]));
                int intdenngay = Integer.parseInt(newdenngay.concat(tempdenngay[0]).concat(tempdenngay[1]).concat(tempdenngay[2]));

                if (inttungay > intdenngay) {
                    Toast.makeText(this, "Lỗi, từ ngày phải bé hơn đến ngày", Toast.LENGTH_SHORT).show();
                    temp++;
                }
            }

            if (temp==0){
                binding.statistialDoanhthu.setText("Doanh thu: "+formatter.format(RoomDatabase_DA.getInstance(this).order_pitchDao().datedoanhthu(tuNgay,denNgay))+" VNĐ");
            }else {
                temp=0;
            }
        });


    }

    public void datefirst(View view) {
        int nam  = lich.get(Calendar.YEAR);
        int thang  = lich.get(Calendar.MONTH);
        int ngay  = lich.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datepick= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                GregorianCalendar c= new GregorianCalendar(year,month,dayOfMonth);
                binding.edDatedau.setText(sdf.format(c.getTime()));
            }
        },nam,thang,ngay );
        datepick.show();
    }

    public void datelast(View view) {
        int nam  = lich.get(Calendar.YEAR);
        int thang  = lich.get(Calendar.MONTH);
        int ngay  = lich.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datepick= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                GregorianCalendar c= new GregorianCalendar(year,month,dayOfMonth);
                binding.edDatecuoi.setText(sdf.format(c.getTime()));
            }
        },nam,thang,ngay );
        datepick.show();
    }


}
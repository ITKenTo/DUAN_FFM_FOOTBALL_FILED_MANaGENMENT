package com.example.football_field_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.football_field_management.Adapter.OderAdpter;
import com.example.football_field_management.Entity.Oder;
import com.example.football_field_management.Login_Register.LoginActivity;
import com.example.football_field_management.databinding.ActivityOderPitchBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class OderPitchActivity extends AppCompatActivity {

    List<Oder> list;
    OderAdpter adpter;
    Calendar lich=Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    String pitchname,datetime;
    double price;
    static OderPitchActivity INSTANCE;

    ActivityOderPitchBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        INSTANCE=this;
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_oder_pitch);
        Intent intent=getIntent();
        pitchname=intent.getStringExtra("pitchname");
        price=intent.getDoubleExtra("price",0);

      //  PitchEntity pitch= RoomDatabase_DA.getInstance(this).pitchDao().pitchID(pitchname);
       // list= RoomDatabase_DA.getInstance(this).order_pitchDao().getselectpitch(pitch.getId_pitch());

        adpter=new OderAdpter(getList(),this);
        GridLayoutManager layoutManager= new GridLayoutManager(this,2);
        binding.recyoder.setLayoutManager(layoutManager);
        binding.recyoder.setAdapter(adpter);
        binding.tvDate.setText( sdf.format(lich.getTime()));

        binding.tvPitchname.setText("Tên Sân: "+pitchname);


//        binding.imgDate.setOnClickListener(view -> {
//            date(view);
//
//        });

        binding.backHomeclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        datetime=binding.tvDate.getText().toString();
        Log.e("DATE", binding.tvDate.getText().toString() );
    }

    public void date(View view) {
        int nam  = lich.get(Calendar.YEAR);
        int thang  = lich.get(Calendar.MONTH);
        int ngay  = lich.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datepick= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                GregorianCalendar c= new GregorianCalendar(year,month,dayOfMonth);
                binding.tvDate.setText(sdf.format(c.getTime()));
                Toast.makeText(OderPitchActivity.this, sdf.format(c.getTime()), Toast.LENGTH_SHORT).show();
            }
        },nam,thang,ngay );
        datepick.show();
    }

    private List<Oder> getList(){
        list= new ArrayList<>();
        list.add(new Oder(1,"Ca 1: 7h-9h",pitchname,price));
        list.add(new Oder(2,"Ca 2: 9h-11h",pitchname,price));
        list.add(new Oder(3,"Ca 3: 11h-13h",pitchname,price));
        list.add(new Oder(4,"Ca 4: 13h-15h",pitchname,price));
        list.add(new Oder(5,"Ca 5: 15h-17h",pitchname,price));
        list.add(new Oder(6,"Ca 6: 17h-19h",pitchname,price));
        list.add(new Oder(6,"Ca 7: 19h-21h",pitchname,price));
        return list;
    }


    public static OderPitchActivity getActivityInstance()
    {
        return INSTANCE;
    }

    public String getData()
    {
        return this.datetime;
    }
}
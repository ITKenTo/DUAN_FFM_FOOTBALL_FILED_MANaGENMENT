package com.example.football_field_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.football_field_management.Adapter.OderAdpter;
import com.example.football_field_management.DAO.IClickitem;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.Oder;
import com.example.football_field_management.Entity.Order_PitchEntity;
import com.example.football_field_management.Entity.PitchEntity;
import com.example.football_field_management.Login_Register.LoginActivity;
import com.example.football_field_management.databinding.ActivityOderPitchBinding;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class OderPitchActivity extends AppCompatActivity implements IClickitem {

    List<Oder> list;
    OderAdpter adpter;
    Calendar lich=Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    TextView tv_date,tv_name,tv_time,tv_price;
    int currentTime,firsttime,lasttime;

    String pitchname,datetime,datecurrent;
    double price;
    static OderPitchActivity INSTANCE;
    Oder item;

    PitchEntity pitch;

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

        adpter=new OderAdpter(getList(),getApplicationContext(),this);
        GridLayoutManager layoutManager= new GridLayoutManager(this,2);
        binding.recyoder.setLayoutManager(layoutManager);
        binding.recyoder.setAdapter(adpter);
        binding.tvDate.setText( sdf.format(lich.getTime()));
        binding.tvPitchname.setText("Tên Sân: "+pitchname);

        datecurrent=sdf.format(lich.getTime());

        binding.imgDate.setOnClickListener(view -> {
            date(view);

        });

        binding.backHomeclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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
        list.add(new Oder(1,"07h","09h",pitchname,price));
        list.add(new Oder(2,"09h","11h",pitchname,price));
        list.add(new Oder(3,"11h","13h",pitchname,price));
        list.add(new Oder(4,"13h","15h",pitchname,price));
        list.add(new Oder(5,"15h","17h",pitchname,price));
        list.add(new Oder(6,"17h","19h",pitchname,price));
        list.add(new Oder(7,"19h","21h",pitchname,price));
        list.add(new Oder(8,"21h","23h",pitchname,price));
        return list;
    }

    @Override
    public void onclickitem(int position) {
        item=getList().get(position);
//        list.remove(position);
//        adpter.notifyItemRemoved(position);
        PitchEntity pitch1= RoomDatabase_DA.getInstance(this).pitchDao().pitchID(pitchname);
        Order_PitchEntity order_pitch1=  RoomDatabase_DA.getInstance(this).order_pitchDao().CheckCa(item.getStart_time(), item.getEnd_time(), pitch1.getId_pitch(),binding.tvDate.getText().toString(),"ĐT");
        if (order_pitch1!=null) {
            item.setIscheck(false);
        //    Snackbar.make(binding.snaskballorder,"Ca đã được đặt",Snackbar.LENGTH_LONG).show();
            getSb(binding.snaskballorder).show();
        }else {
            item.setIscheck(true);
            dialogRegister(item, Gravity.BOTTOM);
        }

    }


    public void dialogRegister(Oder oder,int gravity){
        final Dialog view = new Dialog(this);
        view.requestWindowFeature(Window.FEATURE_NO_TITLE);
        view.setContentView(R.layout.dialog_datsan_khachhang);

        Window window = view.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        tv_name=view.findViewById(R.id.tv_pitchname_dialog);
        tv_date= view.findViewById(R.id.tv_date_dialog);
        tv_time=view.findViewById(R.id.tv_time_dialog);
        tv_price=view.findViewById(R.id.tv_price_dialog);
        pitch= RoomDatabase_DA.getInstance(this).pitchDao().pitchID(oder.getPitch_name());

        ///
        currentTime = Integer.parseInt(new SimpleDateFormat("HH", Locale.getDefault()).format(new Date()));
        firsttime= Integer.parseInt(oder.getStart_time().substring(0,2));
        ///
        tv_price.setText(formatter.format(oder.getPrice())+" VNĐ");
        tv_name.setText(oder.getPitch_name());
        tv_time.setText(oder.getStart_time()+"-"+oder.getEnd_time());
        tv_date.setText(binding.tvDate.getText().toString());
        Log.e("date",tv_date.getText().toString() );
        view.findViewById(R.id.btn_rent_dialog_datsan).setOnClickListener(view1 -> {
            if (currentTime>=firsttime && binding.tvDate.getText().toString().equalsIgnoreCase(datecurrent)) {
                Toast.makeText(INSTANCE, "Đã quá giờ đặt sân", Toast.LENGTH_SHORT).show();
            }else {
                InsertOrder(oder);
                view.dismiss();
            }
        });

        view.findViewById(R.id.btn_cancel_dialog_datsan).setOnClickListener(view1 -> {
            view.cancel();
        });


        view.show();
    }

    public void InsertOrder(Oder oder){
        Order_PitchEntity order_pitch= new Order_PitchEntity();

        order_pitch.setPitch_name(tv_name.getText().toString());
        order_pitch.setOrder_time(tv_date.getText().toString());
        order_pitch.setStart_time(item.getStart_time());
        order_pitch.setEnd_time(item.getEnd_time());
        order_pitch.setId_pitch(pitch.getId_pitch());
        order_pitch.setTotal(price);
        order_pitch.setUsername(LoginActivity.getActivityInstance().getData());
        order_pitch.setStatus("ĐT");


        Order_PitchEntity order_pitch1=  RoomDatabase_DA.getInstance(this).order_pitchDao().CheckCa(item.getStart_time(), item.getEnd_time(), pitch.getId_pitch(),binding.tvDate.getText().toString(),"ĐT");

        if (order_pitch1 == null) {
            oder.setIscheck(true);
            RoomDatabase_DA.getInstance(this).order_pitchDao().insert(order_pitch);
            Toast.makeText(INSTANCE, "Đặt Thành Công", Toast.LENGTH_SHORT).show();

        }else {
            oder.setIscheck(false);
            Toast.makeText(INSTANCE, "Sân đã được đặt ", Toast.LENGTH_SHORT).show();

        }

    }

    private Snackbar getSb(View view){
        return Snackbar.make(view,"Ca đã được đặt",Snackbar.LENGTH_LONG)
                .setAction("Exit", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).setActionTextColor(Color.RED);
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
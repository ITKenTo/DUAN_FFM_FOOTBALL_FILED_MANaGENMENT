package com.example.football_field_management.Adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.Oder;
import com.example.football_field_management.Entity.Order_PitchEntity;
import com.example.football_field_management.Entity.PitchEntity;
import com.example.football_field_management.OderPitchActivity;
import com.example.football_field_management.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class OderAdpter extends RecyclerView.Adapter<OderAdpter.viewholder>{

    List<Oder> list;
    Context context;
    String date="";
    Calendar lich= Calendar.getInstance();
    TextView tv_date;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public OderAdpter(List<Oder> list, Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_datsan,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Oder oder= list.get(position);
        date=OderPitchActivity.getActivityInstance().getData();

        holder.tv_yard_song.setText(oder.getYard_song());
       // holder.tv_price.setText(oder.getTotal()+"");
        holder.itemView.setOnClickListener(view -> {
             dialogRegister(oder);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView tv_yard_song,tv_price;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            tv_yard_song=itemView.findViewById(R.id.tv_YardSong);
            tv_price=itemView.findViewById(R.id.tv_price);
        }
    }

    public void dialogRegister(Oder oder){
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        View view=LayoutInflater.from(context).inflate(R.layout.dialog_datsan_khachhang,null);
        builder.setView(view);
        TextView tv_name=view.findViewById(R.id.tv_pitchname_dialog);
        tv_date= view.findViewById(R.id.tv_date_dialog);
        TextView tv_time=view.findViewById(R.id.tv_time_dialog);
        TextView tv_price=view.findViewById(R.id.tv_price_dialog);
        ImageView imageView= view.findViewById(R.id.dialog_img_date);
        imageView.setOnClickListener(view1 -> {
            date(view);
        });
        PitchEntity pitch= RoomDatabase_DA.getInstance(context).pitchDao().pitchID(oder.getPitch_name());
        tv_price.setText(oder.getPrice()+"");
        tv_name.setText(oder.getPitch_name());
        tv_time.setText(oder.getYard_song());
        tv_date.setText(date);

        view.findViewById(R.id.btn_rent_dialog_datsan).setOnClickListener(view1 -> {

        });


        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }

    public void date(View view) {
        int nam  = lich.get(Calendar.YEAR);
        int thang  = lich.get(Calendar.MONTH);
        int ngay  = lich.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datepick= new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                GregorianCalendar c= new GregorianCalendar(year,month,dayOfMonth);
                tv_date.setText(sdf.format(c.getTime()));

            }
        },nam,thang,ngay );
        datepick.show();
    }
}

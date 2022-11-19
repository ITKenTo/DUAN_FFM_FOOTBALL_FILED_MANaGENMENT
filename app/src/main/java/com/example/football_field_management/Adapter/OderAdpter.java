package com.example.football_field_management.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Trace;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_field_management.DAO.IClickitem;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.Oder;
import com.example.football_field_management.Entity.Order_PitchEntity;
import com.example.football_field_management.Entity.PitchEntity;
import com.example.football_field_management.Login_Register.LoginActivity;
import com.example.football_field_management.OderPitchActivity;
import com.example.football_field_management.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class OderAdpter extends RecyclerView.Adapter<OderAdpter.viewholder>{

    List<Oder> list;
    Context context;
    private final IClickitem iClickitem;

    String date="";
    Calendar lich= Calendar.getInstance();
    TextView tv_date,tv_name,tv_time,tv_price;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public OderAdpter(List<Oder> list, Context context,IClickitem iClickitem) {
        this.list = list;
        this.context=context;
        this.iClickitem=iClickitem;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_datsan,parent,false);
        return new viewholder(view,iClickitem);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Oder oder= list.get(position);
        date=OderPitchActivity.getActivityInstance().getData();

        holder.tv_yard_song.setText(oder.getStart_time()+"-"+oder.getEnd_time());
       // holder.tv_price.setText(oder.getTotal()+"");
//        holder.itemView.setOnClickListener(view -> {
//             dialogRegister(oder);
//        });

//        Order_PitchEntity order_pitch1=  RoomDatabase_DA.getInstance(context).order_pitchDao().CheckCa(oder.getStart_time(), oder.getEnd_time(), oder.getPitch_name(),date);
//        if (order_pitch1==null) {
//            holder.itemView.setEnabled(true);
//        }else {
//            holder.itemView.setEnabled(false);
//            holder.itemView.setBackgroundColor(R.color.purple_700);
//        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView tv_yard_song,tv_price;
        CardView item;
        public viewholder(@NonNull View itemView, IClickitem iClickitem) {
            super(itemView);
            tv_yard_song=itemView.findViewById(R.id.tv_YardSong);
            tv_price=itemView.findViewById(R.id.tv_price);
            item=itemView.findViewById(R.id.card);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (iClickitem!= null) {
                        int pos= getAdapterPosition();
                        if (pos!=RecyclerView.NO_POSITION) {
                            iClickitem.onclickitem(pos);
                        }
                    }
                }
            });
        }
    }

//    public void dialogRegister(Oder oder){
//        AlertDialog.Builder builder= new AlertDialog.Builder(context);
//        View view=LayoutInflater.from(context).inflate(R.layout.dialog_datsan_khachhang,null);
//        builder.setView(view);
//         tv_name=view.findViewById(R.id.tv_pitchname_dialog);
//         tv_date= view.findViewById(R.id.tv_date_dialog);
//         tv_time=view.findViewById(R.id.tv_time_dialog);
//         tv_price=view.findViewById(R.id.tv_price_dialog);
//        ImageView imageView= view.findViewById(R.id.dialog_img_date);
////        imageView.setOnClickListener(view1 -> {
////            date(view);
////        });
//        PitchEntity pitch= RoomDatabase_DA.getInstance(context).pitchDao().pitchID(oder.getPitch_name());
//        tv_price.setText(oder.getPrice()+"");
//        tv_name.setText(oder.getPitch_name());
//        tv_time.setText(oder.getYard_song());
//        tv_date.setText(date);
//
//        view.findViewById(R.id.btn_rent_dialog_datsan).setOnClickListener(view1 -> {
//              InsertOrder();
//        });
//
//
//        AlertDialog alertDialog= builder.create();
//        alertDialog.show();
//    }
//
//    public void InsertOrder(){
//        PitchEntity pitch= RoomDatabase_DA.getInstance(context).pitchDao().pitchID(tv_name.getText().toString());
//
//         Order_PitchEntity order_pitch= new Order_PitchEntity();
//         order_pitch.setPitch_name(tv_name.getText().toString());
//         order_pitch.setOrder_time(tv_date.getText().toString());
//         order_pitch.setStart_time(tv_time.getText().toString());
//         order_pitch.setEnd_time(tv_time.getText().toString());
//         order_pitch.setId_pitch(pitch.getId_pitch());
//         order_pitch.setTotal(Double.parseDouble(tv_price.getText().toString()));
//         order_pitch.setUsername(LoginActivity.getActivityInstance().getData());
//
//         RoomDatabase_DA.getInstance(context).order_pitchDao().insert(order_pitch);
//
//
//    }
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

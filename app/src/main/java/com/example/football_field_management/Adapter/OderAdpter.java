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

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Oder oder= list.get(position);
        date=OderPitchActivity.getActivityInstance().getData();

        holder.tv_yard_song.setText(oder.getStart_time()+"-"+oder.getEnd_time());
        holder.tv_price.setText(oder.getPrice()+" VNĐ");

        if (oder.isIscheck()) {
           holder.itemView.setEnabled(false);
//           holder.itemView.setVisibility(View.VISIBLE);

        }else {
            holder.itemView.setEnabled(true);
//            holder.itemView.setVisibility(View.GONE);
        }

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
        TextView tv_yard_song,tv_price,tv_status;
        CardView item;
        public viewholder(@NonNull View itemView, IClickitem iClickitem) {
            super(itemView);
            tv_yard_song=itemView.findViewById(R.id.tv_YardSong);
            tv_price=itemView.findViewById(R.id.tv_price);
            tv_status = itemView.findViewById(R.id.tv_status);
            item=itemView.findViewById(R.id.cardpitch);
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

}

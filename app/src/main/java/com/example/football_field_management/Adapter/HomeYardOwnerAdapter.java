package com.example.football_field_management.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.Order_PitchEntity;
import com.example.football_field_management.Entity.PitchEntity;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.R;

import java.util.List;

public class HomeYardOwnerAdapter extends RecyclerView.Adapter<HomeYardOwnerAdapter.ViewHolder>{

    List<Order_PitchEntity> list;
    Context context;
    TextView tv_date,tv_name,tv_time,tv_price,tv_phone,tv_pitchname;
    PitchEntity pitch;
    public HomeYardOwnerAdapter(List<Order_PitchEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeYardOwnerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_yard,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeYardOwnerAdapter.ViewHolder holder, int position) {
        Order_PitchEntity oder = list.get(position);
        holder.tv_casan.setText(oder.getStart_time()+" - " +oder.getEnd_time());
        holder.tv_giathue.setText(oder.getTotal()+" VNĐ");
        holder.tv_NgayDat.setText(oder.getOrder_time());
        holder.itemView.setOnClickListener(view -> {
            dialogInformation(context,oder);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_casan,tv_giathue,tv_NgayDat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_casan = itemView.findViewById(R.id.tv_casan);
            tv_giathue = itemView.findViewById(R.id.tv_giathue);
            tv_NgayDat = itemView.findViewById(R.id.tv_NgayDat);
        }
    }

    public void dialogInformation(Context context, Order_PitchEntity order_pitch){
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.dialog_order_information,null);
        builder.setView(view);
        tv_name=view.findViewById(R.id.tv_nameKH);
        tv_phone=view.findViewById(R.id.tv_phonumber);
        tv_pitchname=view.findViewById(R.id.tv_pitchname_information);
        tv_time=view.findViewById(R.id.tv_yardsong_information);
        tv_date=view.findViewById(R.id.tv_date_information);
        tv_price=view.findViewById(R.id.tv_Total_information);
        pitch= RoomDatabase_DA.getInstance(context).pitchDao().pitchID1(order_pitch.getId_pitch());
        UserEntity user= RoomDatabase_DA.getInstance(context).userDAO().getIdUser(order_pitch.getUsername());
        ///gan du lieu

        tv_name.setText("Fullname: "+user.getFullname());
        tv_phone.setText("Phone: "+user.getPhonenumber());
        tv_pitchname.setText("Pitch name: "+pitch.getPitch_name());
        tv_time.setText("Time: "+order_pitch.getStart_time()+" - "+order_pitch.getEnd_time());
        tv_date.setText("Order date: "+order_pitch.getOrder_time());
        tv_price.setText("Total: "+order_pitch.getTotal()+"VND");

        AlertDialog dialog= builder.create();
        ImageView imageView=view.findViewById(R.id.exit_dialog);
        imageView.setOnClickListener(view1 -> {
            dialog.cancel();
        });



        dialog.show();

    }

}

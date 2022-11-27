package com.example.football_field_management.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_field_management.DAO.IClickitem;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.Oder;
import com.example.football_field_management.Entity.Order_PitchEntity;
import com.example.football_field_management.Entity.PitchEntity;
import com.example.football_field_management.R;

import java.util.List;

public class HomeYardOwnerAdapter extends RecyclerView.Adapter<HomeYardOwnerAdapter.ViewHolder>{

    List<Order_PitchEntity> list;
    Context context;
    TextView tv_date,tv_name,tv_time,tv_price;
    PitchEntity pitch;
    public HomeYardOwnerAdapter(List<Order_PitchEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeYardOwnerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_admin,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeYardOwnerAdapter.ViewHolder holder, int position) {
        Order_PitchEntity oder = list.get(position);
        holder.tv_casan.setText(oder.getStart_time()+" - " +oder.getEnd_time());
        holder.tv_giathue.setText(oder.getTotal()+" VNĐ");
        holder.tv_NgayDat.setText(oder.getOrder_time());

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

}

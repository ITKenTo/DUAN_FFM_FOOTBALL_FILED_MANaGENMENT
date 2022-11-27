package com.example.football_field_management.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.PitchEntity;
import com.example.football_field_management.Entity.YardTypeEntity;
import com.example.football_field_management.OderPitchActivity;
import com.example.football_field_management.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeClientAdapter extends RecyclerView.Adapter<HomeClientAdapter.viewholder> {

    List<PitchEntity> list;
    Context context;
    List<PitchEntity> listyard;
    YardTypeEntity yardTypeEntity;
    RoomDatabase_DA db;

    public void setData(List<PitchEntity> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
        this.listyard = new ArrayList<>();
        this.listyard.addAll(list);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pitch, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        PitchEntity pitch = list.get(position);
        db = RoomDatabase_DA.getInstance(context);

        holder.tv_pitch.setText("Sân: " + pitch.getPitch_name());
        yardTypeEntity = db.yardTypeDao().getselectID(pitch.getId_yardTye());

        holder.tv_pitchtype.setText("Loại sân: " + yardTypeEntity.getFiledtypename());
        //  holder.tv_pitchtype.setText("Loại sân: "+pitch.getId_yardTye());
        holder.tv_price.setText("Giá sân: " +  String.format(Locale.US, "%.0f", pitch.getPrice()) + " VNĐ");
        String.format(Locale.US, "%.0f", pitch.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,OderPitchActivity.class);
                intent.putExtra("pitchname",pitch.getPitch_name());
                intent.putExtra("price",pitch.getPrice());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView tv_pitch, tv_pitchtype, tv_price;

        public viewholder(@NonNull View itemView) {

            super(itemView);
            tv_pitch = itemView.findViewById(R.id.tv_pitch);
            tv_pitchtype = itemView.findViewById(R.id.tv_pitchtype);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0) {
            list.addAll(listyard);
        } else {
            for (PitchEntity pitch : listyard) {
                if (pitch.getPitch_name().toLowerCase(Locale.getDefault()).contains(charText)) {
                    list.add(pitch);
                }
            }
        }
        notifyDataSetChanged();
    }

}
package com.example.football_field_management.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.PitchEntity;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.Entity.YardTypeEntity;
import com.example.football_field_management.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class PitchAdapter extends RecyclerView.Adapter<PitchAdapter.viewholder>{

    List<PitchEntity> list;
    Context context;
    List<PitchEntity> listyard;

    RoomDatabase_DA db;
    public void setData(List<PitchEntity> list,Context context){
        this.list=list;
        this.context=context;
        notifyDataSetChanged();
        this.listyard=new ArrayList<>();
        this.listyard.addAll(list);
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pitch,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        PitchEntity pitch = list.get(position);
//        YardTypeEntity yardTypeEntity = listyard.get(position);
        db=RoomDatabase_DA.getInstance(context);
        holder.tv_pitch.setText("Sân: "+pitch.getPitch_name());
        holder.tv_pitchtype.setText("Loại sân: "+pitch.getId_yardTye());
        holder.tv_price.setText("Giá sân: "+pitch.getPrice());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Delete(pitch,context);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView tv_pitch,tv_pitchtype,tv_price;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            tv_pitch = itemView.findViewById(R.id.tv_pitch);
            tv_pitchtype = itemView.findViewById(R.id.tv_pitchtype);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
    public void Delete(PitchEntity pitch,Context context) {
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa sân này!");
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    RoomDatabase_DA.getInstance(context).pitchDao().delete(pitch);
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(db.pitchDao().getselect());
                    notifyDataSetChanged();
            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0){
            list.addAll(listyard);
        }else {
            for (PitchEntity pitch : listyard) {
                if (pitch.getPitch_name().toLowerCase(Locale.getDefault()).contains(charText)){
                    list.add(pitch);
                }
            }
        }
        notifyDataSetChanged();
    }
}

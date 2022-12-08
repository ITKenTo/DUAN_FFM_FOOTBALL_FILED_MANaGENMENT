package com.example.football_field_management.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.PitchEntity;
import com.example.football_field_management.Entity.Statistical;
import com.example.football_field_management.R;

import java.text.DecimalFormat;
import java.util.List;

public class StatificalAdapter extends RecyclerView.Adapter<StatificalAdapter.viewhodler>{
    List<Statistical> list;
    Context context;
    DecimalFormat formatter = new DecimalFormat("###,###,###");


    public StatificalAdapter(List<Statistical> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_statifical,null);
        return new viewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewhodler holder, int position) {
          Statistical statistical= list.get(position);
        PitchEntity pitch= RoomDatabase_DA.getInstance(context).pitchDao().pitchID1(statistical.getId_pitch());
          holder.tv_pitch.setText(pitch.getPitch_name());
          holder.tv_total.setText(formatter.format(statistical.getTotal())+"");
          holder.tv_sl.setText(statistical.getSoluong()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewhodler extends RecyclerView.ViewHolder {
        TextView tv_pitch, tv_total,tv_sl;

        public viewhodler(@NonNull View itemView) {
            super(itemView);
            tv_pitch=itemView.findViewById(R.id.tv_name_sta);
            tv_total=itemView.findViewById(R.id.tv_total_sta);
            tv_sl=itemView.findViewById(R.id.tv_soca_sta);
        }
    }
}

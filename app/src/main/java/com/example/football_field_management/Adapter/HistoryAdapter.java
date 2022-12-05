package com.example.football_field_management.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_field_management.DAO.Order_PitchDao;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.Order_PitchEntity;
import com.example.football_field_management.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.viewholder> {
    List<Order_PitchEntity> list;
    RoomDatabase_DA db;
    Context context;
    List<Order_PitchEntity> listlook;
    int currentTime,firsttime,lasttime;
    DecimalFormat formatter = new DecimalFormat("###,###,###");


    public HistoryAdapter(List<Order_PitchEntity> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
        this.listlook = new ArrayList<>();
        this.listlook.addAll(list);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_khachhangdatsan,parent,false);
       return new viewholder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
         Order_PitchEntity oder = list.get(position);
         db = RoomDatabase_DA.getInstance(context);
         holder.tv_fieldname.setText("Filde name: "+oder.getPitch_name());
         holder.tv_bookingdate.setText("Date: "+oder.getOrder_time());
         holder.tv_time.setText("Time: "+oder.getStart_time()+" - "+oder.getEnd_time());
         holder.tv_price.setText("Total: " +formatter.format(oder.getTotal())+" VNĐ");
          currentTime = Integer.parseInt(new SimpleDateFormat("HH", Locale.getDefault()).format(new Date()));
          firsttime= Integer.parseInt(oder.getStart_time().substring(0,2));
          lasttime= Integer.parseInt(oder.getEnd_time().substring(0,2));
         holder.imageView.setOnClickListener(view -> {
             Delete(oder,context);
         });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView tv_fieldname,tv_bookingdate,tv_price, tv_time;
        ImageView imageView;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            tv_fieldname =itemView.findViewById(R.id.tv_history_name);
            tv_bookingdate=itemView.findViewById(R.id.tv_history_date);
            tv_price=itemView.findViewById(R.id.tv_history_price);
            tv_time=itemView.findViewById(R.id.tv_history_time);
            imageView = itemView.findViewById(R.id.image_delete);
        }
    }

    public void Delete(Order_PitchEntity oder, Context context) {
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn có muốn hủy đặt sân");


        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (firsttime-1==currentTime) {
                    Toast.makeText(context, "Sắp đến giờ, không thể hủy", Toast.LENGTH_SHORT).show();
                }else if (firsttime<=currentTime && lasttime>=currentTime) {
                    Toast.makeText(context, "Đang trong giờ đá không thể hủy", Toast.LENGTH_SHORT).show();
                }else {
                    RoomDatabase_DA.getInstance(context).order_pitchDao().delete(oder);
                    Toast.makeText(context, "Đã hủy", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(db.order_pitchDao().getselect());
                    notifyDataSetChanged();
                }
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
        if ((charText.length() == 0)){
            list.addAll(listlook);
        }else{
            for (Order_PitchEntity order : listlook){
                if (order.getPitch_name().toLowerCase(Locale.getDefault()).contains(charText)){
                    list.add(order);
                }
            }
        }
        notifyDataSetChanged();
    }
}


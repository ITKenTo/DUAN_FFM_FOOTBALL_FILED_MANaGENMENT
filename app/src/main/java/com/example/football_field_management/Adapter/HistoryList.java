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
import androidx.room.Delete;

import com.example.football_field_management.DAO.Order_PitchDao;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.Order_PitchEntity;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.R;

import java.util.List;

public class HistoryList extends RecyclerView.Adapter<HistoryList.viewholder> {
    List<Order_PitchEntity> list;
    RoomDatabase_DA db;
    Context context;
    Order_PitchDao order_pitchDao;

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
         holder.tv_bookingdate.setText("Date"+oder.getOrder_time());
         holder.tv_price.setText("price: " +oder.getTotal());
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Delete(oder,context);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView tv_fieldname,tv_bookingdate,tv_price;
        ImageView imageView;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            tv_fieldname =itemView.findViewById(R.id.tv_fieldname_itemhistorykhachhang);
            tv_bookingdate=itemView.findViewById(R.id.tv_bookingdate_itemhistorykhachhang);
            tv_price=itemView.findViewById(R.id.tv_price_itemhistorykhachhang);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
    public void Delete(Order_PitchEntity oder, Context context) {
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn có muốn hủy đặt sân");


        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                RoomDatabase_DA.getInstance(context).order_pitchDao().delete(oder);
                Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                list.clear();
                list.addAll(db.order_pitchDao().getselect());
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
}


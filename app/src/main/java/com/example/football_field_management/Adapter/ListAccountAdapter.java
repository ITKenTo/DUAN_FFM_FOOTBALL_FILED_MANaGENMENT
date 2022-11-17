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
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListAccountAdapter extends RecyclerView.Adapter<ListAccountAdapter.viewholder>{

    List<UserEntity> list;
    List<UserEntity> listlookfor;
    Context context;

    RoomDatabase_DA db;
    public void setData(List<UserEntity> list,Context context){
        this.list=list;
        this.context=context;
        notifyDataSetChanged();
        this.listlookfor=new ArrayList<>();
        this.listlookfor.addAll(list);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_account,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        UserEntity user= list.get(position);
        db=RoomDatabase_DA.getInstance(context);
         holder.tv_username.setText("Username: "+user.getUsername());
         holder.tv_decentralization.setText("Account: "+user.getDecentralization());
         holder.tv_password.setText("Password: "+user.getPassword());
         holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
             @Override
             public boolean onLongClick(View view) {
                 Delete(user,context);
                 return false;
             }
         });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView tv_username,tv_decentralization, tv_password;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            tv_username=itemView.findViewById(R.id.tv_username);
            tv_password=itemView.findViewById(R.id.tv_password);
            tv_decentralization=itemView.findViewById(R.id.tv_decentralization);
        }
    }


    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0){
            list.addAll(listlookfor);
        }else {
            for (UserEntity ls : listlookfor) {
                if (ls.getUsername().toLowerCase(Locale.getDefault()).contains(charText)){
                    list.add(ls);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void Delete(UserEntity user,Context context) {
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa tàu khoản này?");


        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (user.getUsername().equals("admin")) {
                    Toast.makeText(context, "Không thể xóa Admin", Toast.LENGTH_SHORT).show();
                }else {
                    RoomDatabase_DA.getInstance(context).userDAO().delete(user);
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(db.userDAO().getSelectCS("Chủ sân"));
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

}

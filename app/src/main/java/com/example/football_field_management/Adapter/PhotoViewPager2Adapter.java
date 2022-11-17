package com.example.football_field_management.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_field_management.Entity.Photo;
import com.example.football_field_management.R;

import java.util.List;

public class PhotoViewPager2Adapter extends RecyclerView.Adapter<PhotoViewPager2Adapter.viewholder>{

    List<Photo> list;

    public PhotoViewPager2Adapter(List<Photo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Photo photo= list.get(position);
        holder.photo.setImageResource(photo.getId());
    }

    @Override
    public int getItemCount() {
        if (list!=null) {
            return list.size();
        }
        return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView photo;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            photo=itemView.findViewById(R.id.img_photo);
        }
    }
}

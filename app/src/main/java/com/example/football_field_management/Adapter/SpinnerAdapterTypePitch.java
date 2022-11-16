package com.example.football_field_management.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.football_field_management.Entity.YardTypeEntity;
import com.example.football_field_management.R;

import java.util.ArrayList;

public class SpinnerAdapterTypePitch extends ArrayAdapter<YardTypeEntity> {
    Context context;
    ArrayList<YardTypeEntity> list;
    TextView tvid,tvname;
    public SpinnerAdapterTypePitch(Context context,ArrayList<YardTypeEntity> list){
        super(context,0,list);
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View holder=convertView;
        if (holder==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder=inflater.inflate(R.layout.item_typepitch,null,false);
        }
        final YardTypeEntity ls=list.get(position);
        if (ls !=null){
            tvname=holder.findViewById(R.id.nametypepitch);
            tvname.setText(ls.getFiledtypename());
        }
        return holder;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View holder=convertView;
        if (holder==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder=inflater.inflate(R.layout.item_typepitch,null,false);
        }
        final YardTypeEntity ls=list.get(position);
        if (ls !=null){
            tvname=holder.findViewById(R.id.nametypepitch);
            tvname.setText(ls.getFiledtypename());
        }
        return holder;
    }
}

package com.example.football_field_management.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.football_field_management.Adapter.PitchAdapter;
import com.example.football_field_management.AddPitch;
import com.example.football_field_management.DAO.YardTypeDao;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.PitchEntity;
import com.example.football_field_management.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PitchFragment extends Fragment {

    RecyclerView rycycleView;
    List<PitchEntity> list;
    PitchAdapter adapter;
    RoomDatabase_DA db;
    YardTypeDao yardTypeDao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pitch, container, false);
        FloatingActionButton btnadd=view.findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddPitch.class));
            }
        });
        SearchView search_view_pitch = view.findViewById(R.id.search_view_pitch);
        search_view_pitch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                rycycleView.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                rycycleView.setAdapter(adapter);
                return false;
            }
        });

        db = RoomDatabase_DA.getInstance(getActivity());
        rycycleView = view.findViewById(R.id.rycycleView);
        adapter = new PitchAdapter();
        list = new ArrayList<>();
        list= db.pitchDao().getselect();
        Log.e("zzzzzzzzz",list.size()+"");
        adapter.setData(list,getActivity());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        rycycleView.setLayoutManager(gridLayoutManager);
        rycycleView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        list.addAll(db.pitchDao().getselect());
        adapter.notifyDataSetChanged();
    }
}
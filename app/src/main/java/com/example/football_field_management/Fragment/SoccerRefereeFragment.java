package com.example.football_field_management.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.football_field_management.Adapter.ListAccountAdapter;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.R;

import java.util.ArrayList;
import java.util.List;


public class SoccerRefereeFragment extends Fragment {

    ListAccountAdapter adapter;
    RecyclerView rycycleViewarbitration;
    List<UserEntity> list;
    RoomDatabase_DA db;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_soccer_referee, container, false);
        searchView = view.findViewById(R.id.search_view_arbitration);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                rycycleViewarbitration.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                rycycleViewarbitration.setAdapter(adapter);
                return false;
            }
        });

        rycycleViewarbitration = view.findViewById(R.id.rycycleViewarbitration);
        adapter = new ListAccountAdapter();
        list = new ArrayList<>();
        db = RoomDatabase_DA.getInstance(getActivity());

        list = db.userDAO().getSelectCS("KH");
        adapter.setData(list,getActivity());

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rycycleViewarbitration.setLayoutManager(manager);
        rycycleViewarbitration.setAdapter(adapter);

        return view;
    }
}
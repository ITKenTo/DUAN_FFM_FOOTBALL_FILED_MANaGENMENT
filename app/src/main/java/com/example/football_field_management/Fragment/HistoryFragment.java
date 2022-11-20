package com.example.football_field_management.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.football_field_management.Adapter.HistoryAdapter;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.Order_PitchEntity;
import com.example.football_field_management.Entity.RegisterEntity;
import com.example.football_field_management.Login_Register.LoginActivity;
import com.example.football_field_management.R;

import java.util.List;

public class HistoryFragment extends Fragment {

    RecyclerView recyclerView;
    HistoryAdapter adapter;
    List<Order_PitchEntity> list;
    String data;
    SearchView search_view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView=view.findViewById(R.id.recucler_history);
        search_view = view.findViewById(R.id.search_view);
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                recyclerView.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText );
                recyclerView.setAdapter(adapter);
                return false;
            }
        });
        data= LoginActivity.getActivityInstance().getData();
        list= RoomDatabase_DA.getInstance(getActivity()).order_pitchDao().getselectuser(data);
        adapter=new HistoryAdapter(list,getActivity());
        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
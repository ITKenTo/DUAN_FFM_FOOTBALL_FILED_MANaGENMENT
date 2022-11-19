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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.football_field_management.Adapter.PitchAdapter;
import com.example.football_field_management.AddPitch;
import com.example.football_field_management.DAO.YardTypeDao;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.PitchEntity;
import com.example.football_field_management.Entity.YardTypeEntity;
import com.example.football_field_management.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PitchFragment extends Fragment {

    RecyclerView rycycleView;
    List<PitchEntity> list;
    PitchAdapter adapter;
    RoomDatabase_DA db;
    Spinner spinner;
    String type;
    YardTypeEntity yardTypeEntity;
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

        rycycleView = view.findViewById(R.id.rycycleView);
        spinner=view.findViewById(R.id.spnloaisan);
        adapter = new PitchAdapter();
        list = new ArrayList<>();
        db = RoomDatabase_DA.getInstance(getActivity());

       // SearchView search_view_pitch = view.findViewById(R.id.search_view_pitch);

       // search_view_pitch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                adapter.filter(query);
//                rycycleView.setAdapter(adapter);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.filter(newText);
//                rycycleView.setAdapter(adapter);
//                return false;
//            }
//        });


        List<String> listpitch = db.yardTypeDao().getfiledtypename();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), R.layout.item_sp,listpitch);
        spinner.setAdapter(adapter1);

        type = listpitch.size()>0? listpitch.get(0) : "";
        Log.d("TYOE",type+"");
        yardTypeEntity=db.yardTypeDao().getfiledtypefillname(type);

        list=db.pitchDao().getselectpitch(yardTypeEntity.getId_yardTye());


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        rycycleView.setLayoutManager(gridLayoutManager);
        adapter.setData(list,getActivity());
        rycycleView.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type=listpitch.get(i);
                Log.d("TYOE",type+"");

                yardTypeEntity=db.yardTypeDao().getfiledtypefillname(type);
                Log.e("TYPE", String.valueOf(yardTypeEntity.getId_yardTye()));
                list.clear();
                list.addAll(db.pitchDao().getselectpitch(yardTypeEntity.getId_yardTye()));
                Log.e("LIST", list.size()+"" );
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        list.addAll(db.pitchDao().getselectpitch(yardTypeEntity.getId_yardTye()));
        adapter.notifyDataSetChanged();
    }
}
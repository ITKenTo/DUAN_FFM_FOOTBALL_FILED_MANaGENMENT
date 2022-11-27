package com.example.football_field_management.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.football_field_management.Adapter.HomeYardOwnerAdapter;
import com.example.football_field_management.DAO.IClickitem;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.Order_PitchEntity;
import com.example.football_field_management.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class HomeYardOwnerFragment extends Fragment {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c = Calendar.getInstance();
    TextView tv_date,tv_total;
    RecyclerView recyclerView;
    List<Order_PitchEntity> list;
    HomeYardOwnerAdapter adapter;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_yard, container, false);
        tv_date=view.findViewById(R.id.tv_date_yard);
        tv_total=view.findViewById(R.id.tv_total_yard);
        recyclerView = view.findViewById(R.id.list_statistical);
        searchView=view.findViewById(R.id.search_view_yard_home);
        tv_date.setText(sdf.format(c.getTime()));
        double totalll= RoomDatabase_DA.getInstance(getActivity()).order_pitchDao().doanhthuYard();
        tv_total.setText( String.format(Locale.US, "%.0f", totalll)+" VND");
       // String.format(Locale.US, "%.0f", totalll);

        //tifm kiem du lieu
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                recyclerView.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                recyclerView.setAdapter(adapter);
                return false;
            }
        });

        list = RoomDatabase_DA.getInstance(getContext()).order_pitchDao().getselect();
        adapter = new HomeYardOwnerAdapter(list,getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }


}
package com.example.football_field_management.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.football_field_management.Adapter.HomeYardOwnerAdapter;
import com.example.football_field_management.DAO.IClickitem;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.Order_PitchEntity;
import com.example.football_field_management.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class HomeYardOwnerFragment extends Fragment {


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    Calendar c = Calendar.getInstance();
    TextView tv_date,tv_total,tv_ls,tv_time;
    RecyclerView recyclerView;
    List<Order_PitchEntity> list;
    HomeYardOwnerAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_yard, container, false);
        tv_date=view.findViewById(R.id.tv_date_yard);
        tv_total=view.findViewById(R.id.tv_total_yard);
        recyclerView = view.findViewById(R.id.list_statistical);

        tv_ls= view.findViewById(R.id.tv_Time_admin_home);
        tv_date.setText(sdf.format(c.getTime()));
        double totalll= RoomDatabase_DA.getInstance(getActivity()).order_pitchDao().doanhthuYard();
        tv_total.setText( formatter.format(totalll)+" VND");


       // String.format(Locale.US, "%.0f", totalll);

        //tifm kiem du lieu
       tv_ls.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               chonngay(view);
           }

       });

       tv_ls.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               adapter.filter(tv_ls.getText().toString());
               recyclerView.setAdapter(adapter);

           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });


//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                adapter.filter(query);
//                chonngay();
//                recyclerView.setAdapter(adapter);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.filter(newText);
//                recyclerView.setAdapter(adapter);
//                return false;
//            }
//        });

        list = RoomDatabase_DA.getInstance(getContext()).order_pitchDao().getselect();
        adapter = new HomeYardOwnerAdapter(list,getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }
    public void  chonngay(View view){
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
               SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
               tv_ls.setText(simpleDateFormat.format(calendar.getTime()));

            }
        },nam, thang, ngay);
        datePickerDialog.show();
    }






}
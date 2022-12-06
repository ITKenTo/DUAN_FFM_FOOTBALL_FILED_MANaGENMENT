package com.example.football_field_management.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.football_field_management.Adapter.HomeClientAdapter;
import com.example.football_field_management.Adapter.PhotoViewPager2Adapter;
import com.example.football_field_management.Adapter.PitchAdapter;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.Photo;
import com.example.football_field_management.Entity.PitchEntity;
import com.example.football_field_management.Entity.YardTypeEntity;
import com.example.football_field_management.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;


public class HomeClientFragment extends Fragment {
    PhotoViewPager2Adapter adapter;
    List<Photo> list;
    ViewPager2 viewPager2;
    CircleIndicator3 circleIndicator3;
    RecyclerView recyclerView;
    View view;
    //
    List<PitchEntity> listpitch;
    HomeClientAdapter adapterpitch;
    RoomDatabase_DA db;
    Spinner spinner;
    String type;
    YardTypeEntity yardTypeEntity;

    ///
    private Handler handler= new Handler();
    private Runnable runnable= new Runnable() {
        @Override
        public void run() {
            if (viewPager2.getCurrentItem()==list.size()-1) {
                viewPager2.setCurrentItem(0);
            } else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        finID();

        //slidel
        adapter= new PhotoViewPager2Adapter(getList());
        viewPager2.setAdapter(adapter);
        circleIndicator3.setViewPager(viewPager2);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }
        });

        //Sân
        adapterpitch= new HomeClientAdapter();
        listpitch=new ArrayList<>();
        db=RoomDatabase_DA.getInstance(getActivity());

        ///set dữ liệu spiner
        List<String> listspn = db.yardTypeDao().getfiledtypename();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), R.layout.item_sp,listspn);
        spinner.setAdapter(adapter1);


        type = listspn.size()>0? listspn.get(0) : "";
        Log.d("TYOE",type+"");
        yardTypeEntity=db.yardTypeDao().getfiledtypefillname(type);

        //
        listpitch=db.pitchDao().getselectpitch(yardTypeEntity.getId_yardTye());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapterpitch.setData(listpitch,getActivity());
        recyclerView.setAdapter(adapterpitch);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type=listspn.get(i);
              //  Log.d("TYOE",type+"");
                yardTypeEntity=db.yardTypeDao().getfiledtypefillname(type);
               // Log.e("TYPE", String.valueOf(yardTypeEntity.getId_yardTye()));
                listpitch.clear();
                listpitch.addAll(db.pitchDao().getselectpitch(yardTypeEntity.getId_yardTye()));
                adapterpitch.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return view;
    }
    private List<Photo> getList(){
        list= new ArrayList<>();
        list.add(new Photo(R.drawable.img_1));
        list.add(new Photo(R.drawable.img2));
        list.add(new Photo(R.drawable.img3));
        list.add(new Photo(R.drawable.img4));
        return list;
    }

    private void finID(){
        viewPager2=view.findViewById(R.id.viewPager2);
        circleIndicator3=view.findViewById(R.id.circle_3);
        recyclerView=view.findViewById(R.id.recyClient);
        spinner=view.findViewById(R.id.spnloaisan1);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable,3000);
    }

}
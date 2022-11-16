package com.example.football_field_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.football_field_management.Adapter.SpinnerAdapterTypePitch;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.PitchEntity;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.Entity.YardTypeEntity;

import java.util.ArrayList;

public class AddPitch extends AppCompatActivity {
     EditText namefield;
     EditText typefiled;
     EditText pricefiled;
     Button addfiled;
     RoomDatabase_DA db;
     Spinner spinner;
     int temp=0;
     SpinnerAdapterTypePitch spinnerAdapter;
     ArrayList<YardTypeEntity> list;
     int idtype;
     ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pitch);
        findview();
        db= RoomDatabase_DA.getInstance(getBaseContext());
        list= (ArrayList<YardTypeEntity>) db.yardTypeDao().getselect();
        spinnerAdapter=new SpinnerAdapterTypePitch(getApplicationContext(),list);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idtype=list.get(position).getId_yardTye();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        addfiled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
                if (temp==0) {
                    String name=namefield.getText().toString().trim();
                    double price=Double.parseDouble(pricefiled.getText().toString());
                    PitchEntity pitch=new PitchEntity();
                    pitch.setPitch_name(name);
                    pitch.setId_yardTye(idtype);
                    pitch.setPrice(price);
                    db.pitchDao().insert(pitch);
                    Toast.makeText(getApplication(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else {
                    temp=0;
                }
            }
        });
    }
    void findview(){
        spinner=findViewById(R.id.spntypepitch);
        namefield = (EditText) findViewById(R.id.namefield);
        pricefiled = (EditText) findViewById(R.id.pricefiled);
        addfiled = (Button) findViewById(R.id.addfiled);
        img=findViewById(R.id.imgback);
    }
    public void validate() {
        if (namefield.getText().length()==0) {
            namefield.setError("Vui lòng không để trống username");
            temp++;
        }else {
        }
        if (pricefiled.getText().length()==0) {
            pricefiled.setError("Vui lòng không để trống username");
            temp++;
        }else {
        }
    }
}
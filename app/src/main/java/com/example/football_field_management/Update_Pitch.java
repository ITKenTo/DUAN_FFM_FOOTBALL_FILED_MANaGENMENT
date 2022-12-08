package com.example.football_field_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.football_field_management.Adapter.SpinnerAdapterTypePitch;
import com.example.football_field_management.DATABASE.RoomDatabase_DA;
import com.example.football_field_management.Entity.PitchEntity;
import com.example.football_field_management.Entity.UserEntity;
import com.example.football_field_management.Entity.YardTypeEntity;

import java.util.ArrayList;
import java.util.List;

public class Update_Pitch extends AppCompatActivity {
    EditText namefield;
    EditText typefiled;
    EditText pricefiled;
    Button addfiled;
    RadioButton rdohd,rdokhd;
    RoomDatabase_DA db;
    Spinner spinner;
    int temp=0;
    SpinnerAdapterTypePitch spinnerAdapter;
    ArrayList<YardTypeEntity> list;
    List<YardTypeEntity> listls;
    int idtype;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pitch);
        findview();
        db= RoomDatabase_DA.getInstance(getBaseContext());
        rdohd=findViewById(R.id.rdohd);
        rdokhd=findViewById(R.id.rdokhd);
        listls=RoomDatabase_DA.getInstance(this).yardTypeDao().getselect();

        PitchEntity pitchEntity = (PitchEntity) getIntent().getSerializableExtra("pitch");
        Log.e("PITCH",pitchEntity.getPitch_name() );

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


        namefield.setText(pitchEntity.getPitch_name());
        pricefiled.setText(pitchEntity.getPrice()+"");
        for (int i=0; i<listls.size();i++){
            if (pitchEntity.getId_yardTye()== (listls.get(i).getId_yardTye())){
                spinner.setSelection(i);
            }
        }
        if (pitchEntity.getStatus().equalsIgnoreCase("HĐ")) {
            rdohd.setChecked(true);
        }else {
            rdokhd.setChecked(true);
        }


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
                    pitch.setId_pitch(pitchEntity.getId_pitch());
                    if (rdohd.isChecked()) {
                        pitch.setStatus("HĐ");
                    }else {
                        pitch.setStatus("KHĐ");
                    }
                    db.pitchDao().update(pitch);
                    Toast.makeText(getApplication(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
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
        img=findViewById(R.id.back_homeyard);
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
package com.example.yudiacc.trysliding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TambahMatkulActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText ETmatkul;
    Button Btsimpan;

    List<String> lismatkul =new ArrayList<>();
    ArrayAdapter<String> adapter;


    TambahJadwalActivity tmbjadwal = new TambahJadwalActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_matkul);

        ETmatkul = (EditText) findViewById(R.id.ETtMatkul);
        Btsimpan = (Button) findViewById(R.id.simpan);
        mydb = new DatabaseHelper(this);
        simpankeDb();
    }


    public void simpankeDb(){
        Btsimpan.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean inserted = mydb.insertdata(ETmatkul.getText().toString());
                        if (inserted == true){
//                            tmbjadwal.prepareData();
                            Toast.makeText(getApplicationContext(),"Data Berhasil di insert",Toast.LENGTH_LONG).show();
                        }else
                            Toast.makeText(getApplicationContext(),"insert Gagal",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }


//    public void updateSpinner(){
//            lismatkul=mydb.getalldatamatkul();
//            adapter = new ArrayAdapter<String>(TambahMatkulActivity.this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,lismatkul);
//            tmbjadwal.matkul.setAdapter(adapter);
//    }


}

package com.example.yudiacc.trysliding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

public class TambahTugasActivity extends AppCompatActivity {
    Spinner matakuliah;
    Button SimpanData;
    EditText deadline, deskripsi;
    DatabaseHelper mydb;

    public List<String> lismatkul =new ArrayList<>();
    public ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tugas);

        matakuliah = (Spinner) findViewById(R.id.matakuliah);
        SimpanData = (Button) findViewById(R.id.SimpanTugas);
        deadline = (EditText) findViewById(R.id.deadline);
        deskripsi = (EditText) findViewById(R.id.deskripsi);
        mydb=new DatabaseHelper(this);

        insertTugas();
        prepareData();
    }

    public void prepareData(){
        lismatkul=mydb.getalldatamatkul();
        adapter = new ArrayAdapter<String>(TambahTugasActivity.this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,lismatkul);
        matakuliah.setAdapter(adapter);
    }

    //insert data Tugas
    public void insertTugas(){
        SimpanData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean inserted = mydb.insertTugas(matakuliah.getSelectedItem().toString(),
                                deadline.getText().toString(),
                                deskripsi.getText().toString());
                        if (inserted == true){
                            Toast.makeText(getApplicationContext(),"Tugas Disimpan",Toast.LENGTH_LONG).show();
                        }else
                            Toast.makeText(getApplicationContext(),"insert Gagal",Toast.LENGTH_LONG).show();


                    }
                }
        );

    }


}

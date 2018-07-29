package com.example.yudiacc.trysliding;

import android.app.Application;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TambahJadwalActivity extends AppCompatActivity {
    public Spinner hari,matkul;

    DatabaseHelper mydb;
    EditText mulai,selesai,kodekls,ruangan;
    TimePickerDialog timePickerDialog;
    Button btnSimpan;

   public List<String> lismatkul =new ArrayList<>();
   public ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_jadwal);

        hari = (Spinner) findViewById(R.id.Hari);
        matkul = (Spinner) findViewById(R.id.matkul);
        kodekls = (EditText) findViewById(R.id.kodekls);
        ruangan = (EditText) findViewById(R.id.ruangan);
        mulai = (EditText) findViewById(R.id.mulai);
        selesai = (EditText) findViewById(R.id.selesai);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);

        mydb = new DatabaseHelper(this);
        prepareData();
        clickspinner();
        setTimeMulai();
        setTimeselesai();
        simpanJadwal();
    }

    public void simpanJadwal(){
        btnSimpan.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean inserted = mydb.insertJadwal(hari.getSelectedItem().toString(),matkul.getSelectedItem().toString(),kodekls.getText().toString(),ruangan.getText().toString(),mulai.getText().toString(),selesai.getText().toString());
                        if (inserted == true){
                            Toast.makeText(getApplicationContext(),"Jadwal Disimpan",Toast.LENGTH_LONG).show();
                        }else
                            Toast.makeText(getApplicationContext(),"insert Gagal",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void setTimeMulai(){
        mulai.setOnClickListener(
                new View.OnClickListener() {
                    Calendar calendar = Calendar.getInstance();
                    int hour = calendar.get(Calendar.HOUR);
                    int minute = calendar.get(Calendar.MINUTE);
                    @Override
                    public void onClick(View view) {
                        timePickerDialog = new TimePickerDialog(TambahJadwalActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                mulai.setText(i+":"+i1);
                            }
                        },hour,minute,true);
                        timePickerDialog.show();
                    }
                }
        );

    }


    public void setTimeselesai(){
        selesai.setOnClickListener(
                new View.OnClickListener() {
                    Calendar calendar = Calendar.getInstance();
                    int hour = calendar.get(Calendar.HOUR);
                    int minute = calendar.get(Calendar.MINUTE);
                    @Override
                    public void onClick(View view) {
                        timePickerDialog = new TimePickerDialog(TambahJadwalActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                selesai.setText(i+":"+i1);
                            }
                        },hour,minute,true);
                        timePickerDialog.show();
                    }
                }
        );

    }


    public void prepareData(){
        lismatkul=mydb.getalldatamatkul();
        adapter = new ArrayAdapter<String>(TambahJadwalActivity.this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,lismatkul);
        matkul.setAdapter(adapter);
    }

    //click Spinner
    public void clickspinner(){
        matkul.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                Toast.makeText(getApplicationContext(),""+parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void matkulBaru(View view) {
        Intent tambah = new Intent(this,TambahMatkulActivity.class);
        startActivity(tambah);
    }





}

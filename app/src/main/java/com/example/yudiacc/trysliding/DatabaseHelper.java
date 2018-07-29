package com.example.yudiacc.trysliding;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yudiacc on 7/16/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String Database = "tugasku.db";
    //table matkul
    public static final String tabelmatkul = "Matkul";
    public static final String mtkid = "id";
    public static final String mtknama = "nama";
    //table jadwal
    public static final String tabelJadwal = "Jadwal";
    public static final String jdlId = "id";
    public static final String jdlHari= "hari";
    public static final String jdlMatakuliah = "matakuliah";
    public static final String jdlKodeKelas = "kodekelas";
    public static final String jdlRuangan = "ruangan";
    public static final String jdlMulai = "mulai";
    public static final String jdlSelesai = "selesai";
    //table Tugas
    public static final String tabelTugas = "Tugas";
    public static final String TgId = "id";
    public static final String Tgmatakuliah = "matakuliah";
    public static final String TgDeadline = "deadline";
    public static final String TgDeskripsi = "deskripsi";


    public DatabaseHelper(Context context){
        super(context,Database,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+tabelmatkul+"("+mtkid+" INTEGER PRIMARY KEY, "+mtknama+" TEXT)");
        db.execSQL("create table "+tabelJadwal+"("+jdlId+" INTEGER PRIMARY KEY, "+jdlHari+" TEXT, "+jdlMatakuliah+" TEXT, "+jdlKodeKelas+" TEXT, "+jdlRuangan+" TEXT, "+jdlMulai+" TEXT, "+jdlSelesai+" TEXT)");
        db.execSQL("create table "+tabelTugas+"("+TgId+" INTEGER PRIMARY KEY, "+Tgmatakuliah+" TEXT, "+TgDeadline+" TEXT, "+TgDeskripsi+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+tabelmatkul);
        db.execSQL("DROP TABLE IF EXISTS "+tabelJadwal);
        db.execSQL("DROP TABLE IF EXISTS "+tabelTugas);
        onCreate(db);
    }

    //CRUD data Tugas
    public boolean insertTugas(String matakuliah,String deadline, String deskripsi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Tgmatakuliah,matakuliah);
        contentValues.put(TgDeadline,deadline);
        contentValues.put(TgDeskripsi,deskripsi);
        long result = db.insert(tabelTugas,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    //CRUD Data Matakuliah
    public boolean insertdata(String nama){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(mtknama,nama);
        long result = db.insert(tabelmatkul,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    //CRUD Data Jadwal
    public boolean insertJadwal(String hari,String matakuliah,String kodekls,String ruangan,String mulai,String selesai){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(jdlHari,hari);
        contentValues.put(jdlMatakuliah,matakuliah);
        contentValues.put(jdlKodeKelas,kodekls);
        contentValues.put(jdlRuangan,ruangan);
        contentValues.put(jdlMulai,mulai);
        contentValues.put(jdlSelesai,selesai);
        long result = db.insert(tabelJadwal,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }




    //get all data matkul for spinner
    public List<String> getalldatamatkul(){
        List<String> matkullist = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+mtknama+" FROM "+tabelmatkul,null);
        if (cursor.moveToFirst()){
            do {
                matkullist.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return matkullist;
    }

}

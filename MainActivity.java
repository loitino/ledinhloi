package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String DATABASE_NAME = "ledinhloi_QLBaiHat.db";
    SQLiteDatabase database;
    ListView lstDSNV;
    Button btnThemNV;
    ArrayList<BaiHat> list;
    AdapterBaiHat adapterNhanVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnThemNV = (Button) findViewById(R.id.buttonThembaihat);

        addEvent();
        lstDSNV = (ListView) findViewById(R.id.listViewBaiHat);
        list = new ArrayList<>();
        adapterNhanVien = new AdapterBaiHat(MainActivity.this, list);
        lstDSNV.setAdapter(adapterNhanVien);

        database = Database.initDatabase(MainActivity.this, DATABASE_NAME);

        Cursor cursor = database.rawQuery("Select * from BaiHat", null);
        list.clear();
        for(int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int manv = cursor.getInt(0);
            String tennv = cursor.getString(1);
            String sdt = cursor.getString(2);
            byte[] anh = cursor.getBlob(3);
            list.add(new BaiHat(manv, tennv, sdt, anh));
        }
        adapterNhanVien.notifyDataSetChanged();
    }

    private void addEvent() {
        btnThemNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });
    }
}
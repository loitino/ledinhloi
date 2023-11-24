package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterBaiHat extends BaseAdapter {
    Context context;
    ArrayList<BaiHat> list;
    ListView lstNhanVien;
    AdapterBaiHat adapter;
    public AdapterBaiHat(Context context, ArrayList<BaiHat> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row_baihat, null);
        TextView txtmaBaiHat = (TextView) row.findViewById(R.id.textviewMabaihat);
        TextView txttenBaiHat = (TextView) row.findViewById(R.id.textviewTenbaihat);
        ImageView imageAnh = (ImageView) row.findViewById(R.id.imageviewAnh);
        TextView txttenTG = (TextView) row.findViewById(R.id.textViewtenTG);
        Button btnSua = (Button) row.findViewById(R.id.buttonSua);
        Button btnXoa = (Button) row.findViewById(R.id.buttonXoa);

        BaiHat nhanVien = list.get(i);
        txtmaBaiHat.setText(nhanVien.maBaiHat + "");
        txttenBaiHat.setText(nhanVien.tenBaiHat );
        txttenTG.setText(nhanVien.tenTG);
        Bitmap bmHinh = BitmapFactory.decodeByteArray(nhanVien.Anh,0,nhanVien.Anh.length);
        imageAnh.setImageBitmap(bmHinh);

        return row;
    }

}

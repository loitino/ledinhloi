package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class InsertActivity extends AppCompatActivity {
    String DATABASE_NAME = "ledinhloi_QLBaiHat.db";
    SQLiteDatabase database;
    EditText txtmaTG, txttenBaiHat, txtmaBaiHat;
    Button btnChonHinh, btnChupHinh, btnLuu, btnHuy;
    ImageView imgAnhSua;
    final int REQUEST_TAKE_PHOTO = 123;
    final int REQUEST_CHOOSE_PHOTO = 321;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        addControl();
        addEvent();
    }

    private void addEvent() {
        btnChupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });

        btnChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }

    private void insert() {
        String maBaiHat = txtmaBaiHat.getText().toString();
        String tenBaiHat = txttenBaiHat.getText().toString();
        String maTG = txtmaTG.getText().toString();
        byte[] anh = getByteArrayFromImageView(imgAnhSua);

        ContentValues contentValues = new ContentValues();
        contentValues.put("MaTG", maTG);
        contentValues.put("TenBaiHat", tenBaiHat);
        contentValues.put("MaBaiHat", maBaiHat);
        contentValues.put("Anh", anh);

        database = Database.initDatabase(InsertActivity.this, DATABASE_NAME);
        database.insert("NhanVien", null, contentValues);

        Intent intent = new Intent(InsertActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public byte[] getByteArrayFromImageView(ImageView imgv){

//        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
//        Bitmap bmp = drawable.getBitmap();

//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
        return null;
    }

    private void addControl() {
        txtmaBaiHat = (EditText) findViewById(R.id.editTextmaBaiHat);
        txttenBaiHat = (EditText) findViewById(R.id.editTexttenBaiHat);
        btnChonHinh = (Button) findViewById(R.id.buttonChonHinh);
        btnChupHinh = (Button) findViewById(R.id.buttonChupHinh);
        btnLuu = (Button) findViewById(R.id.buttonLuu);
        btnHuy = (Button) findViewById(R.id.buttonHuy);
        imgAnhSua = (ImageView) findViewById(R.id.imageViewAnh);
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }

    private void choosePhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CHOOSE_PHOTO) {

                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgAnhSua.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_TAKE_PHOTO) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgAnhSua.setImageBitmap(bitmap);
            }
        }

    }
}

package com.example.doctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.doctruyen.database.databasedoctruyen;

public class ManDangNhap extends AppCompatActivity {
    //tao bien dang nhap
    EditText edtTaiKhoan, edtMatKhau;
    Button btnDangNhap, btnDangKy;
    //tao doi tuong database doc truyen
    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_nhap);
        Anhxa();
        //doi tuong database doc truyen
        databasedoctruyen = new databasedoctruyen(this);
        //tao su kien click button chuyen sang man hinh dang ky voi intent
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManDangNhap.this, ManDangKy.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentaikhoan = edtTaiKhoan.getText().toString();
                String matkhau = edtMatKhau.getText().toString();
                // su dung con tro de lay du lieu, goi toi getData() de lay tat ca tai khoan ở database
                Cursor cursor = databasedoctruyen.getData();
                // thuc hien vong lap de lay du lieu tu cursor voi moveToNext() di chuyen tiep
                while (cursor.moveToNext()) {
                    //lay du lieu va gan vào biến, dữ liệu tài khoản ở ô 1 và mật khẩu ở ô 2 , ô không là idtaikhoan, ô 3 là email, v ô 4 là phanquyen
                    String datatentaikhoan = cursor.getString(1);
                    String datmatkhau = cursor.getString(2);
                    //Nếu tài khoản và mật khẩu  nhap vào từ  bàn phím khớp voi database
                    if (datatentaikhoan.equals(tentaikhoan) && datmatkhau.equals(matkhau)) {
                        //lấy dữ liệu phần quyền và id
                        int phanquyen = cursor.getInt(4);
                        int idd = cursor.getInt(0);
                        String email = cursor.getString(3);
                        String tentk = cursor.getString(1);
                   //chuyển qua màn hình MainActivity
                        Intent intent = new Intent(ManDangNhap.this, MainActivity.class);
                   //gửi dữ liệu qua ativity là Mainacivity
                        intent.putExtra("phanq", phanquyen);
                        intent.putExtra("idd", idd);
                        intent.putExtra("email", email);
                        intent.putExtra("tentaikhoan",tentk);
                        startActivity(intent);
                    }

                }
                //thực hiện trả cursor về đầu
                cursor.moveToFirst();
                //đóng khi không dùng
                cursor.close();
            }
        });

    }

    private void Anhxa() {
        edtMatKhau = findViewById(R.id.matkhau);
        edtTaiKhoan = findViewById(R.id.taikhoan);
        btnDangKy = findViewById(R.id.dangky);
        btnDangNhap = findViewById(R.id.dangnhap);
    }
}
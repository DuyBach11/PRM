package com.example.doctruyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Notification;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Toolbar toolbar;
ViewFlipper viewFlipper;
NavigationView navigationView;
ListView listView, listViewNew, listViewThongTin;
DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        ActionViewFliper();
    }
//Phương thức chạy quảng cáo với viewFliper
    private void ActionViewFliper() {
        //mảng chứa tấm ảnh cho quảng cáo
        ArrayList<String> mangquangcao = new ArrayList<>();
        //add ảnh vào mảng
        mangquangcao.add("https://cdn.popsww.com/blog-kids/sites/3/2022/04/songoku.jpg");
        mangquangcao.add("https://sohanews.sohacdn.com/k:thumb_w/640/2015/1-1-2-1-1432612241655/20-bi-mat-thu-vi-ve-loat-phim-hoat-hinh-7-vien-ngoc-rong.jpg");
        mangquangcao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSdoJO09rTY0IsSgJo86DVScM-kiuDRzw-O0nuvTIjBZjBQE_XT6BHOA6hdQ1UHDCJCmg&usqp=CAU");
        // thực hiện vòng lặp gắn ảnh vào ImageView , rồi từ imgview lên app
        for (int i = 0; i<mangquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            //sử dụng hàm thư viện picasso

            Picasso.get().load(mangquangcao.get(i)).into(imageView);

            //phương thức chỉnh hình vừa khung quang cáo
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            //Thêm ảnh tử imgview vào flipper
            viewFlipper.addView(imageView);
        }
        //thiết lập tự động ảnh chạy 4s
        viewFlipper.setFlipInterval(4000);
        //run auto
        viewFlipper.setAutoStart(true);

        //gọi animation cho vào và ra
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right
        );
        //gọi Animation vào viewfliper
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);


    }

    //Phương Thức Ánh Xạ
    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        listViewNew = findViewById(R.id.listviewNew);
        listView = findViewById(R.id.listviewmanhinhchinh);
        listViewThongTin = findViewById(R.id.listviewmanhinhchinh);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerlayout);
    }
}
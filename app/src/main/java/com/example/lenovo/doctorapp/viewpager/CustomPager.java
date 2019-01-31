package com.example.lenovo.doctorapp.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovo.doctorapp.MainActivity;
import com.example.lenovo.doctorapp.R;

public class CustomPager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_pager);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));


        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(CustomPager.this,MainActivity.class);
                startActivity(i);

            }
        },5000);


    }

}

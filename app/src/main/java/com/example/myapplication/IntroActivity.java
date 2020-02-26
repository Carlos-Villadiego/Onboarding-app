package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.data.model.LoggedInUser;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnant;
    Button btnsig;
    Button btnskip;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_intro);

        getSupportActionBar().hide();

        btnant = findViewById(R.id.btnant);
        btnant.setEnabled(false);
        btnsig = findViewById(R.id.btnsig);
        btnskip = findViewById(R.id.btnskip);
        tabIndicator = findViewById(R.id.tab_indicator);

        final List<ScreenItem> mList = new ArrayList<>();

        mList.add(new ScreenItem("Bienvenido a la App",R.drawable.img1));
        mList.add(new ScreenItem("Comunique los errores",R.drawable.img2));
        mList.add(new ScreenItem("Asi aumentamos la productividad",R.drawable.img3));

        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);


        tabIndicator.setupWithViewPager(screenPager);

        btnant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if (position < mList.size()){
                    position--;
                    screenPager.setCurrentItem(position);
                    if (position == 0){
                        btnant.setEnabled(false);
                    }
                }
            }
        });

        btnsig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if (position < mList.size()){
                    position++;
                    screenPager.setCurrentItem(position);
                    btnant.setEnabled(true);
                    if (position == mList.size()){
                        Intent login = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(login);
                    }

                }
            }
        });

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}

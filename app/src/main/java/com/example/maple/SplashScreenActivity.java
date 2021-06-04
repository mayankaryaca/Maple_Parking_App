package com.example.maple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.maple.ViewControllers.MapleSharedPreferences;

public class SplashScreenActivity extends AppCompatActivity {

    private Handler delayHandler = new Handler();
    private boolean isRememberMeValid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        MapleSharedPreferences mapleSharedPreferences = new MapleSharedPreferences(getApplicationContext());
        isRememberMeValid = mapleSharedPreferences.isRememberMeValid();
        delayHandler.postDelayed(new Runnable() {

            @Override
            public void run() {

                try {
                    if(isRememberMeValid){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }, 3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        delayHandler.removeCallbacksAndMessages(null);
    }
}
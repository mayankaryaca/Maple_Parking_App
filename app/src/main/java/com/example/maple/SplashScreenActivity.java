package com.example.maple;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.example.maple.ViewControllers.MapleSharedPreferences;
import com.example.maple.databinding.ActivityMainBinding;
import com.example.maple.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {

    private Handler delayHandler = new Handler();
    private boolean isRememberMeValid;
    ActivitySplashScreenBinding binding;
    RotateAnimation rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
        setContentView(view);

        rotate = new RotateAnimation(0, 100);
        rotate.setDuration(1000);
        this.binding.appCompatImageView.startAnimation(rotate);

        MapleSharedPreferences mapleSharedPreferences = new MapleSharedPreferences(getApplicationContext());
        isRememberMeValid = mapleSharedPreferences.isRememberMeValid();
        delayHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                try {
                    if(isRememberMeValid){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent = new Intent(getApplicationContext(), LoginScreenActivity.class);
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
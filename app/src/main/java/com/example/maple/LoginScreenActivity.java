package com.example.maple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.maple.ViewControllers.MapleSharedPreferences;
import com.example.maple.databinding.ActivityLoginScreenBinding;
import com.example.maple.databinding.ActivityMainBinding;

public class LoginScreenActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginScreenBinding binding;
    MapleSharedPreferences mapleSharedPreferences;

    public final String TAG = this.getClass().getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
        setContentView(view);

        mapleSharedPreferences = new MapleSharedPreferences(getApplicationContext());
        this.binding.btLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
            if (v != null) {
                switch (v.getId()) {
                    case R.id.btLogin: {

                    }
                    case R.id.btSignUp: {

                    }
                }
            }
    }
}
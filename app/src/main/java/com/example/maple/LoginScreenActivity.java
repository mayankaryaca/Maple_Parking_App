package com.example.maple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.maple.Repositories.FirebaseAuthenticationController;
import com.example.maple.ViewControllers.MapleSharedPreferences;
import com.example.maple.databinding.ActivityLoginScreenBinding;
import com.example.maple.databinding.ActivityMainBinding;

public class LoginScreenActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginScreenBinding binding;
    MapleSharedPreferences mapleSharedPreferences;
    FirebaseAuthenticationController firebaseAuthenticationController = new FirebaseAuthenticationController();

    public final String TAG = this.getClass().getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
        setContentView(view);

        firebaseAuthenticationController.getInstance();
        mapleSharedPreferences = new MapleSharedPreferences(getApplicationContext());
        this.binding.btLogin.setOnClickListener(this);
        this.binding.btnSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
            if (v != null) {
                switch (v.getId()) {
                    case R.id.btLogin: {

                        String emailId = this.binding.etLoginEmailId.getText().toString();
                        String password = this.binding.etEditPassword.getText().toString();
                        Boolean rememberMe = this.binding.cbRememberMe.isChecked();
                        firebaseAuthenticationController.getCurrentUser();


                       this.firebaseAuthenticationController.firebaseAuthLoginUser(this,emailId,password).observe(this, new Observer<Boolean>() {
                           @Override
                           public void onChanged(Boolean aBoolean) {

                               if(aBoolean){
                                   String uid = firebaseAuthenticationController.getUserId();
                                   mapleSharedPreferences.loginUser(emailId,password,rememberMe,uid);
                                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                   startActivity(intent);
                                   finish();
                               }else{
                                   Toast.makeText(getApplicationContext(), "Please Enter valid email/password!", Toast.LENGTH_SHORT).show();
                               }

                           }
                       });
                       break;
                    }
                    case R.id.btnSignUp: {
                        Log.d(TAG,"On sign up button clicke");

                        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    }
                }
            }
    }
}
package com.example.maple.Repositories;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class FirebaseAuthenticationController {
    String TAG = this.getClass().getCanonicalName();
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    MutableLiveData<Boolean> isLoginSuccess = new MutableLiveData<>() ;
    MutableLiveData<Boolean> isCreated = new MutableLiveData<>() ;

    public FirebaseAuth getInstance(){
        mAuth = FirebaseAuth.getInstance();
        return mAuth;
    }

    public FirebaseUser getCurrentUser(){
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
        return currentUser;
    }

<<<<<<< HEAD

    public MutableLiveData<Boolean> firebaseAuthSignUpUser(Context context, String email, String password) {
=======
    public void firebaseAuthSignUpUser(Context context, String email, String password) {
>>>>>>> 019cb00e19eaafdc1347966e185be9fcfad6c594
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            isCreated.setValue(true);
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            // Password must have at least 6 characters
                            // The email address is already in use by another account
                            isCreated.setValue(false);
                        }
                    }
                });
        return isCreated;
    }


    public  MutableLiveData<Boolean> firebaseAuthLoginUser(Context context,String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            isLoginSuccess.setValue(true);
                        } else {
                            Log.e(TAG, "signInWithEmail:failure", task.getException());
                            isLoginSuccess.setValue(false);
                        }
                    }
                });

        return isLoginSuccess;
    }

    public String getUserId(){
        this.getCurrentUser();
        String userId = currentUser.getUid();
        return userId;
    }
}

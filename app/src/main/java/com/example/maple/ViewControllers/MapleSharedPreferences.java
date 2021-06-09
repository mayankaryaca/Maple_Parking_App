package com.example.maple.ViewControllers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.maple.R;

public class MapleSharedPreferences {
    private MapleSharedPreferences myInstance;
    private String USER_NAME = "Username";
    private String PASSWORD = "Password";
    private String REMEMBER_ME = "RememberMe";
    private String FAUTH_ID = "FirebaseUserId";

    private static SharedPreferences mapleSharedPreferences;
    private static SharedPreferences.Editor mapleEditor;

    public MapleSharedPreferences(Context context){
        mapleSharedPreferences = context.getSharedPreferences(String.valueOf(R.string.app_name),
                Context.MODE_PRIVATE);
        this.mapleEditor = mapleSharedPreferences.edit();
    }



    public void loginUser(String username, String password, Boolean rememberMe,String UID){
        mapleEditor.putString(USER_NAME,username);
        mapleEditor.putString(PASSWORD, password);
        mapleEditor.putBoolean(REMEMBER_ME, rememberMe);
        mapleEditor.putString(FAUTH_ID, UID);
        mapleEditor.commit();
    }


    public boolean isRememberMeValid(){
        return mapleSharedPreferences.getBoolean(REMEMBER_ME,false);
    }

}

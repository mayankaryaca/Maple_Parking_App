package com.example.maple.ViewControllers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.maple.R;

public class MapleSharedPreferences {
    private MapleSharedPreferences myInstance;
    private String USER_NAME = "Username";
    private String EMAIL_ID = "EmailId";
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



    public void loginUser(String email, String password, Boolean rememberMe,String UID){
        mapleEditor.putString(EMAIL_ID,email);
        mapleEditor.putString(PASSWORD, password);
        mapleEditor.putBoolean(REMEMBER_ME, rememberMe);
        mapleEditor.putString(FAUTH_ID, UID);
        mapleEditor.commit();
    }


    public void logOutUser(){
        mapleEditor.clear();
        mapleEditor.commit();
    }
    public String getUserId(){
        return mapleSharedPreferences.getString(FAUTH_ID,"");
    }
    public boolean isRememberMeValid(){
        return mapleSharedPreferences.getBoolean(REMEMBER_ME,false);
    }

    public void setUserName(String username) {
        mapleEditor.putString(USER_NAME,username);
        mapleEditor.commit();
    }

    public String getUserName() {
        return mapleSharedPreferences.getString(USER_NAME,"");
    }

    public String getEmailId(){
        return mapleSharedPreferences.getString(EMAIL_ID,"");
    }
}

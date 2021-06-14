package com.example.maple;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maple.ViewControllers.MapleSharedPreferences;
import com.example.maple.databinding.FragmentEditParkingDetailsBinding;
import com.example.maple.databinding.FragmentWelcomeScreenMapleBinding;


public class WelcomeScreenMaple extends Fragment {
    private MapleSharedPreferences mapleSharedPreferences;
    FragmentWelcomeScreenMapleBinding binding;
    @SuppressLint("StringFormatInvalid")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWelcomeScreenMapleBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mapleSharedPreferences = new MapleSharedPreferences(getContext());
        String welcomeLabel = "Dear " +  mapleSharedPreferences.getUserName() + ",";

        this.binding.tvWelcomeLabel.setText(welcomeLabel);

        String data= "Welcome to Maple parking app where you can efficiently save all your parking data quickly. " +"<br/><br/><B>"+ "Home"+ "</B>"+ ": You can find the list of all parkings which you have saved. You can update and delete any parking you want."+ "<br/><br/><B>"+"Profile"+"</B>"+" : You can view and edit any informatin you want to change."+"<br/><br/><B>"+"Add Parking"+"</B>"+" : You can enter the required data and save the parking using your current location or street name. "+"<br/><br/><br/><B>"+"So,go ahead and save your first parking!"+"</B>";
        this.binding.tvInfoLabel.setText(Html.fromHtml(data));

        return view;
    }
}
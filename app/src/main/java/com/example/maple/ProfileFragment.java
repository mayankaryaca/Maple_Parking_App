package com.example.maple;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maple.Models.Profile;
import com.example.maple.Models.User;
import com.example.maple.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;

//    public ProfileFragment() {
//        // Required empty public constructor
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Bundle bundle = this.getArguments();
        Profile profile  = (Profile) bundle.getSerializable("PROFILE");
        User user  = (User) bundle.getSerializable("USER");

        profileUI(view, profile, user);

        return view;
    }

    private void profileUI(View view, Profile profile, User user) {
        this.binding.tvDisplayFirstName.setText(profile.getFirstName());
        this.binding.tvDisplayLastName.setText(profile.getLastName());
        this.binding.tvDisplayEmail.setText(user.getEmail());
        this.binding.tvDisplayPhoneNumber.setText(profile.getContact());
        this.binding.tvDisplayPlateNumber.setText(profile.getCarPlate());
    }
}


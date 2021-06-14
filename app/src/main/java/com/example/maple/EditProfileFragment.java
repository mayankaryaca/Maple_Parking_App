package com.example.maple;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maple.Models.Profile;
import com.example.maple.Models.User;
import com.example.maple.databinding.FragmentEditProfileBinding;

public class EditProfileFragment extends Fragment {

    FragmentEditProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Bundle bundle = this.getArguments();
        Profile profile = (Profile) bundle.getSerializable("PROFILE");
        User user = (User) bundle.getSerializable("USER");
        profileUI(view, profile, user);

        return view;}

    private void profileUI(View view, Profile profile, User user) {
        this.binding.etOutputFirstName.setText(profile.getFirstName());
        this.binding.etOutputLastName.setText(profile.getLastName());
        this.binding.etOutputEmail.setText(user.getEmail());
        this.binding.etOutputContact.setText(profile.getContact());
        this.binding.etOutputPlate.setText(profile.getCarPlate());
    }
}
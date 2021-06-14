package com.example.maple;

import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maple.Models.Parking;
import com.example.maple.Models.Profile;
import com.example.maple.ViewControllers.UserViewModel;
import com.example.maple.databinding.FragmentEditProfileBinding;

public class EditProfileFragment extends Fragment {

    FragmentEditProfileBinding binding;
    UserViewModel userViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(
                inflater, container, false);
        View view = binding.getRoot();

        userViewModel = UserViewModel.getInstance(getActivity().getApplication());
        Bundle bundle = this.getArguments();
        Profile profile = (Profile) bundle.getSerializable("Profile");

        if(profile != null){
            this.binding.etFirstName.setText(profile.getFirstName());
            this.binding.etLastName.setText(profile.getLastName());
            this.binding.etPhoneNumber.setText(profile.getContact());
            this.binding.etPlateNumber.setText(profile.getCarPlate());
        }

        this.binding.btUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = binding.etFirstName.getText().toString();
                String lastName = binding.etLastName.getText().toString();
                String phoneNumber = binding.etPhoneNumber.getText().toString();
                String plateNumber = binding.etPlateNumber.getText().toString();

                Profile newProfile = new Profile(firstName,lastName,phoneNumber,plateNumber);
                userViewModel.updateProfile(newProfile);
            }
        });
        return view;
    }
}
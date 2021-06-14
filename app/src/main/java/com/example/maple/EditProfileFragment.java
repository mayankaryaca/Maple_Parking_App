package com.example.maple;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maple.Models.Parking;
import com.example.maple.Models.Profile;
import com.example.maple.ViewControllers.UserViewModel;
import com.example.maple.databinding.FragmentEditProfileBinding;

import com.example.maple.Models.User;

public class EditProfileFragment extends Fragment {

    FragmentEditProfileBinding binding;
    UserViewModel userViewModel;
    Profile profile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        this.userViewModel = UserViewModel.getInstance(getActivity().getApplication());
        Bundle bundle = this.getArguments();
        profile = (Profile) bundle.getSerializable("Profile");

        Log.d("TAG","Profile id" + profile.getId());
        if(profile!=null){
            setProfileUI(profile);
        }

        this.binding.btUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = binding.etOutputFirstName.getText().toString();
                String lastName = binding.etOutputLastName.getText().toString();
                String phoneNumber = binding.etOutputContact.getText().toString();
                String plateNumber = binding.etOutputPlate.getText().toString();
//                   String email = binding.etOutputEmail.getText().toString();
                Profile newProfile = new Profile(profile.getId(),firstName,lastName,phoneNumber,plateNumber);
                userViewModel.updateProfile(newProfile);
            }
        });
        return view;
    }

    private void setProfileUI(Profile profile) {
        this.binding.etOutputFirstName.setText(profile.getFirstName());
        this.binding.etOutputLastName.setText(profile.getLastName());
//        this.binding.etOutputEmail.setText(user.getEmail());
        this.binding.etOutputContact.setText(profile.getContact());
        this.binding.etOutputPlate.setText(profile.getCarPlate());
    }

    private void validateInput(){

    }
}
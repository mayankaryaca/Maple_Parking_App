package com.example.maple;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.maple.Models.Parking;
import com.example.maple.Models.Profile;
import com.example.maple.ViewControllers.UserViewModel;
import com.example.maple.databinding.FragmentEditProfileBinding;

public class EditProfileFragment extends Fragment {
    private final String TAG = this.getClass().getCanonicalName();
    FragmentEditProfileBinding binding;
    UserViewModel userViewModel;
    Profile profile;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        context  = this.getContext();
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

                if(validateInput()) {
                    Profile newProfile = new Profile(profile.getId(), firstName, lastName, phoneNumber, plateNumber);
                    boolean isSuccess = userViewModel.updateProfile(newProfile);
                    makeToast("Profile updated successfully");
                }
            }
        });
        return view;
    }

    private void setProfileUI(Profile profile) {
        this.binding.etOutputFirstName.setText(profile.getFirstName());
        this.binding.etOutputLastName.setText(profile.getLastName());
        this.binding.etOutputContact.setText(profile.getContact());
        this.binding.etOutputPlate.setText(profile.getCarPlate());
    }

    private boolean validateInput() {
        boolean isValidInput = true;
        if (this.binding.etOutputFirstName.getText().toString().isEmpty()){
            this.binding.etOutputFirstName.setError("Please enter Name");
            isValidInput = false;
        }
        if (this.binding.etOutputLastName.getText().toString().isEmpty()){
            this.binding.etOutputLastName.setError("Please enter Name");
            isValidInput = false;
        }

        if (this.binding.etOutputPlate.getText().toString().isEmpty()){
            this.binding.etOutputPlate.setError("Please enter Car Plate");
            isValidInput = false;
        }
        if (this.binding.etOutputPlate.getText().length() < 2 ||
                this.binding.etOutputPlate.getText().length() > 8) {
            this.binding.etOutputPlate.setError("Car Plate must have 2-8 characters");
            isValidInput = false;
        }
        Log.d(TAG, "validate - end: " + isValidInput);

        return isValidInput;
    }

    public void makeToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
package com.example.maple;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maple.Models.Profile;
import com.example.maple.Models.User;
import com.example.maple.Repositories.FirebaseAuthenticationController;
import com.example.maple.ViewControllers.MapleSharedPreferences;
import com.example.maple.ViewControllers.UserViewModel;
import com.example.maple.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    MapleSharedPreferences mapleSharedPreferences;
    private UserViewModel userViewModel;
    Profile userProfile;
    FirebaseAuthenticationController firebaseAuthenticationController = new FirebaseAuthenticationController();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        firebaseAuthenticationController.getInstance();

        mapleSharedPreferences = new MapleSharedPreferences(getActivity());
        this.userViewModel = UserViewModel.getInstance(getActivity().getApplication());
        userViewModel.getUserProfile(mapleSharedPreferences.getUserId());

        this.userViewModel.userProfile.observe(getActivity(), new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                userProfile = profile;
                if(profile != null){
                    profileUI(profile);
                }
            }
        });

        this.binding.btEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userProfile!=null) {
                    EditProfileFragment myFragment = new EditProfileFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Profile", userProfile);
                    myFragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, myFragment).addToBackStack(null).commit();
                }else{
                    Toast.makeText(getContext(),"Edit option not available",Toast.LENGTH_SHORT).show();
                }
            }
        });
        this.binding.btDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogBox();
            }
        });

        this.binding.btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapleSharedPreferences.logOutUser();
                Toast.makeText(getContext(),"Log out success!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), LoginScreenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });



        return view;
    }

    private void profileUI(Profile profile) {
        this.binding.tvDisplayFirstName.setText(profile.getFirstName());
        this.binding.tvDisplayLastName.setText(profile.getLastName());
        this.binding.tvDisplayPhoneNumber.setText(profile.getContact());
        this.binding.tvDisplayPlateNumber.setText(profile.getCarPlate());
    }

    private void reauthUser(String password){
        firebaseAuthenticationController.firebaseReauthenticate(getContext(),mapleSharedPreferences.getEmailId(),password);

    }

    private void showDialogBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Please Confirm user");
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);
        builder.setPositiveButton("Authenticate user", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String password = input.getText().toString();
                reauthUser(password);
                firebaseAuthenticationController.firebaseDeleteUser(getContext(), firebaseAuthenticationController.getCurrentUser()
                ).observe((LifecycleOwner) getContext(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean) {
                            mapleSharedPreferences.logOutUser();
                            Toast.makeText(getContext(),"Account deleted successfully",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), LoginScreenActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}


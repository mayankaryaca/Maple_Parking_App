package com.example.maple;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.maple.Models.Profile;
import com.example.maple.Models.User;
import com.example.maple.ViewControllers.MapleSharedPreferences;
import com.example.maple.ViewControllers.UserViewModel;
import com.example.maple.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    MapleSharedPreferences mapleSharedPreferences;
    private UserViewModel userViewModel;
    Profile userProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mapleSharedPreferences = new MapleSharedPreferences(getActivity());
        this.userViewModel = UserViewModel.getInstance(getActivity().getApplication());
        userViewModel.getUserProfile(mapleSharedPreferences.getUserId());

        this.userViewModel.userProfile.observe(getActivity(), new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                userProfile = profile;
                Log.d("TAG","Data Recieved" + profile.toString());
                profileUI(profile);
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

        return view;
    }

    private void profileUI(Profile profile) {
        this.binding.tvDisplayFirstName.setText(profile.getFirstName());
        this.binding.tvDisplayLastName.setText(profile.getLastName());
        this.binding.tvDisplayPhoneNumber.setText(profile.getContact());
        this.binding.tvDisplayPlateNumber.setText(profile.getCarPlate());
    }
}


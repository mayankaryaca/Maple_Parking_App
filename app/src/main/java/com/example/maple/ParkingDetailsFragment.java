package com.example.maple;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maple.Models.Parking;
import com.example.maple.databinding.FragmentParkingDetailsBinding;
import com.example.maple.databinding.FragmentParkingListBinding;


public class ParkingDetailsFragment extends Fragment {


    FragmentParkingDetailsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentParkingDetailsBinding.inflate(
                inflater, container, false);
        View view = binding.getRoot();
        Bundle bundle = this.getArguments();
        Parking myValue = (Parking) bundle.getSerializable("PARKING");
        binding.tvParkingData.setText(myValue.toString() + myValue.getDoc_id());

        return view;
    }
}
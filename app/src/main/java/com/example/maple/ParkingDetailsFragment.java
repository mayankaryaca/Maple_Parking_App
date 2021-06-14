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

        binding = FragmentParkingDetailsBinding.inflate(
                inflater, container, false);
        View view = binding.getRoot();

        Bundle bundle = this.getArguments();
        Parking parkingDetail = (Parking) bundle.getSerializable("PARKING");

        parkingUI(view, parkingDetail);

        return view;
    }
    private void parkingUI(View view, Parking parkingDetail) {
        this.binding.tvOutputBuildingCode.setText(parkingDetail.getBuilding_number());
        this.binding.tvOutputApartmentNum.setText(parkingDetail.getApt_number());
        this.binding.tvOutputPlateNum.setText(parkingDetail.getPlate_number());
        this.binding.tvOutputNumOfHours.setText(parkingDetail.getNumber_of_hours());
        this.binding.tvStreet.setText(parkingDetail.getStreet_address());
        this.binding.tvStreet.setText(parkingDetail.getStreet_address());
    }
}
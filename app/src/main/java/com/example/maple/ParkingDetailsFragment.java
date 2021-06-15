package com.example.maple;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.maple.Helpers.LocationHelper;
import com.example.maple.Models.Parking;
import com.example.maple.ViewControllers.ParkingViewModel;
import com.example.maple.databinding.FragmentAddParkingBinding;
import com.example.maple.databinding.FragmentParkingDetailsBinding;
import com.example.maple.databinding.FragmentParkingListBinding;


public class ParkingDetailsFragment extends Fragment {
    String TAG = getClass().getCanonicalName();
    ParkingViewModel parkingViewModel;
    FragmentParkingDetailsBinding binding;
    private LocationHelper locationHelper;
    private Location parkingLocation;
    private Address mAddress;
    private String lat;
    private String lng;
    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentParkingDetailsBinding.inflate(
                inflater, container, false);
        View view = binding.getRoot();

        this.context = this.getActivity().getApplication().getApplicationContext();

        Bundle bundle = this.getArguments();
        this.parkingViewModel = ParkingViewModel.getInstance(getActivity().getApplication());

        this.locationHelper = LocationHelper.getInstance();
        this.locationHelper.checkPermissions(this.context);

        Parking parkingDetail = (Parking) bundle.getSerializable("PARKING");

        parkingUI(view, parkingDetail);

        this.binding.btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set mapView
                Log.d(TAG, "Lat " + parkingDetail.getGeo_location_lat() + " Lng " + parkingDetail.getGeo_location_lng());

                Intent mapIntent = new Intent(context, MapsActivity.class);
                mapIntent.putExtra("EXTRA_LAT", parkingDetail.getGeo_location_lat());
                mapIntent.putExtra("EXTRA_LNG", parkingDetail.getGeo_location_lng());
                startActivity(mapIntent);
            }
        });
        return view;
    }

    private void parkingUI(View view, Parking parkingDetail) {
        this.binding.tvOutputBuildingCode.setText(parkingDetail.getBuilding_number());
        this.binding.tvOutputApartmentNum.setText(parkingDetail.getApt_number());
        this.binding.tvOutputPlateNum.setText(parkingDetail.getPlate_number());
        this.binding.tvOutputNumOfHours.setText(parkingDetail.getNumber_of_hours());
        this.binding.tvStreet.setText(parkingDetail.getStreet_address());
    }

}
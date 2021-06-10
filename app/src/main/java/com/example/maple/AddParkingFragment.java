package com.example.maple;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maple.Models.Parking;
import com.example.maple.ViewControllers.MapleSharedPreferences;
import com.example.maple.ViewControllers.ParkingViewModel;
import com.example.maple.databinding.FragmentAddParkingBinding;


public class AddParkingFragment extends Fragment {

    FragmentAddParkingBinding binding;
    ParkingViewModel parkingViewModel;
    private MapleSharedPreferences mapleSharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentAddParkingBinding binding = FragmentAddParkingBinding.inflate(
                inflater, container, false);
        View view = binding.getRoot();
        mapleSharedPreferences = new MapleSharedPreferences(this.getActivity().getBaseContext());

        this.parkingViewModel = ParkingViewModel.getInstance(getActivity().getApplication());


        //MARK : Spinner Parking Hours

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.parking_hours, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner parkingSpinner = view.findViewById(R.id.sNumOfHours);
        parkingSpinner.setAdapter(adapter);

        //MARK : Geo Location Switch

        binding.geoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.etStreet.setHint("Enter Latitude...");
                    binding.etLocality.setHint("Enter Longitude...");
                    binding.etCountry.setVisibility(View.INVISIBLE);
                } else {
                    binding.etStreet.setHint("Enter Street...");
                    binding.etLocality.setHint("Enter Locality...");
                    binding.etCountry.setVisibility(View.VISIBLE);

                }
            }
        });


        binding.btnAddParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String building_number = binding.etBuildingCode.getText().toString();
                String apt_number = binding.etApartmentNum.getText().toString();
                String plate_number = binding.etPlateNum.getText().toString();
                String number_of_hours= binding.sNumOfHours.getSelectedItem().toString();
                String street_address= binding.etStreet.getText().toString();
                String geo_location_lat= "Test V1";
                String geo_location_lng= "Test V1";
                String user_id= mapleSharedPreferences.getUserId();


                Parking parking = new Parking(building_number,apt_number,plate_number,number_of_hours,street_address,geo_location_lat,geo_location_lng,user_id);
                boolean isSuccess = parkingViewModel.addNewParking(parking);
                if(isSuccess){
                    Toast.makeText(getContext(), "Parking Added successfully", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getContext(), "Error occured. Please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }


}
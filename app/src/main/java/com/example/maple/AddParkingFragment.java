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

import com.example.maple.databinding.FragmentAddParkingBinding;


public class AddParkingFragment extends Fragment {

    FragmentAddParkingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentAddParkingBinding binding = FragmentAddParkingBinding.inflate(
                inflater, container, false);
        View view = binding.getRoot();


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
        return view;
    }
}
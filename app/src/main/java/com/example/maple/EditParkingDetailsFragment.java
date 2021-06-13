package com.example.maple;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.maple.Models.Parking;
import com.example.maple.databinding.FragmentEditParkingDetailsBinding;
import com.example.maple.databinding.FragmentParkingListBinding;


public class EditParkingDetailsFragment extends Fragment {

  FragmentEditParkingDetailsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditParkingDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Bundle bundle = this.getArguments();
        Parking parking = (Parking) bundle.getSerializable("PARKING");

        setParkingUI(view, parking);

        return view;}

        private void setParkingUI(View view, Parking parking){
            this.binding.etBuildingCode.setText(parking.getBuilding_number());
            this.binding.etApartmentNum.setText(parking.getApt_number());
            this.binding.etPlateNum.setText(parking.getPlate_number());

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.parking_hours, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            Spinner parkingSpinner = view.findViewById(R.id.sNumOfHours);
            parkingSpinner.setAdapter(adapter);

            String previousValue = parking.getNumber_of_hours();
            if (previousValue != null) {
                int spinnerPosition = adapter.getPosition(previousValue);
                parkingSpinner.setSelection(spinnerPosition);
            }

        }
}
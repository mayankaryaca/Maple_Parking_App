package com.example.maple;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.maple.Helpers.LocationHelper;
import com.example.maple.Models.Parking;
import com.example.maple.ViewControllers.MapleSharedPreferences;
import com.example.maple.ViewControllers.ParkingViewModel;
import com.example.maple.databinding.FragmentAddParkingBinding;
import com.google.android.gms.location.LocationCallback;


public class AddParkingFragment extends Fragment {
    String TAG = getClass().getCanonicalName();
    FragmentAddParkingBinding binding;
    ParkingViewModel parkingViewModel;
    private MapleSharedPreferences mapleSharedPreferences;
    private LocationHelper locationHelper;
    private Location lastLocation;
    private Address mAddress;
    private String lat;
    private String lng;
    Context context;
    Boolean isValidInput = true;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentAddParkingBinding binding = FragmentAddParkingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        this.view = view;
        this.context = this.getActivity().getApplication().getApplicationContext();
        mapleSharedPreferences = new MapleSharedPreferences(this.context);

        this.parkingViewModel = ParkingViewModel.getInstance(getActivity().getApplication());

        this.locationHelper = LocationHelper.getInstance();
        this.locationHelper.checkPermissions(this.context);



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
                    if (locationHelper.locationPermissionGranted) {
                        Log.d(TAG, "onCreate: Location Permission Granted");

                        locationHelper.getLocation(context).observe(getViewLifecycleOwner(), new Observer<Location>() {
                            @Override
                            public void onChanged(Location location) {
                                if (location != null){
                                    lastLocation = location;
                                    lat = String.valueOf(lastLocation.getLatitude());
                                    lng = String.valueOf(lastLocation.getLongitude());
                                    binding.etStreet.setText(String.valueOf(lastLocation.getLatitude()));
                                    binding.etLocality.setText(String.valueOf(lastLocation.getLongitude()));
                                    Log.d(TAG, "onCreate: Last Location obtained " + lastLocation.toString());
                                }
                            }
                        });
                    }
                    binding.btnFetchLocation.setVisibility(View.GONE);
                    binding.etCountry.setVisibility(View.GONE);
                } else {
                    binding.etStreet.setText("");
                    binding.etLocality.setText("");
                    binding.etCountry.setText("");
                    binding.etStreet.setHint("Enter Street...");
                    binding.etLocality.setHint("Enter Locality...");
                    binding.etCountry.setVisibility(View.VISIBLE);
                    binding.btnFetchLocation.setVisibility(View.VISIBLE);
                }
            }
        });


        binding.btnFetchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String street = String.valueOf(binding.etStreet.getText());
                String locality = String.valueOf(binding.etLocality.getText());
                String country = String.valueOf(binding.etCountry.getText());
                locationHelper.getLocationFromAddress(context,street,locality,country).observe(getViewLifecycleOwner(), new Observer<Address>() {
                    @Override
                    public void onChanged(Address address) {
                        mAddress = address;
                        lat = String.valueOf(mAddress.getLatitude());
                        lng = String.valueOf(mAddress.getLongitude());
                        Toast.makeText(context, "Location recieved successfully", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        binding.btnAddParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String building_number = binding.etBuildingCode.getText().toString();
                String apt_number = binding.etApartmentNum.getText().toString();
                String plate_number = binding.etPlateNum.getText().toString();
                String number_of_hours= binding.sNumOfHours.getSelectedItem().toString();
                String street_address= binding.etStreet.getText().toString() ;
                String locality = binding.etLocality.getText().toString() ;
                String country = binding.etCountry.getText().toString() ;


                String geo_location_lat= lat;
                String geo_location_lng= lng;

                String user_id= mapleSharedPreferences.getUserId();
                String fetchedAddress = street_address + ","+locality+","+ country;

//                validateInput(building_number,apt_number,plate_number,street_address);
                if(building_number.length() < 4){
                    binding.etBuildingCode.setError("Please enter an input more than 4 characters!");
                    isValidInput = false;
                }
                if(apt_number.isEmpty()){
                    binding.etApartmentNum.setError("Please enter an apartment number!");
                    isValidInput = false;
                }
                if(plate_number.length()<2 || plate_number.length()>8){
                    binding.etPlateNum.setError("Please enter min 2 and max 8 characters!");
                    isValidInput = false;
                }

                if(country.isEmpty()){
                    fetchedAddress = locationHelper.getAddress(context,lastLocation);
                }

                if(isValidInput){
                    Parking parking = new Parking(building_number,apt_number,plate_number,number_of_hours,fetchedAddress,geo_location_lat,geo_location_lng,user_id);
                    boolean isSuccess = parkingViewModel.addNewParking(parking);
                    if(isSuccess){
                        makeToast("Parking Added successfully");

                    }else{
                        makeToast("Error occured. Please try again later");
                    }
                }else{
                    makeToast("Error occured. Please fill the fields correctly");

                }

            }
        });
        return view;
    }

    public void makeToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
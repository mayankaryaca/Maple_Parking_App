package com.example.maple;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


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
    private GoogleMap googleMap;
    MapView mMapView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentParkingDetailsBinding.inflate(
                inflater, container, false);
        View view = binding.getRoot();

        mMapView = (MapView) view.findViewById(R.id.mapViewParkingDetails);
        mMapView.onCreate(savedInstanceState);

        this.context = this.getActivity().getApplication().getApplicationContext();

        Bundle bundle = this.getArguments();
        this.parkingViewModel = ParkingViewModel.getInstance(getActivity().getApplication());

        this.locationHelper = LocationHelper.getInstance();
        this.locationHelper.checkPermissions(this.context);

        Parking parkingDetail = (Parking) bundle.getSerializable("PARKING");

        parkingUI(parkingDetail);


        this.binding.btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set mapView
                binding.mapViewParkingDetails.setVisibility(View.VISIBLE);
                Log.d(TAG, "Lat " + parkingDetail.getGeo_location_lat() + " Lng " + parkingDetail.getGeo_location_lng());

                Double lat = Double.valueOf(parkingDetail.getGeo_location_lat());
                Double lng = Double.valueOf(parkingDetail.getGeo_location_lng());

                LatLng latLng = new LatLng(lat,lng);
                loadMapPoint(latLng);
            }
        });
        return view;
    }

    private void parkingUI( Parking parkingDetail) {
        this.binding.tvOutputBuildingCode.setText(parkingDetail.getBuilding_number());
        this.binding.tvOutputApartmentNum.setText(parkingDetail.getApt_number());
        this.binding.tvOutputPlateNum.setText(parkingDetail.getPlate_number());
        this.binding.tvOutputNumOfHours.setText(parkingDetail.getNumber_of_hours());
        this.binding.tvStreet.setText(parkingDetail.getStreet_address());
    }

    private void loadMapPoint(LatLng latLng) {

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(@NonNull GoogleMap mMap) {
                googleMap = mMap;

                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng parkingLocation = new LatLng(latLng.latitude, latLng.longitude);
                googleMap.addMarker(new MarkerOptions().position(parkingLocation).title("Your Car").snippet("Car is parked here!"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(parkingLocation).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }
        });

    }

}
package com.example.maple.Helpers;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.GeoPoint;
import com.google.type.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationHelper {
    private final String TAG = this.getClass().getCanonicalName();
    public boolean locationPermissionGranted = false;
    private LocationRequest locationRequest;
    public final int REQUEST_CODE_LOCATION = 101;
    private FusedLocationProviderClient fusedLocationProviderClient = null;
    MutableLiveData<Location> mLocation = new MutableLiveData<>();
    MutableLiveData<Address> mAddress = new MutableLiveData<>();

    private static final LocationHelper ourInstance = new LocationHelper();

    public static LocationHelper getInstance() {
        return ourInstance;
    }


    private LocationHelper() {
        this.locationRequest = new LocationRequest();
        this.locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        this.locationRequest.setInterval(10000); // 30 seconds
    }

    public void checkPermissions(Context context) {
        this.locationPermissionGranted = (ContextCompat.checkSelfPermission(context.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED);

        Log.d(TAG, "checkPermissions: LocationPermissionGranted : " + this.locationPermissionGranted);

        if (!this.locationPermissionGranted) {
            requestLocationPermission(context);
        }
    }

    public void requestLocationPermission(Context context) {
        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, this.REQUEST_CODE_LOCATION);
    }

    public FusedLocationProviderClient getFusedLocationProviderClient(Context context) {
        if (this.fusedLocationProviderClient == null) {
            this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        }

        return this.fusedLocationProviderClient;
    }

    @SuppressLint("MissingPermission")
    public MutableLiveData<Location> getLocation(Context context) {
        if (this.locationPermissionGranted) {
            try {
                this.getFusedLocationProviderClient(context)
                        .getLastLocation()
                        .addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    mLocation.setValue(location);
                                    Log.d(TAG, "onSuccess: Last Location Obtained --- Lat : " + mLocation.getValue().getLatitude() +
                                            " -- Lng : " + mLocation.getValue().getLongitude());
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(TAG, "onFailure: Couldn't get  location " + e.getLocalizedMessage());
                            }
                        });

            }catch (Exception ex){
                Log.e(TAG, "get Location: Exception " + ex.getLocalizedMessage() );
                return null;
            }

            return this.mLocation;
        }else{
            Log.e(TAG, "get Location: " +
                    "Certain features won't be available as the app does not have access permission for location");
            return null;
        }
    }

    public String getAddress(Context context, Location loc){
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addressList;

        try{
            addressList = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 2);
            Address addressObj = addressList.get(0);
            String fullAddress = addressObj.getFeatureName()+"," + addressObj.getSubAdminArea() +"," +addressObj.getAdminArea();
            return fullAddress;
        }catch(Exception ex){
            Log.e(TAG, "getAddress: Couldn't get the address for given location" + ex.getLocalizedMessage() );
        }

        return null;
    }

    public MutableLiveData<Address> getLocationFromAddress(Context context,String strAddress, String locality, String country){

        Geocoder coder = new Geocoder(context);
        List<Address> address;

        String fetchAddres = strAddress + ","+locality+","+ country;
        try {
            address = coder.getFromLocationName(fetchAddres,2);
            if (address==null) {
                return null;
            }
            mAddress.setValue(address.get(0));

            Log.d(TAG,mAddress.getValue().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mAddress;
    }

}

package com.example.maple.ViewControllers;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import android.app.Application;
import android.util.Log;

import com.example.maple.Models.Parking;
import com.example.maple.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class ParkingViewModel extends AndroidViewModel {
    private static ParkingViewModel ourInstance;
    private final UserRepository userRepository = new UserRepository();
    public MutableLiveData<ArrayList<Parking>> allParkings;
    MapleSharedPreferences mapleSharedPreferences = new MapleSharedPreferences(getApplication().getApplicationContext());



    public ParkingViewModel(Application application) {
        super(application);
        this.userRepository.getAllParkings(mapleSharedPreferences.getUserId());
        this.allParkings = this.userRepository.allParkings;
    }
    public static ParkingViewModel getInstance(Application application){
        if (ourInstance == null){
            ourInstance = new ParkingViewModel(application);
        }
        return ourInstance;
    }

    public boolean addNewParking(Parking newParking){
       boolean isSuccess =  this.userRepository.addParking(newParking);
       return isSuccess;
    }

    public void updateParking(Parking parking){

        String doc_id = parking.getDoc_id();
        this.userRepository.updateParking(parking, doc_id);
    }

    public void deleteparking(String docId){
        this.userRepository.deleteParking(docId);
    }
}

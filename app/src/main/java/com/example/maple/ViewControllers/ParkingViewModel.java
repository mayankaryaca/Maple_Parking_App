package com.example.maple.ViewControllers;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import android.app.Application;

import com.example.maple.Models.Parking;
import com.example.maple.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class ParkingViewModel extends AndroidViewModel {
    private static ParkingViewModel ourInstance;
    private final UserRepository userRepository = new UserRepository();
    public MutableLiveData<ArrayList<Parking>> allParkings;


    public ParkingViewModel(Application application) {
        super(application);
        this.userRepository.getAllParkings();
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

    public void deleteparking(String docId){
        this.userRepository.deleteParking(docId);
    }
}

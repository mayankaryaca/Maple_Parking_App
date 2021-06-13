package com.example.maple.ViewControllers;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.maple.Models.Profile;
import com.example.maple.Models.User;
import com.example.maple.Repositories.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private static UserViewModel ourInstance;
    private final UserRepository userRepository = new UserRepository();
    public MutableLiveData<User> matchedUser;

    public static UserViewModel getInstance(Application application){
        if (ourInstance == null){
            ourInstance = new UserViewModel(application);
        }
        return ourInstance;
    }

    private UserViewModel(Application application) {
        super(application);
    }

    public void addUser(User user){
        this.userRepository.addUser(user);
    }

    public void addProfile(Profile profile){
        this.userRepository.addProfile(profile);
    }

    public void updateProfile(Profile profile){
        this.userRepository.updateProfile(profile);
    }

    public void updateStatus(String userID){
        this.userRepository.updateStatus(userID);
    }

}

package com.example.maple.ViewControllers;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.maple.Models.UserModel;
import com.example.maple.Repositories.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private static UserViewModel ourInstance;
    private final UserRepository userRepository = new UserRepository();
    public MutableLiveData<UserModel> matchedUser;

    public static UserViewModel getInstance(Application application){
        if (ourInstance == null){
            ourInstance = new UserViewModel(application);
        }
        return ourInstance;
    }

    private UserViewModel(Application application) {
        super(application);
    }

    public void addUser(UserModel user){
        this.userRepository.addUser(user);
    }

    public void searchUser(String email){
        this.userRepository.searchUser(email);
        this.matchedUser = this.userRepository.userFromDB;
    }

    public void updateUser(UserModel user){
        this.userRepository.updateUser(user);
    }

    public void deleteUser(UserModel user){
        this.userRepository.deleteUser(user);
    }

}

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
    public MutableLiveData<User> user = new MutableLiveData<>();
    public MutableLiveData<Profile> userProfile = new MutableLiveData<Profile>();


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

    public void getUserProfile(String id){
        this.userRepository.getProfile(id);
        this.userProfile = this.userRepository.userProfile;
    }
    public boolean updateProfile(Profile profile){
        boolean isSuccess = this.userRepository.updateProfile(profile);
        return isSuccess;
    }

    public void updateStatus(String userID){
        this.userRepository.updateStatus(userID);
    }

    public void getUser(String userId){
        this.userRepository.getUser(userId);
        this.user = this.userRepository.userLogin;
    }

    public void updateUserActiveStatus(String doc_id){
        this.userRepository.updateUserAcitve(doc_id);
    }
}

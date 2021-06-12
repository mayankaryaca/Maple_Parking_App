package com.example.maple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.maple.Models.UserModel;
import com.example.maple.ViewControllers.UserViewModel;
import com.example.maple.databinding.ActivitySignUpBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivitySignUpBinding binding;
    private final String TAG = this.getClass().getCanonicalName();
    private UserModel newUser;
    private UserViewModel userViewModel;

    // WOM
    private MutableLiveData<UserModel> userSearch;

    Boolean isValidInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
        setContentView(view);

        this.userViewModel = UserViewModel.getInstance(this.getApplication());

        this.binding.btSignUp.setOnClickListener(this);
        this.binding.etName.setOnClickListener(this);
        this.binding.etEmail.setOnClickListener(this);
        this.binding.etPassword.setOnClickListener(this);
        this.binding.etContact.setOnClickListener(this);
        this.binding.etCarPlate.setOnClickListener(this);

        this.newUser = new UserModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearInputFields();
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            Log.d(TAG, "onClick: Saving the User Info");
            if (validateInput()) {
                Log.d(TAG, "onClick: Validate Input is OK");
                    this.saveDataToDB();
            }
        }
    }

    private Boolean validateInput(){
        if (this.binding.etName.getText().toString().isEmpty()){
            this.binding.etName.setError("Please enter Name");
            isValidInput = false;
        }
        if (this.binding.etEmail.getText().toString().isEmpty()){
            this.binding.etEmail.setError("Please enter Email");
            isValidInput = false;
        }
        String regex = "(.+)@(.+)\\.([a-zA-Z]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.binding.etEmail.getText().toString());
        if (!matcher.matches()) {
            this.binding.etEmail.setError("Please enter Correct Email Format");
            isValidInput = false;
        }

        if (this.binding.etPassword.getText().toString().isEmpty()){
            this.binding.etPassword.setError("Please enter Password");
            isValidInput = false;
        }
        if (this.binding.etConfirmPassword.getText().toString().isEmpty()){
            this.binding.etConfirmPassword.setError("Please confirm Password");
            isValidInput = false;
        }
        String password = this.binding.etPassword.getText().toString();
        String confirmedPassword = this.binding.etConfirmPassword.getText().toString();
        if (!password.equals(confirmedPassword)) {
            this.binding.etPassword.setError("Password doesn't match");
            this.binding.etConfirmPassword.setError("Password doesn't match");
            isValidInput = false;
        }
        if (this.binding.etCarPlate.getText().toString().isEmpty()){
            this.binding.etCarPlate.setError("Please enter Car Plate");
            isValidInput = false;
        }
        if (this.binding.etCarPlate.getText().length() < 2 ||
                this.binding.etCarPlate.getText().length() > 8) {
            this.binding.etCarPlate.setError("Car Plate must have 2-8 characters");
            isValidInput = false;
        }else{
            isValidInput = true;
        }
        Log.d(TAG, "validate - end: " + isValidInput);
        return isValidInput;
    }

    private void clearInputFields() {
        isValidInput = true;
        this.binding.etName.setText("");
        this.binding.etEmail.setText("");
        this.binding.etPassword.setText("");
        this.binding.etConfirmPassword.setText("");
        this.binding.etContact.setText("");
        this.binding.etCarPlate.setText("");
    }

    private void saveDataToDB(){
        this.newUser.setName(this.binding.etName.getText().toString());
        this.newUser.setEmail(this.binding.etEmail.getText().toString());
        this.newUser.setPassword(this.binding.etPassword.getText().toString());
        this.newUser.setContact(this.binding.etContact.getText().toString());
        this.newUser.setCarPlate(this.binding.etCarPlate.getText().toString());
        this.newUser.setActive(true);

        // Verify if user Exist
        this.userViewModel.searchUser(newUser.getEmail());

        this.userViewModel.matchedUser.observe(this, new Observer<UserModel>() {
            @Override
            public void onChanged(UserModel user) {
                // VALIDATIONS:
                // 1. If email exists && active is TRUE -> ALERT User already exists -> Go to Login
                // 2. If email exists && active is FALSE -> ALERT User already exists -> Recovery -> Go to Login
                // 2. If email doesn't exist -> Create NEW Account -> Go to Login
                if (user != null) {
                    Log.d(TAG, "saveDataToDB  - onChanged: matchedUser NOT NULL " + user.toString() );
                    if (user.getActive()) {  // ACTIVE is TRUE
                        Log.d(TAG, "saveDataToDB - Already exists - ACTIVE : " + user.getId() + " " + user.getEmail() + " active " + user.getActive());
                    } else {  // ACTIVE is FALSE
                        Log.d(TAG, "saveDataToDB - Already exists - INACTIVE : " + user.getId() + " " +  user.getEmail() + " active " + user.getActive());
                        updateStatus(user.getId());
                    }
                } else {
                    Log.d(TAG, "saveDataToDB - newUser to Insert : " + newUser.toString());
                    saveUser();
                }
                goToLogin();
            }
        });
    }  //end saveData


    private void updateStatus(String userId){
        // Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        // Option to change password - Go to Update Profile
        this.userViewModel.updateStatus(userId);
        Toast.makeText(this, "Recovering inactive User", Toast.LENGTH_SHORT).show();
    }

    private void saveUser() {
        Log.d(TAG, "saveUser - Go to Login " + newUser.toString() );
        this.userViewModel.addUser(newUser);
        Toast.makeText(this, "User Successfully Created ", Toast.LENGTH_SHORT).show();
    }

    private void goToLogin() {
        Log.d(TAG, "Go Back to Login " );
        Intent intent = new Intent(getApplicationContext(), LoginScreenActivity.class);
        startActivity(intent);
        finish();
    }

}
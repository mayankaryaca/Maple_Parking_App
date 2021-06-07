package com.example.maple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.maple.Models.UserModel;
import com.example.maple.ViewControllers.UserViewModel;
import com.example.maple.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivitySignUpBinding binding;
    private final String TAG = this.getClass().getCanonicalName();
    private UserModel newUser;
    private UserViewModel userViewModel;

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
    public void onClick(View view) {
        if (view != null) {
            Log.d(TAG, "onClick: Saving the User Info");
            if (validateData()) {
                        // save DB
                Log.d(TAG, "onClick: Validate is OK");
                this.saveDataToDB();
            }
        }
    }

    private Boolean validateData(){
        Boolean isValidDate = true;

        if (this.binding.etName.getText().toString().isEmpty()){
            this.binding.etName.setError("Please enter Name");
            isValidDate = false;
        }
        if (this.binding.etEmail.getText().toString().isEmpty()){
            this.binding.etEmail.setError("Please enter Email");
            isValidDate = false;
        }
        if (this.binding.etPassword.getText().toString().isEmpty()){
            this.binding.etPassword.setError("Please enter Email");
            isValidDate = false;
        }
        if (this.binding.etConfirmPassword.getText().toString().isEmpty()){
            this.binding.etConfirmPassword.setError("Please enter Email");
            isValidDate = false;
        }
        if (this.binding.etContact.getText().toString().isEmpty()){
            this.binding.etContact.setError("Please enter Email");
            isValidDate = false;
        }
        if (this.binding.etCarPlate.getText().toString().isEmpty()){
            this.binding.etCarPlate.setError("Please enter Email");
            isValidDate = false;
        }

        return isValidDate;
    }

    private void saveDataToDB(){

        // User exists and is active - "User Already Exists"
        // User exists and is inactive - "Recovery User"
        // User doesn't exist - "Insert"

        this.newUser.setName(this.binding.etName.getText().toString());
        this.newUser.setEmail(this.binding.etEmail.getText().toString());
        this.newUser.setPassword(this.binding.etPassword.getText().toString());
        this.newUser.setContact(this.binding.etContact.getText().toString());
        this.newUser.setCarPlate(this.binding.etCarPlate.getText().toString());
        this.newUser.setActive(true);

        Log.d(TAG, "saveDataToDB - newUser to Insert : " + this.newUser.toString());

        this.userViewModel.addUser(this.newUser);
    }

}
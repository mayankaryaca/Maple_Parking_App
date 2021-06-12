package com.example.maple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.maple.Models.UserModel;
import com.example.maple.ViewControllers.UserViewModel;
import com.example.maple.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivitySignUpBinding binding;
    private final String TAG = this.getClass().getCanonicalName();
    private UserModel newUser;
    private UserViewModel userViewModel;

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
//        if (this.binding.etName.getText().toString().isEmpty() && this.binding.etEmail.getText().toString().isEmpty()) {
//            Toast.makeText(this, "Fields should not be empty", Toast.LENGTH_SHORT).show();
//            Log.e(TAG, "Fields should not be empty");
//            isValidInput = false;
//        }

//  OR
        Log.d(TAG, "validate - begin : " + isValidInput);


        if (this.binding.etName.getText().toString().isEmpty()){
            this.binding.etName.setError("Please enter Name");
            isValidInput = false;
        }
        if (this.binding.etEmail.getText().toString().isEmpty()){
            this.binding.etEmail.setError("Please enter Email");
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
//        if (this.binding.etContact.getText().toString().isEmpty()){
//            this.binding.etContact.setError("Please enter Contact Number");
//            isValidDate = false;
//        }
        if (this.binding.etCarPlate.getText().toString().isEmpty()){
            this.binding.etCarPlate.setError("Please enter Car Plate");
            isValidInput = false;
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

        // User exists and is active - "User Already Exists"
        // User exists and is inactive - "Recovery User"
        // User doesn't exist - "Insert"

        this.newUser.setName(this.binding.etName.getText().toString());
        this.newUser.setEmail(this.binding.etEmail.getText().toString());
        this.newUser.setPassword(this.binding.etPassword.getText().toString());
        this.newUser.setContact(this.binding.etContact.getText().toString());
        this.newUser.setCarPlate(this.binding.etCarPlate.getText().toString());
        this.newUser.setActive(true);

        Log.d(TAG, "saveDataToDB - Search newUser before Insert : " + newUser.getEmail());
        this.userViewModel.searchUser(newUser.getEmail());

        Log.d(TAG, "saveDataToDB - Delete/Update user Active : " + newUser.getActive());
        this.newUser.setActive(false);
        this.userViewModel.deleteUser(newUser);

//        Log.d(TAG, "saveDataToDB - newUser to Insert : " + this.newUser.toString());
//        this.userViewModel.addUser(this.newUser);
    }

}
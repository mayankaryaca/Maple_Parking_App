package com.example.maple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.maple.Models.Profile;
import com.example.maple.Models.User;
import com.example.maple.Repositories.FirebaseAuthenticationController;
import com.example.maple.ViewControllers.UserViewModel;
import com.example.maple.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivitySignUpBinding binding;
    private final String TAG = this.getClass().getCanonicalName();
    private User newUser;
    private Profile newProfile;
    private UserViewModel userViewModel;
<<<<<<< HEAD
    private FirebaseAuthenticationController firebaseAuthenticationController = new FirebaseAuthenticationController();
=======
>>>>>>> 019cb00e19eaafdc1347966e185be9fcfad6c594

    Boolean isValidInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
        setContentView(view);

        this.userViewModel = UserViewModel.getInstance(this.getApplication());
        this.firebaseAuthenticationController.getInstance();

        this.binding.btSignUp.setOnClickListener(this);
        this.binding.etFirstName.setOnClickListener(this);
        this.binding.etLastName.setOnClickListener(this);
        this.binding.etEmail.setOnClickListener(this);
        this.binding.etPassword.setOnClickListener(this);
        this.binding.etContact.setOnClickListener(this);
        this.binding.etCarPlate.setOnClickListener(this);

        this.newUser = new User();
        this.newProfile = new Profile();
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
<<<<<<< HEAD
                    this.saveUser();
=======
                this.saveDataToDB();
>>>>>>> 019cb00e19eaafdc1347966e185be9fcfad6c594
            }
        }
    }

    private Boolean validateInput(){
<<<<<<< HEAD
        if (this.binding.etFirstName.getText().toString().isEmpty()){
            this.binding.etFirstName.setError("Please enter Name");
            isValidInput = false;
        }
        if (this.binding.etLastName.getText().toString().isEmpty()){
            this.binding.etLastName.setError("Please enter Name");
=======
//        if (this.binding.etName.getText().toString().isEmpty() && this.binding.etEmail.getText().toString().isEmpty()) {
//            Toast.makeText(this, "Fields should not be empty", Toast.LENGTH_SHORT).show();
//            Log.e(TAG, "Fields should not be empty");
//            isValidInput = false;
//        }

//  OR
        Log.d(TAG, "validate - begin : " + isValidInput);


        if (this.binding.etName.getText().toString().isEmpty()){
            this.binding.etName.setError("Please enter Name");
>>>>>>> 019cb00e19eaafdc1347966e185be9fcfad6c594
            isValidInput = false;
        }
        if (this.binding.etEmail.getText().toString().isEmpty()){
            this.binding.etEmail.setError("Please enter Email");
            isValidInput = false;
        }
<<<<<<< HEAD
        String regex = "(.+)@(.+)\\.([a-zA-Z]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.binding.etEmail.getText().toString());
        if (!matcher.matches()) {
            this.binding.etEmail.setError("Please enter Correct Email Format");
            isValidInput = false;
        }

        if (this.binding.etPassword.getText().toString().isEmpty() || this.binding.etPassword.getText().toString().length() < 6){
            this.binding.etPassword.setError("Password must have at least 6 charactes");
=======
        if (this.binding.etPassword.getText().toString().isEmpty()){
            this.binding.etPassword.setError("Please enter Password");
>>>>>>> 019cb00e19eaafdc1347966e185be9fcfad6c594
            isValidInput = false;
        }
        if (this.binding.etConfirmPassword.getText().toString().isEmpty() || this.binding.etConfirmPassword.getText().toString().length() < 6) {
            this.binding.etConfirmPassword.setError("Password must have at least 6 charactes");
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
        this.binding.etFirstName.setText("");
        this.binding.etLastName.setText("");
        this.binding.etEmail.setText("");
        this.binding.etPassword.setText("");
        this.binding.etConfirmPassword.setText("");
        this.binding.etContact.setText("");
        this.binding.etCarPlate.setText("");
    }
<<<<<<< HEAD

    private void saveUser(){
        // USER
=======
    private void saveDataToDB(){

        // User exists and is active - "User Already Exists"
        // User exists and is inactive - "Recovery User"
        // User doesn't exist - "Insert"

        this.newUser.setName(this.binding.etName.getText().toString());
>>>>>>> 019cb00e19eaafdc1347966e185be9fcfad6c594
        this.newUser.setEmail(this.binding.etEmail.getText().toString());
        this.newUser.setPassword(this.binding.etPassword.getText().toString());
        this.newUser.setActive(true);

<<<<<<< HEAD
        // PROFILE
        this.newProfile.setFirstName(this.binding.etFirstName.getText().toString());
        this.newProfile.setLastName(this.binding.etLastName.getText().toString());
        this.newProfile.setContact(this.binding.etContact.getText().toString());
        this.newProfile.setCarPlate(this.binding.etCarPlate.getText().toString());

        this.firebaseAuthenticationController.firebaseAuthSignUpUser(this,newUser.getEmail(),newUser.getPassword()).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isCreated) {
                Log.d(TAG,"saveUser - User Auth : " + isCreated + " " + newUser.toString());
                if(isCreated) {
                    String userID = firebaseAuthenticationController.getUserId();
                    newUser.setId(userID);
                    newProfile.setId(userID);
                    Log.d(TAG, "saveUser - newUser to Insert : " + newUser.toString());
                    saveUserDB();
                    goToLogin();
                } else {
                    Log.d(TAG, "saveUser - No Insert - Auth in Firebase failure: " + newUser.toString());
                    noAuthSignUpUser();
                }
            }
        });
    }

    private void updateStatus(String userId){
        // Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        this.userViewModel.updateStatus(userId);
        Toast.makeText(this, "Recovering inactive User", Toast.LENGTH_SHORT).show();
    }

    private void saveUserDB() {
        Log.d(TAG, "saveUser - Go to Login " + newUser.toString() );
        this.userViewModel.addUser(newUser);
        this.userViewModel.addProfile(newProfile);
        Toast.makeText(this, "User Successfully Created ", Toast.LENGTH_SHORT).show();
    }

    private void noAuthSignUpUser() {
        Log.d(TAG, "Auth failure " + newUser.toString() );
        Toast.makeText(this, "Authentication Problem - User Not Created", Toast.LENGTH_SHORT).show();
    }

    private void goToLogin() {
        Log.d(TAG, "Go Back to Login " );
        Intent intent = new Intent(getApplicationContext(), LoginScreenActivity.class);
        //    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
=======
        Log.d(TAG, "saveDataToDB - Search newUser before Insert : " + newUser.getEmail());
        this.userViewModel.searchUser(newUser.getEmail());

        Log.d(TAG, "saveDataToDB - Delete/Update user Active : " + newUser.getActive());
        this.newUser.setActive(false);
        this.userViewModel.deleteUser(newUser);

//        Log.d(TAG, "saveDataToDB - newUser to Insert : " + this.newUser.toString());
//        this.userViewModel.addUser(this.newUser);
>>>>>>> 019cb00e19eaafdc1347966e185be9fcfad6c594
    }

}

package com.example.maple.Repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.maple.Models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private final String TAG = this.getClass().getCanonicalName();
    private final FirebaseFirestore db;
    private final String COLLECTION_NAME = "Users";
    public  MutableLiveData<List<UserModel>> allUsers = new MutableLiveData<List<UserModel>>();
    public MutableLiveData<UserModel> userFromDB = new MutableLiveData<>();

    public UserRepository() {
        db = FirebaseFirestore.getInstance();
    }

    public void addUser(UserModel user) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("name", user.getName());
            data.put("email", user.getEmail());
            data.put("password", user.getName());
            data.put("contact", user.getContact());
            data.put("carPlate", user.getCarPlate());

            db.collection(COLLECTION_NAME)
                    .add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "onSuccess: Document added successfully" + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG, "onFailure: Error creating document on Firestore" + e.getLocalizedMessage());
                        }
                    });

        }catch(Exception ex){
            Log.e(TAG, "Error - addUser: " + ex.getLocalizedMessage() );
        }
    }

    public void searchUser(String email) {
        try {
            db.collection(COLLECTION_NAME)
                    .whereEqualTo("email", email)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                if (task.getResult().getDocuments().size() != 0) {
                                    UserModel matchUser = (UserModel) task.getResult().getDocuments().get(0).toObject(UserModel.class);
                                    matchUser.setId((task.getResult().getDocuments().get(0).getId()));
                                    userFromDB.postValue(matchUser);
                                    Log.d(TAG, "onComplete matched " + matchUser.toString());
                                }else{
                                    Log.e(TAG, "onComplete No User with given email" );
                                }
                            }
                        }
                    });
        }catch (Exception ex) {
            Log.e(TAG, "searchUser(): " + ex.getLocalizedMessage());
        }
    }
    public void updateUser(UserModel user){
        try{
            Map<String, Object> updateInfo = new HashMap<>();
            updateInfo.put("name", user.getName());
            updateInfo.put("email", user.getEmail() );
            updateInfo.put("password", user.getPassword() );
            updateInfo.put("contact", user.getContact() );
            updateInfo.put("carPlate", user.getCarPlate() );

            db.collection(COLLECTION_NAME)
                    .document(user.getId())
                    .update(updateInfo)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "onSuccess: Document updated successfully");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: Unable to update document");
                        }
                    });
        }catch (Exception ex){
            Log.e(TAG, "updateUser: Unable to update document " + ex.getLocalizedMessage() );
        }
    }

    public void deleteUser(UserModel user){
        try{
            Map<String, Object> updateInfo = new HashMap<>();
            updateInfo.put("active", user.getActive());

            db.collection(COLLECTION_NAME)
                    .document(user.getId())
                    .update(updateInfo)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "onSuccess: Document updated(deleted) successfully");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: Unable to update document");
                        }
                    });
        }catch (Exception ex){
            Log.e(TAG, "deleteUser-update: Unable to update document " + ex.getLocalizedMessage() );
        }
    }
}

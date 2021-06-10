package com.example.maple.Repositories;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.maple.Models.Parking;
import com.example.maple.Models.UserModel;
import com.example.maple.ViewControllers.MapleSharedPreferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.model.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private final String TAG = this.getClass().getCanonicalName();
    private final FirebaseFirestore db;
    private final String COLLECTION_USER = "Users";
    private final String COLLECTION_PARKING = "Parkings";
    public MutableLiveData<UserModel> userData = new MutableLiveData<UserModel>();
    public MutableLiveData<ArrayList<Parking>> allParkings = new MutableLiveData<>();


    public UserRepository() {
        db = FirebaseFirestore.getInstance();
    }

    public void addUser(UserModel user) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("name", user.getName());
            data.put("email", user.getEmail());
            data.put("password", user.getPassword());
            data.put("contact", user.getContact());
            data.put("carPlate", user.getCarPlate());
            data.put("active", user.getActive());

            db.collection(COLLECTION_USER)
                    .add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "onSuccess: User document added successfully" + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG, "onFailure: Error creating User document on Firestore" + e.getLocalizedMessage());
                        }
                    });

        }catch(Exception ex){
            Log.e(TAG, "Error - addUser: " + ex.getLocalizedMessage() );
        }
    }

    public void searchUser(String email) {
        Log.d(TAG, "searchUser EMAIL " + email);

        try {
            db.collection(COLLECTION_USER)
                    .whereEqualTo("email", email)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                if (task.getResult().getDocuments().size() != 0) {
                                    UserModel matchUser = (UserModel) task.getResult().getDocuments().get(0).toObject(UserModel.class);
                                    matchUser.setId((task.getResult().getDocuments().get(0).getId()));
                                    userData.postValue(matchUser);
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
            updateInfo.put("email", user.getEmail());
            updateInfo.put("password", user.getPassword());
            updateInfo.put("contact", user.getContact());
            updateInfo.put("carPlate", user.getCarPlate());

            db.collection(COLLECTION_USER)
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

    // Delete - Update active: False
    public void deleteUser(UserModel user){
        Log.d(TAG, " Deleted (Active false)  " + user);

        try{
            Map<String, Object> updateInfo = new HashMap<>();
            updateInfo.put("active", user.getActive());

            Log.d(TAG, " Update to  false " + user.getActive());
            db.collection(COLLECTION_USER)
                    .document(user.getId())
                    .update(updateInfo)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "onSuccess: Document Deleted (Active false) successfully");
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

    boolean addParkingSuccess = false;
    public boolean addParking(Parking newParking){
        try{
            Map<String,Object> newParkingData = new HashMap<>();
            newParkingData.put( "building_number", newParking.getBuilding_number());
            newParkingData.put( "apt_number", newParking.getApt_number());
            newParkingData.put( "plate_number", newParking.getPlate_number());
            newParkingData.put( "number_of_hours", newParking.getNumber_of_hours());
            newParkingData.put( "street_address", newParking.getStreet_address());
            newParkingData.put( "geo_location_lat", newParking.getGeo_location_lat());
            newParkingData.put( "geo_location_lng", newParking.getGeo_location_lng());
            newParkingData.put( "user_id", newParking.getUser_id());


            db.collection(COLLECTION_PARKING).add(newParkingData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d(TAG,"OnAddSuccess : "+ documentReference.getId());
                    addParkingSuccess = true;
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG,"OnAddFailure : "+ e.getLocalizedMessage());

                }
            });
            return addParkingSuccess;

        }catch(Exception e){
            Log.d(TAG,"AddFriendFirebase : " + e.getLocalizedMessage());
            return addParkingSuccess;
        }
    }

    public void getAllParkings(){
        try{
            db.collection(COLLECTION_PARKING).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot value, FirebaseFirestoreException error) {
                    if(error!= null){
                        Log.e(TAG, "OnEvent : Listening to collection failed due to : " + error);
                        return;
                    }

                    ArrayList<Parking> parkingList = new ArrayList<>();
                    if(value.isEmpty()){
                        Log.d(TAG,"Empty or not change in collection" + value);
                    }else{
                        //We have changes in the collection
                        Log.d(TAG,"OnEvent : current data : "+ value);
                        for(DocumentSnapshot document : value.getDocuments()){

                            Parking currentParking = document.toObject(Parking.class);
                            currentParking.setDoc_id(document.getId());
                            parkingList.add(currentParking);

                        }
                    }
                    //here it is telling change to other UI
                    allParkings.postValue(parkingList);
                }

            });
        }catch(Exception e){
            Log.e(TAG,"Retrieve Parkings" + e);
        }

    }

    public void deleteParking(String docId){
        try{
            db.collection(COLLECTION_PARKING).document(docId).delete().addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    Log.e(TAG, " Delete Parking Error" + e.getLocalizedMessage());

                }
            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d(TAG, "Delete Parking Success");

                }
            });
        }catch(Exception e){
            Log.e(TAG, "Error" + e.getLocalizedMessage());
        }
    }
}

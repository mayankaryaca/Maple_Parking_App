package com.example.maple.Repositories;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.maple.Models.Parking;
import com.example.maple.Models.Profile;
import com.example.maple.Models.User;
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
import com.google.firebase.firestore.model.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private final String TAG = this.getClass().getCanonicalName();
    private final FirebaseFirestore db;

    private MapleSharedPreferences mapleSharedPreferences;
    private final String COLLECTION_USER = "Users";
    private final String COLLECTION_PROFILE = "Profiles";
    private final String COLLECTION_PARKING = "Parkings";
    public MutableLiveData<User> userLogin = new MutableLiveData<User>();
    public MutableLiveData<Profile> userProfile = new MutableLiveData<Profile>();
    public MutableLiveData<ArrayList<Parking>> allParkings = new MutableLiveData<>();

    public UserRepository() {
        db = FirebaseFirestore.getInstance();
    }

    public void addUser(User user) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("id", user.getId());
            data.put("email", user.getEmail());
            data.put("password", user.getPassword());
            data.put("active", true);

            db.collection(COLLECTION_USER)
                    .add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "onSuccess: User document added successfully" + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener()  {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG, "onFailure: Error creating User document on Firestore" + e.getLocalizedMessage());
                        }
                    });

        }catch(Exception ex){
            Log.e(TAG, "Error - addUser: " + ex.getLocalizedMessage() );
        }
    }

    public void addProfile(Profile profile) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("id",profile.getId());
            data.put("firstName", profile.getFirstName());
            data.put("lastName", profile.getLastName());
            data.put("contact", profile.getContact());
            data.put("carPlate", profile.getCarPlate());

            db.collection(COLLECTION_PROFILE)
                    .add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "onSuccess: Profile document added successfully" + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG, "onFailure: Error creating User document on Firestore" + e.getLocalizedMessage());
                        }
                    });

        }catch(Exception ex){
            Log.e(TAG, "Error - addProfile: " + ex.getLocalizedMessage() );
        }
    }

    boolean updateSuccess = false;
    public boolean updateProfile(Profile profile){
        try{
            Map<String, Object> updateInfo = new HashMap<>();
            updateInfo.put("firstName", profile.getFirstName());
            updateInfo.put("lastName", profile.getLastName());
            updateInfo.put("contact", profile.getContact());
            updateInfo.put("carPlate", profile.getCarPlate());

            Log.d(TAG,"Profile id "+ profile.getId().toString());
            db.collection(COLLECTION_PROFILE).document(profile.getId()).update(updateInfo)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "onSuccess: Document updated successfully");
                            updateSuccess = true;
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: Unable to update document" + e.getLocalizedMessage());
                        }
                    });
                    return updateSuccess;
        }catch (Exception ex){
            Log.e(TAG, "updateProfile: Unable to update document " + ex.getLocalizedMessage() );
            return updateSuccess;
        }
    }

    public void getProfile(String id){
        try{
            db.collection(COLLECTION_PROFILE).whereEqualTo("id",id).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot value, FirebaseFirestoreException error) {
                    Profile currentProfile = null;
                    if(error!= null){
                        Log.e(TAG, "OnEvent : Listening to collection failed due to : " + error);
                        return;
                    }
                    if(value.isEmpty()){
                        Log.d(TAG,"Empty or not change in collection" + value);
                    }else{
                        //We have changes in the collection
                        Log.d(TAG,"OnEvent : current data : "+ value);
                        for(DocumentSnapshot document : value.getDocuments()){
                            currentProfile = document.toObject(Profile.class);
                            currentProfile.setId(document.getId());
                        }
                    }
                    //here it is telling change to other UI
                    userProfile.postValue(currentProfile);
                }
            });
        }catch(Exception e){
            Log.e(TAG,"Retrieve Parkings" + e);
        }
    }

    public void getUser(String id){
        try{
            db.collection(COLLECTION_USER).whereEqualTo("id",id).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot value, FirebaseFirestoreException error) {
                    User currentUser = null;
                    if(error!= null){
                        Log.e(TAG, "OnEvent : Listening to user failed due to : " + error);
                        return;
                    }
                    if(value.isEmpty()){
                        Log.d(TAG,"Empty or not user in collection" + value);
                    }else{
                        //We have changes in the collection
                        Log.d(TAG,"OnEvent : user data : "+ value);
                        for(DocumentSnapshot document : value.getDocuments()){
                            currentUser = document.toObject(User.class);
                            currentUser.setId(document.getId());
                        }
                    }
                    //here it is telling change to other UI
                    userLogin.postValue(currentUser);
                }
            });
        }catch(Exception e){
            Log.e(TAG,"Retrieve Parkings" + e);
        }
    }
    public void updateUserAcitve(String doc_id){
        try{
            Map<String,Object> newUserData = new HashMap<>();
            newUserData.put( "active", false);

            Log.d(TAG,"Doc id" + doc_id);
            db.collection(COLLECTION_USER).document(doc_id).update(newUserData)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "onSuccess: Document updated successfully");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: Unable to update document" + e.getLocalizedMessage());
                        }
                    });
        }catch (Exception ex){
            Log.e(TAG, "updateProfile: Unable to update document " + ex.getLocalizedMessage() );
        }
    }

    public void updateStatus(String userID){
        Log.d(TAG, " Update (Active false)  " + userID);

        try{
            db.collection(COLLECTION_USER)
                    .document(userID)
                    .update("active", false)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "deleteUser - onSuccess: Document Deleted (Active false) successfully");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "deleteUser - onFailure: Unable to update document");
                        }
                    });
        }catch (Exception ex){
            Log.e(TAG, "deleteUser - update: Unable to update document " + ex.getLocalizedMessage() );
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

    public void getAllParkings(String user_id){
        try{
            db.collection(COLLECTION_PARKING).whereEqualTo("user_id",user_id).addSnapshotListener(new EventListener<QuerySnapshot>() {
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

    public void updateParking(Parking parking, String doc_id){
        try{
            Map<String,Object> newParkingData = new HashMap<>();
            newParkingData.put( "building_number", parking.getBuilding_number());
            newParkingData.put( "apt_number", parking.getApt_number());
            newParkingData.put( "plate_number", parking.getPlate_number());
            newParkingData.put( "number_of_hours", parking.getNumber_of_hours());
            newParkingData.put( "street_address", parking.getStreet_address());
            newParkingData.put( "geo_location_lat", parking.getGeo_location_lat());
            newParkingData.put( "geo_location_lng", parking.getGeo_location_lng());
            newParkingData.put( "user_id", parking.getUser_id());

            Log.d(TAG,"Doc id" + doc_id);
            db.collection(COLLECTION_PARKING).document(doc_id).update(newParkingData)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "onSuccess: Document updated successfully");
                            updateSuccess = true;
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: Unable to update document" + e.getLocalizedMessage());
                        }
                    });
        }catch (Exception ex){
            Log.e(TAG, "updateProfile: Unable to update document " + ex.getLocalizedMessage() );
        }
    }
}
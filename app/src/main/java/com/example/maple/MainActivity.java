package com.example.maple;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.maple.Helpers.LocationHelper;
import com.example.maple.Models.Parking;
import com.example.maple.ViewControllers.ParkingViewModel;
import com.example.maple.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public final String TAG = this.getClass().getCanonicalName();
    private LocationHelper locationHelper;
    private ParkingViewModel parkingViewModel;
    private ArrayList<Parking> parkingList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
        setContentView(view);

        this.locationHelper = LocationHelper.getInstance();
        this.locationHelper.checkPermissions(this);

        this.parkingViewModel = ParkingViewModel.getInstance(this.getApplication());

        Fragment welcomeScreenMaple = new WelcomeScreenMaple();
        loadFragment(welcomeScreenMaple);
//        if(parkingList.isEmpty()){
//            Fragment welcomeScreenMaple = new WelcomeScreenMaple();
//            loadFragment(welcomeScreenMaple);
//        }else{
//            Fragment parkingListFragment = new ParkingListFragment();
//            loadFragment(parkingListFragment);
//        }



        this.binding.fabAddParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "Add Parking clicked");
                Fragment addParkingFragment = new AddParkingFragment();
                loadFragment(addParkingFragment);
            }
        });


        this.binding.bottomNavigationMenu.setBackgroundColor(ContextCompat.getColor(this,R.color.transparent));

        this.binding.bottomNavigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.maple_home:
                        Log.d("TAG", "Home clicked");
                        Fragment parkingListFragment = new ParkingListFragment();
                        loadFragment(parkingListFragment);
                        return true;
                    case R.id.maple_profile:
                        Log.d("TAG", "Profile clicked");
                        Fragment profileFragment = new ProfileFragment();
                        loadFragment(profileFragment);

                        return true;
                }
                return false;
            } });

        this.parkingViewModel.allParkings.observe(this, new Observer<List<Parking>>() {
            @Override
            public void onChanged(List<Parking> parkings) {
                if(!parkings.isEmpty()){
                    Fragment parkingListFragment = new ParkingListFragment();
                    loadFragment(parkingListFragment);
                }
            }
        });
    }



    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
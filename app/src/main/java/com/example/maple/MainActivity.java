package com.example.maple;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.maple.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public final String TAG = this.getClass().getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = this.binding.getRoot();
        setContentView(view);

        Fragment parkingListFragment = new ParkingListFragment();
        loadFragment(parkingListFragment);

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
    }



    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//frame_container is your layout name in xml file
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
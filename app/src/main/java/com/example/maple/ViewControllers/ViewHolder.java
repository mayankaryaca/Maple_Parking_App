package com.example.maple.ViewControllers;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maple.EditParkingDetailsFragment;
import com.example.maple.Models.Parking;
import com.example.maple.ParkingDetailsFragment;
import com.example.maple.R;
import com.example.maple.databinding.ParkingListCellLayoutBinding;

public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    Parking parking;
    Context context;
    ParkingListCellLayoutBinding binding;
    ParkingViewModel parkingViewModel;

    public ViewHolder(ParkingListCellLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        this.binding.btnUpdateParking.setOnClickListener(this);
        this.binding.btnDeleteParking.setOnClickListener(this);
        this.binding.cellView.setOnClickListener(this);

    }

    public void bind(Context context, Parking parking){
        this.context = context;
        this.parking = parking;
        parkingViewModel = ParkingViewModel.getInstance((Application) context.getApplicationContext());

        binding.tvBuildingCode.setText(parking.getBuilding_number());
        binding.tvAddress.setText(parking.getStreet_address());

    }

    @Override
    public void onClick(View v) {
        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        switch(v.getId()){
            case R.id.btnDeleteParking:{
                Log.d("TAG","On Delete clicked " + parking.getDoc_id());
                parkingViewModel.deleteparking(parking.getDoc_id());
                break;
            }
            case R.id.btnUpdateParking:{
                Log.d("TAG","On Update clicked " + parking.getDoc_id());
                EditParkingDetailsFragment myFragment = new EditParkingDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("PARKING",parking);
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, myFragment).addToBackStack(null).commit();

                break;

            }
            case R.id.cellView :{
                ParkingDetailsFragment myFragment = new ParkingDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("PARKING",parking);
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, myFragment).addToBackStack(null).commit();
                break;
            }
        }
    }
}
package com.example.maple.ViewControllers;


import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maple.Models.Parking;
import com.example.maple.ParkingDetailsFragment;
import com.example.maple.R;
import com.example.maple.databinding.ParkingListCellLayoutBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ParkingListRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Parking> parkingList;
    private Context context;

    public ParkingListRecyclerViewAdapter(List<Parking> parkingList,Context context) {
        this.parkingList = parkingList;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ParkingListCellLayoutBinding binding = ParkingListCellLayoutBinding.inflate(LayoutInflater.from(this.context), viewGroup, false);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        if(parkingList != null){
            Parking parking = parkingList.get(position);
            holder.bind(this.context,parking);
        }
    }

    @Override
    public int getItemCount() {
        if(parkingList!=null){
            return parkingList.size();
        }
        return 0;
    }







}

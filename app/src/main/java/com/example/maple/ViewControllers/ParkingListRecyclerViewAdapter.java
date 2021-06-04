package com.example.maple.ViewControllers;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maple.Models.ParkingModel;
import com.example.maple.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ParkingListRecyclerViewAdapter extends RecyclerView.Adapter<ParkingListRecyclerViewAdapter.ViewHolder> {

    private List<ParkingModel> myList;
    @Override
    public ParkingListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.parking_list_cell_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;      }

    @Override
    public void onBindViewHolder(@NonNull ParkingListRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.tvparkingHeading.setText("Test Parking Building Code");
        holder.tvStreetAddress.setText("Test Parking Street, Country");


    }

    @Override
    public int getItemCount() {
        return 30;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvparkingHeading;
        public TextView tvStreetAddress;
        public ViewHolder(View itemView) {
            super(itemView);
            this.tvparkingHeading = (TextView) itemView.findViewById(R.id.tvBuildingCode);
            this.tvStreetAddress = (TextView) itemView.findViewById(R.id.tvAddress);


        }
    }
}

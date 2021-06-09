package com.example.maple.ViewControllers;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maple.Models.Parking;
import com.example.maple.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ParkingListRecyclerViewAdapter extends RecyclerView.Adapter<ParkingListRecyclerViewAdapter.ViewHolder> {

    private List<Parking> parkingList;

    public ParkingListRecyclerViewAdapter(List<Parking> parkingList) {
        this.parkingList = parkingList;
    }

    @Override
    public ParkingListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.parking_list_cell_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;      }

    @Override
    public void onBindViewHolder(@NonNull ParkingListRecyclerViewAdapter.ViewHolder holder, int position) {
        if(parkingList != null){
            Parking item = parkingList.get(position);

            Log.d("TAG",item.toString());
        holder.tvparkingHeading.setText(item.getBuilding_number());
        holder.tvStreetAddress.setText(item.getStreet_address());
        }


    }

    @Override
    public int getItemCount() {
        if(parkingList!=null){
            return parkingList.size();
        }
        return 0;
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

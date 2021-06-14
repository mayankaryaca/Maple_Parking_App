package com.example.maple;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maple.Models.Parking;
import com.example.maple.ViewControllers.ParkingListRecyclerViewAdapter;
import com.example.maple.ViewControllers.ParkingViewModel;
import com.example.maple.databinding.FragmentAddParkingBinding;
import com.example.maple.databinding.FragmentParkingListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ParkingListFragment extends Fragment {

    private ParkingViewModel parkingViewModel;
    private ArrayList<Parking> parkingList = new ArrayList<>();

    FragmentParkingListBinding binding;
    ParkingListRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentParkingListBinding.inflate(
                inflater, container, false);
        View view = binding.getRoot();

        //Recycler View
        adapter = new ParkingListRecyclerViewAdapter(parkingList, binding.getRoot().getContext());
        binding.rvParkingList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.rvParkingList.setHasFixedSize(true);
        binding.rvParkingList.setAdapter(adapter);

        //Recieving parking list data
        this.parkingViewModel = ParkingViewModel.getInstance(getActivity().getApplication());
        recieveParkingData();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recieveParkingData();

    }

    public void recieveParkingData(){
        this.parkingViewModel.allParkings.observe(this.getActivity(), new Observer<List<Parking>>() {
            @Override
            public void onChanged(List<Parking> parkings) {
                parkingList.clear();
                parkingList.addAll(parkings);
                adapter.notifyDataSetChanged();

                if(parkings != null){
                    for(Parking parking : parkings){
                        Log.d("TAG","OnChanged : "+ parking.toString());
                    }
                }
            }
        });
    }

}
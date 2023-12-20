package com.example.googlemapsfinal.ui.home;

import android.annotation.SuppressLint;
import android.health.connect.datatypes.ExerciseRoute;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.googlemapsfinal.databinding.FragmentHomeBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private Location mLastLocation;

    private FusedLocationProviderClient mFusedLocationClient;


    @SuppressLint("MissingPermission")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
        mFusedLocationClient.getLastLocation().addOnSuccessListener(
                location -> {
                    if (location != null) {
                        mLastLocation = location;
                        binding.loc.setText(
                                String.format("Latitud: %1$.4f \n Longitud: %2$.4f\n Hora: %3$tr",
                                        mLastLocation.getLatitude(),
                                        mLastLocation.getLongitude(),
                                        mLastLocation.getTime()));
                    } else {
                        binding.loc.setText("Sense localització coneguda");
                    }
                });

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
        return root;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.ButonLocation.setOnClickListener(v -> {
            binding.textHome.setText("Has Clicado");

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.portunus;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.portunus.databinding.ActivityMapsBinding;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        Bundle extras = getIntent().getExtras();
        if(extras != null){
//            ArrayList<Integer> lats = extras.getIntegerArrayList("latitudes");
//            ArrayList<Integer> longs = extras.getIntegerArrayList("longitudes");

            double[] lats = extras.getDoubleArray("latitudes");
            double[] longs = extras.getDoubleArray("longitudes");

            Log.d("TAG", "lats array length: " + lats.length);

//            LatLng p1 = new LatLng(lats.get(0), longs.get(0));
//            mMap.addMarker(new MarkerOptions().position(p1).title("L1"));

            if(lats.length>0 && longs.length>0) {
                for (int i = 0; i < lats.length; i++) {
                   // Log.d("TAG", "size of lats: " + lats.size());
                    //LatLng point = new LatLng(lats.get(i), longs.get(i));
                    LatLng point = new LatLng(lats[i], longs[i]);
                    mMap.addMarker(new MarkerOptions().position(point).title("location history"));
                }
            }
        }


    }
}
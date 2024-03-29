package com.example.portunus;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.portunus.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private ArrayList<Double> latitudes, longitudes;
    private ArrayList<LatLng> pointList;
    Button refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference locationRef = database.getReference("Location");

        latitudes = new ArrayList<>();
        longitudes = new ArrayList<>();
        pointList = new ArrayList<>();


        locationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot lat_read = dataSnapshot.child("Live-Location").child("Latitude");
                DataSnapshot long_read = dataSnapshot.child("Live-Location").child("Longitude");
                Double lat_value = lat_read.getValue(Double.class);
                Double long_value = long_read.getValue(Double.class);
                Log.d("newlatval", String.valueOf(lat_value));
                Log.d("newlongval", String.valueOf(long_value));

//                latitudes.add(lat_value);
//                longitudes.add(long_value);
                latitudes.add(lat_read.getValue(Double.class));
                longitudes.add(long_read.getValue(Double.class));

                LatLng newPoint = new LatLng(lat_value, long_value);
                pointList.add(newPoint);

                Log.d("pointMap", String.valueOf(pointList));
                Log.d("pointList", String.valueOf(pointList.size()));
                Log.d("latList", String.valueOf(latitudes.size()));
                Log.d("longList", String.valueOf(longitudes.size()));

                //mMap.addMarker(new MarkerOptions().position(newPoint).title("New Point"));
                //mMap.animateCamera(CameraUpdateFactory.zoomTo(21.0f));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(newPoint));
                Polyline path = mMap.addPolyline(new PolylineOptions().clickable(true).addAll(pointList));
                //path.setColor(225); //Blue ARGB code for Polyline


                //Polyline path = mMap.addPolyline(new PolylineOptions().clickable(true).add());

                //path.setColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
//                for(int i = 0; i<pointList.size();i++){
//                    mMap.addMarker(new MarkerOptions().position(pointList.get(i)).title("Point List"));
//                    Log.d("addedpoint", String.valueOf(pointList.get(i)));
//                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        refreshButton = findViewById(R.id.refreshButton);
//        refreshButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Polyline path = mMap.addPolyline(new PolylineOptions().clickable(true).addAll(pointList));
////                for(int i = 0; i<pointList.size();i++){
////                    mMap.addMarker(new MarkerOptions().position(pointList.get(i)).title("Point List"));
////                    Log.d("addedpoint", String.valueOf(pointList.get(i)));
////                }
//            }
//        });

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

        LatLng islavista = new LatLng(34.409721, -119.856949);
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(islavista, 15f));
        mMap.addMarker(new MarkerOptions().position(islavista).title("Isla Vista"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(islavista));

        Polyline path = mMap.addPolyline(new PolylineOptions().clickable(true).add(
           new LatLng(34.4162023, -119.8481601),
           new LatLng(34.4171649, -119.8481456), new LatLng(34.4180453, -119.8482244),
                new LatLng(34.4187233, -119.8481735), new LatLng(34.4190832, -119.8476711),
                new LatLng(34.4187550, -119.8470312), new LatLng(34.4184955, -119.8463328),
                new LatLng(34.4181236, -119.8460418), new LatLng(34.4179274, -119.8457387),
                new LatLng(34.4176843, -119.8451970), new LatLng(34.4174194, -119.8445645),
                new LatLng(34.4173289, -119.8441240), new LatLng(34.4172136, -119.8435577),
                new LatLng(34.4167639, -119.8432440), new LatLng(34.4167639, -119.8432440),
                new LatLng(34.4164151, -119.8425680), new LatLng(34.4161200, -119.8421623),
                new LatLng(34.4159485, -119.8413908), new LatLng(34.4163266, -119.8409513)
        ));

        mMap.addMarker(new MarkerOptions().position(new LatLng(34.4162023, -119.8481601)).title("Isla Vista"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(34.4163266, -119.8409513)).title("Isla Vista"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(34.4173289, -119.8441240)).title("Isla Vista"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(34.4167639, -119.8432440)).title("Isla Vista"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(34.4174194, -119.8445645)).title("Isla Vista"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(34.4187233, -119.8481735)).title("Isla Vista"));


//        Polyline dummy  = mMap.addPolyline(new PolylineOptions().clickable(true).add(
//                new LatLng(34.409721, -119.856949),
//                new LatLng(34.41914115994636, -119.86784274965484),
//                new LatLng(34.430044242571, -119.86775691897405),
//                new LatLng(34.43436260288937, -119.84423931244189),
//                new LatLng(34.437972864025966, -119.83007725011412),
//                new LatLng(34.43761892375195, -119.80441387656256),
//                new LatLng(34.43924703678484, -119.7906809638184),
//                new LatLng(34.4408043324319, -119.74553402573105)));
        refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Polyline path = mMap.addPolyline(new PolylineOptions().clickable(true).addAll(pointList));
                for(int i = 0; i<pointList.size();i++){
                    mMap.addMarker(new MarkerOptions().position(pointList.get(i)).title("Point List"));
                    Log.d("addedpoint", String.valueOf(pointList.get(i)));
                }
            }
        });


//        for(int i = 0; i<pointList.size();i++){
//            mMap.addMarker(new MarkerOptions().position(pointList.get(i)).title("Point List"));
//            Log.d("addedpoint", String.valueOf(pointList.get(i)));
//        }


        //Polyline path = mMap.addPolyline(new PolylineOptions().clickable(true).addAll(pointList));

//        for(int i = 0; i<pointList.size();i++){
//            mMap.addMarker(new MarkerOptions().position(pointList.get(i)).title("Point List"));
//        }

//        Bundle extras = getIntent().getExtras();
//        if(extras != null){
////            ArrayList<Integer> lats = extras.getIntegerArrayList("latitudes");
////            ArrayList<Integer> longs = extras.getIntegerArrayList("longitudes");
//
//            double[] lats = extras.getDoubleArray("latitudes");
//            double[] longs = extras.getDoubleArray("longitudes");
//
//            //Log.d("TAG", "lats array length: " + lats.length);
//
////            LatLng p1 = new LatLng(lats.get(0), longs.get(0));
////            mMap.addMarker(new MarkerOptions().position(p1).title("L1"));
//
//            if(lats.length>0 && longs.length>0) {
//                for (int i = 0; i < lats.length; i++) {
//                   // Log.d("TAG", "size of lats: " + lats.size());
//                    //LatLng point = new LatLng(lats.get(i), longs.get(i));
//                    LatLng point = new LatLng(lats[i], longs[i]);
//                    mMap.addMarker(new MarkerOptions().position(point).title("location history"));
//                }
//            }
//        }


    }
}
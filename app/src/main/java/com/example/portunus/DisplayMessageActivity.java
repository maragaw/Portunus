package com.example.portunus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;

public class DisplayMessageActivity extends AppCompatActivity {


    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Toast.makeText(this, "MapView Activity", Toast.LENGTH_LONG).show();
        mapView = findViewById(R.id.mapView);


    }
}
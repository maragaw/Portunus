package com.example.portunus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.*;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //lists out GPS data linearly on UI
    private ListView listView;

    //field for uploading data back to Firebase
    TextInputLayout locationData;
    private EditText editText;
    //EditText editText = (EditText)findViewById(R.id.EditText);
    Button uploadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        EditText editText = (EditText)findViewById(R.id.EditText);

        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<Pair<Pair<String, Double>, Pair<String, Double>>> pairArrayList = new ArrayList<>();




        Toast.makeText(this, "Firebase connection successful", Toast.LENGTH_LONG).show();




        //write message to firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Location");
//        myRef.setValue("Some GPS Data");

        ArrayList<Pair<Double, Double>> firebaseRead = new ArrayList<>();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,firebaseRead);
        listView.setAdapter(arrayAdapter);



        //eventListener for upload button, uploads data in text field to firebase
        //in the text field, input two double values separated by comma (lat, long)
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                //String value = myRef.child("Live-Location").child("Latitude");
                DataSnapshot lat_read = dataSnapshot.child("Live-Location").child("Latitude");
                DataSnapshot long_read = dataSnapshot.child("Live-Location").child("Longitude");
                Double lat_value = lat_read.getValue(Double.class);
                Double long_value = long_read.getValue(Double.class);
               // Log.d(TAG, "Value is: " + value)
                Pair<Double, Double> coordinates = new Pair<>(lat_value, long_value);
                firebaseRead.add(coordinates);
                arrayAdapter.notifyDataSetChanged();


//                for(int i = 0; i<firebaseRead.size(); i++){
////            latitudes[i] = (firebaseRead.get(i).first);
////            longitudes[i] = (firebaseRead.get(i).second);
//
//                    int lat = (int)Math.round(firebaseRead.get(i).first);
//                    int lng = (int)Math.round(firebaseRead.get(i).second);
//                    latitudes.add(lat);
//                    longitudes.add(lng);
//
//                }

            }


            @Override
            public void onCancelled(DatabaseError error) {
                //failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


       // double[] latitudes = new double[100];
        //double[] longitudes = new double[100];



        uploadButton = findViewById(R.id.UploadButton);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){


                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
//                intent.putExtra("latitudes", latitudes);
//                intent.putExtra("longitudes", longitudes);
                startActivity(intent);
            }
        });




    }

    public void readFireBase() {

    }



}
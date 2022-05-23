package com.example.portunus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DiagnosticActivity extends AppCompatActivity {

    private ListView rpmListView, prndlListView, lightListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic);

        //set dynamic vertical arraylist for RPM entries
        rpmListView = findViewById(R.id.rpmListView);
        ArrayList<Integer> rpmList = new ArrayList<>();
        ArrayAdapter rpmAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, rpmList);
        rpmListView.setAdapter(rpmAdapter);

        //set for PRNDL entries
        prndlListView = findViewById(R.id.prndlListView);
        ArrayList<String> prndlList = new ArrayList<>();
        ArrayAdapter prndlAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, prndlList);
        prndlListView.setAdapter(prndlAdapter);

        //set for Light entries
        lightListView = findViewById(R.id.lightListView);
        ArrayList<String> lightList = new ArrayList<>();
        ArrayAdapter lightAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lightList);
        lightListView.setAdapter(lightAdapter);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference carRef = database.getReference("Car");

        carRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot light = dataSnapshot.child("light");
                DataSnapshot prndl = dataSnapshot.child("prndl");
                DataSnapshot rpm = dataSnapshot.child("rpm");

                String lightvalue = light.getValue(String.class);
                String prndlvalue = prndl.getValue(String.class);
                Integer rpmvalue = rpm.getValue(Integer.class);

                rpmList.add(rpmvalue);
                prndlList.add(prndlvalue);
                lightList.add(lightvalue);

                rpmAdapter.notifyDataSetChanged();
                prndlAdapter.notifyDataSetChanged();
                lightAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        //dummy values
//        rpmList.add(4000);
//        rpmAdapter.notifyDataSetChanged();
//
//        prndlList.add("P");
//        prndlAdapter.notifyDataSetChanged();
//
//        lightList.add("On");
//        lightAdapter.notifyDataSetChanged();


    }



}
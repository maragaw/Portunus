package com.example.portunus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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


        //dummy values
        rpmList.add(4000);
        rpmAdapter.notifyDataSetChanged();

        prndlList.add("P");
        prndlAdapter.notifyDataSetChanged();

        lightList.add("On");
        lightAdapter.notifyDataSetChanged();


    }



}
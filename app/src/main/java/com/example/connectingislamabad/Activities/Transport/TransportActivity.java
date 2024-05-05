package com.example.connectingislamabad.Activities.Transport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Adapter;


import com.example.connectingislamabad.Activities.Category.CategoryActivity;
import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.Activities.Setting.SettingActivity;
import com.example.connectingislamabad.Adapters.TransportAdapter;
import com.example.connectingislamabad.Domains.TransportDomain;
import com.example.connectingislamabad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class TransportActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterTransport ;
    private RecyclerView recyclerViewTransport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        initRecyclerView();

        // Navigation Bar Controller
        BottomNavigationView bottomNavigationView = findViewById(R.id.buttomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.transport_bottom);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

                @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home_bottom) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.category_bottom) {
                    // Handle navigation to category
                    // Start the CategoryActivity
                    startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.transport_bottom) {
                    // Handle navigation to transport
                    // Start the TransportActivity

                    return true;
                } else if (item.getItemId() == R.id.setting_bottom) {
                    // Handle navigation to settings
                    // Start the SettingsActivity
                    startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private void initRecyclerView() {
        // For Trasport Data
        ArrayList<TransportDomain> transportList = new ArrayList<>();

        transportList.add(new TransportDomain("Green Line",     "hey thereh","cat1", "route_1"));
        transportList.add(new TransportDomain("Orange Line",    "hey thereh","cat1", "route_1"));
        transportList.add(new TransportDomain("Local Transport","hey thereh","cat1", "route_1"));
        transportList.add(new TransportDomain("Metro",          "hey thereh","cat1", "route_1"));
        transportList.add(new TransportDomain("Booking Application",   "hey thereh","cat1", "route_1"));

        recyclerViewTransport = findViewById(R.id.view_transport);

        recyclerViewTransport.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterTransport = new TransportAdapter(transportList);
        recyclerViewTransport.setAdapter(adapterTransport);
    }
}
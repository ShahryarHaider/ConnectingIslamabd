package com.example.connectingislamabad.Activities.Category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.Activities.Setting.SettingActivity;
import com.example.connectingislamabad.Adapters.FoodCatAdapter;
import com.example.connectingislamabad.Adapters.MuseumCatAdapter;
import com.example.connectingislamabad.Domains.FoodCatDomain;
import com.example.connectingislamabad.Domains.MuseumCatDomain;
import com.example.connectingislamabad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MuseumCatActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterMuseumCat ;
    private RecyclerView recyclerViewMuseumCat;
    private ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_cat);

        initRecyclerView();


        // Initialize the back button
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Navigation Bar Controller
        BottomNavigationView bottomNavigationView = findViewById(R.id.buttomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.category_bottom);

        //Navigation bottom Bar
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

        ArrayList<MuseumCatDomain> museumcatList = new ArrayList<>();

        museumcatList.add(new MuseumCatDomain("The Islamabad Museum","museum_isb","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        museumcatList.add(new MuseumCatDomain("Supreme Court","museum_supreme","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        museumcatList.add(new MuseumCatDomain("Pakistan Museum of Natural Histroy","museum_natual","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        museumcatList.add(new MuseumCatDomain("Pakistan Monument Museum","museum_monument","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        museumcatList.add(new MuseumCatDomain("National Insect Museum","museum_insect","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        museumcatList.add(new MuseumCatDomain("Lok Virsa","museum_lokvirsa","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));


        recyclerViewMuseumCat = findViewById(R.id.view_museum_cat);
        recyclerViewMuseumCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterMuseumCat = new MuseumCatAdapter(museumcatList);
        recyclerViewMuseumCat.setAdapter(adapterMuseumCat);
    }
}
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
import com.example.connectingislamabad.Adapters.MuseumCatAdapter;
import com.example.connectingislamabad.Domains.MuseumCatDomain;
import com.example.connectingislamabad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MarketCatActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterMuseumCat ;
    private RecyclerView recyclerViewMarketCat;
    private ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_cat);

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

        ArrayList<MuseumCatDomain> marketcatList = new ArrayList<>();

        marketcatList.add(new MuseumCatDomain("Centaurus Mall","market_1","ISB","4.5","Top Rated","Shopping","Top Place","F9",
                "Experience unparalleled shopping, dining, and entertainment at The Centaurus Mall, Islamabad's premier destination.",
                "https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        marketcatList.add(new MuseumCatDomain("The Safa Gold Mall","market_2","ss","4.5","Top Rated","Shopping","Top Place","F9",
                "The Safa Gold Mall is a unique experience of shopping and entertainment with the class of environment you would love to live with, located in the hub of Islamabad’s most sophisticated area.",
                "https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        marketcatList.add(new MuseumCatDomain("Aabpara Market","market_3","ss","4.5","Top Rated","Shopping","Top Place","F9",
                "One of the oldest and most comprehensive markets in Islamabad. It hosts a great variety of shops selling dry fruit, toys, consumer goods, kitchen utensils, sports and leather goods, school uniforms and everything that is needed to keep a household running smoothly. Located close to the centre of the city, Aabpara market is the place where everything is available for all and sundry at a very reasonable and affordable price.",
                "https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));



        recyclerViewMarketCat = findViewById(R.id.view_market_cat);
        recyclerViewMarketCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterMuseumCat = new MuseumCatAdapter(marketcatList);
        recyclerViewMarketCat.setAdapter(adapterMuseumCat);
    }
}
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

        marketcatList.add(new MuseumCatDomain("The Islamabad Museum","route_1","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        marketcatList.add(new MuseumCatDomain("Supreme Court","route_1","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        marketcatList.add(new MuseumCatDomain("Pakistan Museum of Natural Histroy","route_1","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        marketcatList.add(new MuseumCatDomain("Pakistan Monument Museum","route_1","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        marketcatList.add(new MuseumCatDomain("National Insect Museum","route_1","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        marketcatList.add(new MuseumCatDomain("Lok Virsa","route_1","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));


        recyclerViewMarketCat = findViewById(R.id.view_market_cat);
        recyclerViewMarketCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterMuseumCat = new MuseumCatAdapter(marketcatList);
        recyclerViewMarketCat.setAdapter(adapterMuseumCat);
    }
}
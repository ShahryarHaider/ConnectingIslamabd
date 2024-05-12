package com.example.connectingislamabad.Activities.Category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.Activities.Setting.SettingActivity;
import com.example.connectingislamabad.Adapters.MuseumCatAdapter;
import com.example.connectingislamabad.Domains.MuseumCatDomain;
import com.example.connectingislamabad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MustvisitCatActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterMustvisitCat ;
    private RecyclerView recyclerViewMustvisitCat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mustvisit_cat);
        initRecyclerView();

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

        ArrayList<MuseumCatDomain> mustvisitcatList = new ArrayList<>();

        mustvisitcatList.add(new MuseumCatDomain("Faisal Masjid","route_1","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        mustvisitcatList.add(new MuseumCatDomain("F9 Park","route_1","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        mustvisitcatList.add(new MuseumCatDomain("Sd","route_1","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        mustvisitcatList.add(new MuseumCatDomain("Pakistan Monument Museum","route_1","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        mustvisitcatList.add(new MuseumCatDomain("National Insect Museum","route_1","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));
        mustvisitcatList.add(new MuseumCatDomain("Lok Virsa","route_1","ss","ss","ss","ss","ss","sx","se","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));


        recyclerViewMustvisitCat = findViewById(R.id.view_mustvisit_cat);
        recyclerViewMustvisitCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterMustvisitCat = new MuseumCatAdapter(mustvisitcatList);
        recyclerViewMustvisitCat.setAdapter(adapterMustvisitCat);
    }
}
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
import com.example.connectingislamabad.Adapters.FoodCatAdapter;
import com.example.connectingislamabad.Domains.FoodCatDomain;
import com.example.connectingislamabad.Domains.TransportDomain;
import com.example.connectingislamabad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FoodCatActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterFoodCat ;
    private RecyclerView recyclerViewFoodCat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_cat);

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

        ArrayList<FoodCatDomain> foodcatList = new ArrayList<>();

        //Add Data Into FoodCat Related
        foodcatList.add(new FoodCatDomain("Cheezious",
                "food_cheezious", "H8", "4.7",
                "Dine In", "Fast Food", "No",
                "Fastly Growing Fast Food Brand In Pakistan",
                "food_pic1","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));

        foodcatList.add(new FoodCatDomain("Savour",
                "food_savour", "H8", "47",
                "Dine In", "Fast Food", "No",
                "Fastly Growing Fast Food Brand In Pakistan",
                "food_pic1","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8"));

        foodcatList.add(new FoodCatDomain("Ranchers",
                "food_ranchers", "H8", "4.7",
                "Dine In", "Fast Food", "No",
                "Fastly Growing Fast Food Brand In Pakistan",
                "food_pic1","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8\n"));

        foodcatList.add(new FoodCatDomain("TKR",
                "food_tkr", "H8", "4.7",
                "Dine In", "Fast Food", "No",
                "Fastly Growing Fast Food Brand In Pakistan. Fastly Growing Fast Food Brand In Pakistan" +
                        "Fastly Growing Fast Food Brand In Pakistan" +
                        "Fastly Growing Fast Food Brand In Pakistan" +
                        "Fastly Growing Fast Food Brand In Pakistan" +
                        "Fastly Growing Fast Food Brand In Pakistan ",
                "food_pic1","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8\n"));

        foodcatList.add(new FoodCatDomain("Malang Jan",
                "food_malang", "H8", "4.7",
                "Dine In", "Fast Food", "No",
                "Fastly Growing Fast Food Brand In Pakistan",
                "food_pic1",
                "https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8\n"));

        foodcatList.add(new FoodCatDomain("Ox And Grill House",
                "food_cheezious", "H8", "4.7",
                "Dine In", "Fast Food", "No",
                "Fastly Growing Fast Food Brand In Pakistan",
                "food_pic1",
                "https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8\n"));


        recyclerViewFoodCat = findViewById(R.id.view_food_cat);
        recyclerViewFoodCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterFoodCat = new FoodCatAdapter(foodcatList);
        recyclerViewFoodCat.setAdapter(adapterFoodCat);
    }
}
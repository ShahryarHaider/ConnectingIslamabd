package com.example.connectingislamabad.Activities.Category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.Activities.Setting.SettingActivity;
import com.example.connectingislamabad.Adapters.FoodCatAdapter;
import com.example.connectingislamabad.Adapters.ReviewAdapter;
import com.example.connectingislamabad.Domains.FoodCatDomain;
import com.example.connectingislamabad.Domains.TransportDomain;
import com.example.connectingislamabad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FoodCatActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterFoodCat ;
    private RecyclerView recyclerViewFoodCat;
    private ImageView backBtn;

    private Button directionBtn, ratingBtn;

    private FirebaseFirestore db;

    private SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_cat);


        initRecyclerView();

        // Initialize the back button
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ((FoodCatAdapter) adapterFoodCat).filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((FoodCatAdapter) adapterFoodCat).filter(newText);
                return false;
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

        db = FirebaseFirestore.getInstance();






        db.collection("food").document("1Data").get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {

                            ArrayList<FoodCatDomain> foodcatList = new ArrayList<>();

                            // Fetch total reviews count from counter field
                            Map<String, Object> data = document.getData();
                            List<Map<String, Object>> allData = new ArrayList<>();

                            Map<String, Object> cheezious = (Map<String, Object>) data.get("cheezious");
                            Map<String, Object> malangJan = (Map<String, Object>) data.get("malangJan");
                            Map<String, Object> oxAndGrill = (Map<String, Object>) data.get("oxAndGrill");
                            Map<String, Object> ranchers = (Map<String, Object>) data.get("ranchers");
                            Map<String, Object> savour = (Map<String, Object>) data.get("savour");
                            Map<String, Object> tkr = (Map<String, Object>) data.get("tkr");

                            allData.add(cheezious);
                            allData.add(malangJan);
                            allData.add(oxAndGrill);
                            allData.add(ranchers);
                            allData.add(savour);
                            allData.add(tkr);

                            for(int i = 0; i < allData.size(); i++)
                            {
                                String titleTxt = (String) allData.get(i).get("titleTxt");
                                String picImg = (String) allData.get(i).get("picImg");
                                String locationTxt = (String) allData.get(i).get("locationTxt");
                                String ratingTxt = (String) allData.get(i).get("ratingTxt");
                                String dineTxt = (String) allData.get(i).get("dineTxt");
                                String typeTxt = (String) allData.get(i).get("typeTxt");
                                String wifiTxt = (String) allData.get(i).get("wifiTxt");
                                String descriptionTxt = (String) allData.get(i).get("descriptionTxt");
                                String collection_name = (String) allData.get(i).get("collection_name");
                                String document_name = (String) allData.get(i).get("document_name");
                                Object latitudeObj = allData.get(i).get("latitude");
                                Object longitudeObj = allData.get(i).get("longitude");
                                List<String> type = (List<String>) allData.get(i).get("type");

                                double latitude = 0.0;
                                double longitude = 0.0;

                                if (latitudeObj instanceof Long) {
                                    latitude = ((Long) latitudeObj).doubleValue();
                                } else if (latitudeObj instanceof Double) {
                                    latitude = (Double) latitudeObj;
                                }

                                if (longitudeObj instanceof Long) {
                                    longitude = ((Long) longitudeObj).doubleValue();
                                } else if (longitudeObj instanceof Double) {
                                    longitude = (Double) longitudeObj;
                                }
                                System.out.println(latitude);

                                String direction_btn = (String) allData.get(i).get("direction_btn");


                                foodcatList.add(new FoodCatDomain(titleTxt,
                                        picImg, locationTxt, ratingTxt,
                                        dineTxt, typeTxt, wifiTxt,
                                        descriptionTxt,
                                        "food_pic1",direction_btn, document_name, latitude, longitude, collection_name, type));
                            }

                            recyclerViewFoodCat = findViewById(R.id.view_food_cat);
                            recyclerViewFoodCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

                            adapterFoodCat = new FoodCatAdapter(foodcatList);
                            recyclerViewFoodCat.setAdapter(adapterFoodCat);

                        } else {
                            Log.d("Rating", "No such document");
                        }
                    } else {
                        Log.d("Rating", "get failed with ", task.getException());
                    }
                });






        //Add Data Into FoodCat Related
//        foodcatList.add(new FoodCatDomain(allData.get(0).get("titleTxt").toString(),
//                "food_cheezious", "F7", "4.7",
//                "Dine In", "Fast Food", "No",
//                "Description: Cheezious is a popular eatery known for its mouthwatering pizzas, burgers, and fried chicken. Their thin-crust pizzas are a must-try, and their Crown Crust Pizza is a favorite among locals.\n" +
//                        "Famous For: Thin-crust pizzas, juicy burgers, and crispy fried chicken.\n" +
//                        "Locations: You can find Cheezious at Giga Mall in DHA Phase 2 and Bhitai Road in F-7 Markaz",
//                "food_pic1","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8", "cheezious", 1, 1, "food"));
//
//        foodcatList.add(new FoodCatDomain("Savour",
//                "food_savour", "Blue A", "47",
//                "Dine In", "Fast Food", "No",
//                "Description: Savour Foods is a beloved spot for traditional Pakistani pulao. Their flavorful rice dishes, including Bannu Beef Pulao, are a hit among locals.\n" +
//                        "Famous For: Authentic pulao, kebabs, and rice-based dishes.\n" +
//                        "Locations: Savour Foods has branches on Jinnah Avenue and in the Blue Area",
//                "food_pic1","https://maps.app.goo.gl/xFDd4sfpfGUUwBiR9", "savour", 1, 1, "food"));
//
//        foodcatList.add(new FoodCatDomain("Ranchers",
//                "food_ranchers", "I8", "4.7",
//                "Dine In", "Fast Food", "No",
//                "Description: Ranchers Café offers a flavorful experience with its mighty burgers, both chicken and beef. Their menu includes a variety of options, from Bronco Chicken Burgers to Texas Jack Beef Burgers.\n" +
//                        "Famous For: Mighty burgers, thin-crust pizzas, and loaded fries.\n" +
//                        "Locations: Ranchers has branches in I-8 Markaz and Blue Area",
//                "food_pic1","https://maps.app.goo.gl/cGUmaBJ9cB8SVoYH7", "ranchers", 1, 1, "food"));
//
//        foodcatList.add(new FoodCatDomain("TKR",
//                "food_tkr", "F7", "4.7",
//                "Dine In", "Fast Food", "No",
//                "Description: TKR is known for its diverse menu, offering everything from desi to continental dishes. Their biryanis and karahi are particularly popular.\n" +
//                        "Famous For: Flavorful biryanis, karahi, and fusion cuisine.\n" +
//                        "Locations: TKR has branches in F-7 Markaz and other areas.",
//                "food_pic1","https://maps.app.goo.gl/oBwtikxxgAQs66Ri9", "tkr", 1, 1, "food"));
//
//        foodcatList.add(new FoodCatDomain("Malang Jan",
//                "food_malang", "G-9", "4.7",
//                "Dine In", "Fast Food", "No",
//                "Description: Malang Jan offers authentic Afghan cuisine in a cozy setting. From kebabs to soups, their menu features a variety of Afghan delights.\n" +
//                        "Famous For: Afghan kebabs, rice dishes, and flavorful soups.\n" +
//                        "Locations: You can find Malang Jan in G-9 Markaz and other locations",
//                "food_pic1",
//                "https://maps.app.goo.gl/zoZD1EDb1HxtH9JM6", "malangJan", 1, 1, "food"));
//
//        foodcatList.add(new FoodCatDomain("Ox And Grill House",
//                "food_ox", "F7", "4.7",
//                "Dine In", "Fast Food", "No",
//                "Description: OX & Grill is a steakhouse that serves juicy steaks, grilled meats, and seafood. Their cozy ambiance and quality cuts make it a favorite for meat lovers.\n" +
//                        "Famous For: Steaks, grilled dishes, and seafood.\n" +
//                        "Locations: OX & Grill is located in F-7 Markaz",
//                "food_ox",
//                "https://maps.app.goo.gl/vZScLsoEece6tffd6", "oxAndGrill", 1, 1, "food"));

//        foodcatList.add(new FoodCatDomain("Test",
//                "food_ox", "F7", "4.7",
//                "Dine In", "Fast Food", "No",
//                "Description: Test is a steakhouse that serves juicy steaks, grilled meats, and seafood. Their cozy ambiance and quality cuts make it a favorite for meat lovers.\n" +
//                        "Famous For: Steaks, grilled dishes, and seafood.\n" +
//                        "Locations: OX & Grill is located in F-7 Markaz",
//                "food_ox",
//                "https://maps.app.goo.gl/vZScLsoEece6tffd6"));
//        foodcatList.add(new FoodCatDomain("Cheezious",
//                "food_cheezious", "F7", "4.7",
//                "Dine In", "Fast Food", "No",
//                "Description: Cheezious is a popular eatery known for its mouthwatering pizzas, burgers, and fried chicken. Their thin-crust pizzas are a must-try, and their Crown Crust Pizza is a favorite among locals.\n" +
//                        "Famous For: Thin-crust pizzas, juicy burgers, and crispy fried chicken.\n" +
//                        "Locations: You can find Cheezious at Giga Mall in DHA Phase 2 and Bhitai Road in F-7 Markaz",
//                "food_pic1","https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8", "cheezious", 1, 1, "food"));
//
//        foodcatList.add(new FoodCatDomain("Savour",
//                "food_savour", "Blue A", "47",
//                "Dine In", "Fast Food", "No",
//                "Description: Savour Foods is a beloved spot for traditional Pakistani pulao. Their flavorful rice dishes, including Bannu Beef Pulao, are a hit among locals.\n" +
//                        "Famous For: Authentic pulao, kebabs, and rice-based dishes.\n" +
//                        "Locations: Savour Foods has branches on Jinnah Avenue and in the Blue Area",
//                "food_pic1","https://maps.app.goo.gl/xFDd4sfpfGUUwBiR9", "savour", 1, 1, "food"));
//
//        foodcatList.add(new FoodCatDomain("Ranchers",
//                "food_ranchers", "I8", "4.7",
//                "Dine In", "Fast Food", "No",
//                "Description: Ranchers Café offers a flavorful experience with its mighty burgers, both chicken and beef. Their menu includes a variety of options, from Bronco Chicken Burgers to Texas Jack Beef Burgers.\n" +
//                        "Famous For: Mighty burgers, thin-crust pizzas, and loaded fries.\n" +
//                        "Locations: Ranchers has branches in I-8 Markaz and Blue Area",
//                "food_pic1","https://maps.app.goo.gl/cGUmaBJ9cB8SVoYH7", "ranchers", 1, 1, "food"));
//
//        foodcatList.add(new FoodCatDomain("TKR",
//                "food_tkr", "F7", "4.7",
//                "Dine In", "Fast Food", "No",
//                "Description: TKR is known for its diverse menu, offering everything from desi to continental dishes. Their biryanis and karahi are particularly popular.\n" +
//                        "Famous For: Flavorful biryanis, karahi, and fusion cuisine.\n" +
//                        "Locations: TKR has branches in F-7 Markaz and other areas.",
//                "food_pic1","https://maps.app.goo.gl/oBwtikxxgAQs66Ri9", "tkr", 1, 1, "food"));
//
//        foodcatList.add(new FoodCatDomain("Malang Jan",
//                "food_malang", "G-9", "4.7",
//                "Dine In", "Fast Food", "No",
//                "Description: Malang Jan offers authentic Afghan cuisine in a cozy setting. From kebabs to soups, their menu features a variety of Afghan delights.\n" +
//                        "Famous For: Afghan kebabs, rice dishes, and flavorful soups.\n" +
//                        "Locations: You can find Malang Jan in G-9 Markaz and other locations",
//                "food_pic1",
//                "https://maps.app.goo.gl/zoZD1EDb1HxtH9JM6", "malangJan", 1, 1, "food"));
//
//        foodcatList.add(new FoodCatDomain("Ox And Grill House",
//                "food_ox", "F7", "4.7",
//                "Dine In", "Fast Food", "No",
//                "Description: OX & Grill is a steakhouse that serves juicy steaks, grilled meats, and seafood. Their cozy ambiance and quality cuts make it a favorite for meat lovers.\n" +
//                        "Famous For: Steaks, grilled dishes, and seafood.\n" +
//                        "Locations: OX & Grill is located in F-7 Markaz",
//                "food_ox",
//                "https://maps.app.goo.gl/vZScLsoEece6tffd6", "oxAndGrill", 1, 1, "food"));




    }
}

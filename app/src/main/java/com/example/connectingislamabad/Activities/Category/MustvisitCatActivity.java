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
import android.widget.ImageView;

import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.Activities.Setting.SettingActivity;
import com.example.connectingislamabad.Adapters.MuseumCatAdapter;
import com.example.connectingislamabad.Domains.MuseumCatDomain;
import com.example.connectingislamabad.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MustvisitCatActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterMustvisitCat ;
    private RecyclerView recyclerViewMustvisitCat;
    private ImageView backBtn;
    private GoogleMap mMap;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mustvisit_cat);
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

        db = FirebaseFirestore.getInstance();

        db.collection("mustVisit").document("1Data").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {

                    ArrayList<MuseumCatDomain> mustvisitcatList = new ArrayList<>();

                    // Fetch total reviews count from counter field
                    Map<String, Object> data = document.getData();
                    List<Map<String, Object>> allData = new ArrayList<>();

                    Map<String, Object> centaurus = (Map<String, Object>) data.get("centaurus");
                    Map<String, Object> lokVirsa = (Map<String, Object>) data.get("lokVirsa");
                    Map<String, Object> supremeCourt = (Map<String, Object>) data.get("supremeCourt");

                    allData.add(centaurus);
                    allData.add(lokVirsa);
                    allData.add(supremeCourt);

                    for(int i = 0; i < allData.size(); i++)
                    {
                        String titleTxt = (String) allData.get(i).get("titleTxt");
                        String picImg = (String) allData.get(i).get("picImg");
                        String locationTxt = (String) allData.get(i).get("locationTxt");
                        String ratingTxt = (String) allData.get(i).get("ratingTxt");
                        String firstTxt = (String) allData.get(i).get("firstTxt");
                        String secondTxt = (String) allData.get(i).get("secondTxt");
                        String thirdTxt = (String) allData.get(i).get("thirdTxt");
                        String fourthTxt = (String) allData.get(i).get("fourthTxt");
                        String descriptionTxt = (String) allData.get(i).get("descriptionTxt");
                        String collection_name = (String) allData.get(i).get("collection_name");
                        String document_name = (String) allData.get(i).get("document_name");
                        Object latitudeObj = allData.get(i).get("latitude");
                        Object longitudeObj = allData.get(i).get("longitude");
                        String direction_btn = (String) allData.get(i).get("direction_btn");

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

                        mustvisitcatList.add(new MuseumCatDomain(titleTxt,picImg,locationTxt,ratingTxt,firstTxt,secondTxt,thirdTxt,fourthTxt,
                                descriptionTxt,
                                direction_btn,latitude,longitude, document_name, collection_name));


                    }

                    recyclerViewMustvisitCat = findViewById(R.id.view_mustvisit_cat);
                    recyclerViewMustvisitCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

                    adapterMustvisitCat = new MuseumCatAdapter(mustvisitcatList, mMap);
                    recyclerViewMustvisitCat.setAdapter(adapterMustvisitCat);

                } else {
                    Log.d("Rating", "No such document");
                }
            } else {
                Log.d("Rating", "get failed with ", task.getException());
            }
        });


//        ArrayList<MuseumCatDomain> mustvisitcatList = new ArrayList<>();
//
//
//        mustvisitcatList.add(new MuseumCatDomain("Centaurus Mall","market_1","ISB","4.5","Top Rated","Shopping","Top Place","F9",
//                "Experience unparalleled shopping, dining, and entertainment at The Centaurus Mall, Islamabad's premier destination.",
//                "https://maps.app.goo.gl/sUVw65Ys8Y7ZMHkL8",33.6937,73.0937, "centaurus", "mustVisit"));
//        mustvisitcatList.add(new MuseumCatDomain("Lok Virsa Heritage Museum","museum_lokvirsa","ISB","4.5","Top Rated","Shopping","Top Place","F9",
//                "Description: The Lok Virsa Museum, also known as the Heritage Museum, showcases living cultures of Pakistan. Located on the Shakarparian Hills, it features artifacts, traditional clothes, woodwork, and paintings. The museum celebrates Pakistan’s rich heritage and cultural diversity7.\n" +
//                        "Famous For: Representing Pakistan’s diverse cultural traditions.",
//                "https://maps.app.goo.gl/ywEP8vKpbG1HdMk17",33.6937,73.0937, "lokVirsa", "mustVisit"));
//
//        mustvisitcatList.add(new MuseumCatDomain("Supreme Court Museum","museum_supreme","ISB","4.5","Top Rated","Shopping","Top Place","F9",
//                "Description: The Supreme Court Museum serves as an invaluable repository, preserving the judicial history of both pre- and post-Independence eras. It houses fine arts, oral histories, photographs, personal belongings of Honorable Judges and Chief Justices, and an archival collection of rare documents2.\n" +
//                        "Famous For: Showcasing the evolution of the judiciary in Pakistan.",
//                "https://maps.app.goo.gl/H3f2DD5KQir7vDUK6",33.6937,73.0937, "supremeCourt", "mustVisit"));
//
//
//        recyclerViewMustvisitCat = findViewById(R.id.view_mustvisit_cat);
//        recyclerViewMustvisitCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//
//        adapterMustvisitCat = new MuseumCatAdapter(mustvisitcatList, mMap);
//        recyclerViewMustvisitCat.setAdapter(adapterMustvisitCat);
    }
}
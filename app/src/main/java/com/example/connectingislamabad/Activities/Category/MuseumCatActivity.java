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
import com.example.connectingislamabad.Adapters.FoodCatAdapter;
import com.example.connectingislamabad.Adapters.MuseumCatAdapter;
import com.example.connectingislamabad.Domains.FoodCatDomain;
import com.example.connectingislamabad.Domains.MuseumCatDomain;
import com.example.connectingislamabad.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MuseumCatActivity extends AppCompatActivity   {

    private RecyclerView.Adapter adapterMuseumCat;
    private RecyclerView recyclerViewMuseumCat;
    private ImageView backBtn;
    private GoogleMap mMap;

    private FirebaseFirestore db;
//    private ArrayList<MuseumCatDomain> museumcatList; // Declare it here


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_cat);

//         museumcatList = new ArrayList<>(); // Initialize it here

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

        db.collection("museum").document("1Data").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {

                    ArrayList<MuseumCatDomain> museumcatList = new ArrayList<>();

                    // Fetch total reviews count from counter field
                    Map<String, Object> data = document.getData();
                    List<Map<String, Object>> allData = new ArrayList<>();

                    Map<String, Object> islamabad = (Map<String, Object>) data.get("islamabad");
                    Map<String, Object> lokVirsa = (Map<String, Object>) data.get("lokVirsa");
                    Map<String, Object> monument = (Map<String, Object>) data.get("monument");
                    Map<String, Object> nationalInsect = (Map<String, Object>) data.get("nationalInsect");
                    Map<String, Object> pakistan = (Map<String, Object>) data.get("pakistan");
                    Map<String, Object> supremeCourt = (Map<String, Object>) data.get("supremeCourt");

                    allData.add(islamabad);
                    allData.add(lokVirsa);
                    allData.add(monument);
                    allData.add(nationalInsect);
                    allData.add(pakistan);
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

                        museumcatList.add(new MuseumCatDomain(titleTxt,picImg,locationTxt,ratingTxt,firstTxt,secondTxt,thirdTxt,fourthTxt,
                                descriptionTxt,
                                direction_btn,latitude,longitude, document_name, collection_name));


                    }

                    recyclerViewMuseumCat = findViewById(R.id.view_museum_cat);
                    recyclerViewMuseumCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

                    adapterMuseumCat = new MuseumCatAdapter(museumcatList, mMap);
                    recyclerViewMuseumCat.setAdapter(adapterMuseumCat);

                } else {
                    Log.d("Rating", "No such document");
                }
            } else {
                Log.d("Rating", "get failed with ", task.getException());
            }
        });

//        ArrayList<MuseumCatDomain> museumcatList = new ArrayList<>();
////
////        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
////        mapFragment.getMapAsync(this);
//
//        museumcatList.add(new MuseumCatDomain("The Islamabad Museum", "museum_isb", "ISB", "4.5", "Top Rated", "Shopping", "Top Place", "F9",
//                "Description: The Islamabad Museum, also known as the Pakistan Monument Museum, is a window into the soul of the city. It captures the essence of Islamabad’s development, its people, and its vision for the future. The museum features models, photographs, and exhibits that showcase the capital’s master plan and its role in the modern world1.\n" +
//                        "Famous For: Depicting the history of Islamabad, from ancient times to the present day",
//                "https://maps.app.goo.gl/X7SeBZUW9XG2W62M8", 33.6844, 72.4194, "islamabad", "museum"));
//
//        museumcatList.add(new MuseumCatDomain("Supreme Court Museum", "museum_supreme", "ISB", "4.5", "Top Rated", "Shopping", "Top Place", "F9",
//                "Description: The Supreme Court Museum serves as an invaluable repository, preserving the judicial history of both pre- and post-Independence eras. It houses fine arts, oral histories, photographs, personal belongings of Honorable Judges and Chief Justices, and an archival collection of rare documents2.\n" +
//                        "Famous For: Showcasing the evolution of the judiciary in Pakistan.",
//                "https://maps.app.goo.gl/H3f2DD5KQir7vDUK6", 33.6937, 73.0937, "supremeCourt", "museum"));
//
//        museumcatList.add(new MuseumCatDomain("Pakistan Museum of Natural Histroy", "museum_natual", "ISB", "4.5", "Top Rated", "Shopping", "Top Place", "F9",
//                "Description: Established in 1976, PMNH is a public natural history museum situated in Islamabad. Its exhibits and galleries provide information about the ecology, geology, and paleontology of Pakistan. The museum houses over 35 million specimens, making it one of the largest entomological collections globally34.\n" +
//                        "Famous For: Diverse insect specimens and research on taxonomy, life history, and evolutionary history.",
//                "https://maps.app.goo.gl/8JS2BVnXUhD7r8Xy6", 33.6937, 73.0937, "pakistan", "museum"));
//        museumcatList.add(new MuseumCatDomain("Pakistan Monument Museum", "museum_monument", "ISB", "4.5", "Top Rated", "Shopping", "Top Place", "F9",
//                "Description: The Pakistan Monument, located on the western Shakarparian Hills, symbolizes unity among the Pakistani people. The monument’s four large petals represent the main cultures (Punjabi, Baloch, Sindhi, and Pakhtun), while the smaller petals represent minorities, Azad Kashmir, and Gilgit-Baltistan. The museum adjacent to the monument depicts ancient civilization, freedom struggle, and major achievements of Pakistan56.\n" +
//                        "Famous For: Commemorating sacrifices for a better tomorrow.",
//                "https://maps.app.goo.gl/qysFGZPWkRfzfeyg7", 33.6937, 73.0937, "monument", "museum"));
//
//        museumcatList.add(new MuseumCatDomain("National Insect Museum", "museum_insect", "ISB", "4.5", "Top Rated", "Shopping", "Top Place", "F9",
//                "Description: While not specifically located in Islamabad, the Smithsonian National Museum of Natural History’s entomology collection is one of the largest globally, with over 35 million specimens. It contributes to research on insects, arachnids, and myriapods, emphasizing taxonomy, life history, and evolutionary relationships4.\n" +
//                        "Famous For: Vast insect diversity and scientific research.",
//                "https://maps.app.goo.gl/s9BtdT1zVV6KtKiU7", 33.6937, 73.0937, "nationalInsect", "museum"));
//        museumcatList.add(new MuseumCatDomain("Lok Virsa Heritage Museum", "museum_lokvirsa", "ISB", "4.5", "Top Rated", "Shopping", "Top Place", "F9",
//                "Description: The Lok Virsa Museum, also known as the Heritage Museum, showcases living cultures of Pakistan. Located on the Shakarparian Hills, it features artifacts, traditional clothes, woodwork, and paintings. The museum celebrates Pakistan’s rich heritage and cultural diversity7.\n" +
//                        "Famous For: Representing Pakistan’s diverse cultural traditions.",
//                "https://maps.app.goo.gl/ywEP8vKpbG1HdMk17", 33.6937, 73.0937, "lokVirsa", "museum"));
//
//
//        recyclerViewMuseumCat = findViewById(R.id.view_museum_cat);
//        recyclerViewMuseumCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//
//        adapterMuseumCat = new MuseumCatAdapter(museumcatList, mMap);
//        recyclerViewMuseumCat.setAdapter(adapterMuseumCat);
    }
}

package com.example.connectingislamabad.Activities.Transport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.SearchView;


import com.example.connectingislamabad.Activities.Category.CategoryActivity;
import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.Activities.Setting.SettingActivity;
import com.example.connectingislamabad.Adapters.FoodCatAdapter;
import com.example.connectingislamabad.Adapters.PopularAdapter;
import com.example.connectingislamabad.Adapters.TransportAdapter;
import com.example.connectingislamabad.Domains.PopularDomain;
import com.example.connectingislamabad.Domains.TransportDomain;
import com.example.connectingislamabad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransportActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterTransport ;
    private RecyclerView recyclerViewTransport;

    private SearchView searchView;

    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        //Calling Recycler View
        initRecyclerView();

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ((TransportAdapter) adapterTransport).filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((TransportAdapter) adapterTransport).filter(newText);
                return false;
            }
        });

        // Navigation Bar Controller
        BottomNavigationView bottomNavigationView = findViewById(R.id.buttomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.transport_bottom);

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

        db.collection("transport").document("1Data").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {

                    ArrayList<TransportDomain> transportList = new ArrayList<>();

                    // Fetch total reviews count from counter field
                    Map<String, Object> data = document.getData();
                    List<Map<String, Object>> allData = new ArrayList<>();

                    Map<String, Object> blueline = (Map<String, Object>) data.get("blueline");
                    Map<String, Object> greenline = (Map<String, Object>) data.get("greenline");
                    Map<String, Object> orangeline = (Map<String, Object>) data.get("orangeline");
                    Map<String, Object> redline = (Map<String, Object>) data.get("redline");
                    Map<String, Object> local = (Map<String, Object>) data.get("local");

                    allData.add(blueline);
                    allData.add(greenline);
                    allData.add(orangeline);
                    allData.add(redline);
                    allData.add(local);

                    for(int i = 0; i < allData.size(); i++)
                    {
                        String titleTxt = (String) allData.get(i).get("titleTxt");
                        String descTxt = (String) allData.get(i).get("descTxt");
                        String picImg = (String) allData.get(i).get("picImg");
                        String routeImg = (String) allData.get(i).get("routeImg");
                        String collection_name = (String) allData.get(i).get("collection_name");
                        String document_name = (String) allData.get(i).get("document_name");


                        transportList.add(new TransportDomain(titleTxt,
                                descTxt,
                                picImg,
                                routeImg, document_name, collection_name));
                    }

                    recyclerViewTransport = findViewById(R.id.view_transport);

                    recyclerViewTransport.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                    adapterTransport = new TransportAdapter(transportList);
                    recyclerViewTransport.setAdapter(adapterTransport);

                } else {
                    Log.d("Rating", "No such document");
                }
            } else {
                Log.d("Rating", "get failed with ", task.getException());
            }
        });


        // For Trasport Data
//        ArrayList<TransportDomain> transportList = new ArrayList<>();
//
//
//        //Add these Details in Tranport Details Activity :)
//        transportList.add(new TransportDomain("Red Line Bus",
//                "Red Line Metro\n" +
//                        "1. Secretariat \n" +
//                        "2. Shaheed-e-Millat\n" +
//                        "3. 7th Avenue \n" +
//                        "4. Stock Exchange \n" +
//                        "5. Pims\n" +
//                        "6. Katchery \n" +
//                        "7. Ibn-e-Sina\n" +
//                        "8. Chaman\n" +
//                        "9. Kashmir Highway\n" +
//                        "10. Faiz Ahmad Faiz\n" +
//                        "11. Khayaban-e-Johar\n" +
//                        "12. Potohar\n" +
//                        "13. IJP\n" +
//                        "14. FAIZABAD\n" +
//                        "15. Khatam-e-Nabuwat\n" +
//                        "16. 6th Road\n" +
//                        "17. Rehmanabad\n" +
//                        "18. Chandni Chowk\n" +
//                        "19. Waris Khan\n" +
//                        "20. Commitee Chowk\n" +
//                        "21. Liaqat Bagh\n" +
//                        "22. Marrir Chowk\n" +
//                        "23. Sadar",
//                "bus_metro",
//                "route_red", "redline", "transport"));
//        transportList.add(new TransportDomain("Blue Line Bus",
//                "Blue Line\n" +
//                        "1. Pims/Centaurus\n" +
//                        "2. Pims Gate\n" +
//                        "3. G7/G8\n" +
//                        "4. H-8/Shakarparian\n" +
//                        "5. I-8/Parade Ground\n" +
//                        "6. Faizabad \n" +
//                        "7. Sohan\n" +
//                        "8. Iqbal Town\n" +
//                        "9. Kuri Road\n" +
//                        "10. Zia Masjid\n" +
//                        "11. Khanna Pul\n" +
//                        "12. Fazaia\n" +
//                        "13. Gangal\n" +
//                        "14. Karal Chowk\n" +
//                        "15. Gulberg Green",
//                "bus_blue",
//                "route_1", "blueline", "transport"));
//        transportList.add(new TransportDomain("Orange Line Bus",
//                "Orange Line\n" +
//                        "1. Faiz Ahmad Faiz(H-8/H-9)\n" +
//                        "2. NHA\n" +
//                        "3. G-10\n" +
//                        "4. Police Foundation\n" +
//                        "5. Nust\n" +
//                        "6. G-13\n" +
//                        "7. Golra Morr\n" +
//                        "8. N-5/26 No.\n" +
//                        "9. Islamabad International Airport" +
//                        "\n",
//                "bus_orange",
//                "route_orange", "orangeline", "transport"));
//        transportList.add(new TransportDomain("Green Line Bus",
//                "Green line\n" +
//                        "1. Pims/Centaurus\n" +
//                        "2. Pims Gate\n" +
//                        "3. G7/G8\n" +
//                        "4. CDA\n" +
//                        "5. Aabpara\n" +
//                        "6. Foreign Office\n" +
//                        "7. Lake View Park\n" +
//                        "8. Malpur\n" +
//                        "9. Shahdara\n" +
//                        "10. Bharakahu",
//                "bus_metro",
//                "route_1", "greenline", "transport"));
//        transportList.add(new TransportDomain("Local Trasport Guide",
//                "In This The Route and stops of wagon is showing ",
//                "bus_metro",
//                "local", "localbus", "transport"));
//
//
//        recyclerViewTransport = findViewById(R.id.view_transport);
//
//        recyclerViewTransport.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        adapterTransport = new TransportAdapter(transportList);
//        recyclerViewTransport.setAdapter(adapterTransport);

    }
}
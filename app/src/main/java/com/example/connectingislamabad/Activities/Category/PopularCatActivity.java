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
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.connectingislamabad.Activities.Setting.SettingActivity;
import com.example.connectingislamabad.Activities.Transport.TransportActivity;
import com.example.connectingislamabad.Adapters.FoodCatAdapter;
import com.example.connectingislamabad.Adapters.MuseumCatAdapter;
import com.example.connectingislamabad.Adapters.PopularAdapter;
import com.example.connectingislamabad.Domains.FoodCatDomain;
import com.example.connectingislamabad.Domains.MuseumCatDomain;
import com.example.connectingislamabad.Domains.PopularDomain;
import com.example.connectingislamabad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PopularCatActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular;
    private RecyclerView recyclerViewPopular;
    private ImageView backBtn;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_cat);

        initRecyclerView();
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> onBackPressed());

        BottomNavigationView bottomNavigationView = findViewById(R.id.buttomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.category_bottom);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_bottom) {
                // Handle navigation to home
                return true;
            } else if (item.getItemId() == R.id.category_bottom) {
                startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.transport_bottom) {
                startActivity(new Intent(getApplicationContext(), TransportActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.setting_bottom) {
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else {
                return false;
            }
        });
    }

    private void initRecyclerView() {

        db = FirebaseFirestore.getInstance();

        db.collection("popular").document("1Data").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {

                    ArrayList<PopularDomain> popularList = new ArrayList<>();

                    // Fetch total reviews count from counter field
                    Map<String, Object> data = document.getData();
                    List<Map<String, Object>> allData = new ArrayList<>();

                    Map<String, Object> damnekoh = (Map<String, Object>) data.get("damnekoh");
                    Map<String, Object> faisalMasjid = (Map<String, Object>) data.get("faisalMasjid");
                    Map<String, Object> monument = (Map<String, Object>) data.get("monument");

                    allData.add(damnekoh);
                    allData.add(faisalMasjid);
                    allData.add(monument);

                    for(int i = 0; i < allData.size(); i++)
                    {
                        String title = (String) allData.get(i).get("title");
                        String location = (String) allData.get(i).get("location");
                        String description = (String) allData.get(i).get("description");
                        boolean guide = (boolean) allData.get(i).get("guide");
                        String pic = (String) allData.get(i).get("pic");
                        boolean wifi = (boolean) allData.get(i).get("wifi");
                        Object priceObj = allData.get(i).get("price");
                        String collection_name = (String) allData.get(i).get("collection_name");
                        String document_name = (String) allData.get(i).get("document_name");
                        Object latitudeObj = allData.get(i).get("latitude");
                        Object longitudeObj = allData.get(i).get("longitude");
                        Object ratingObj = allData.get(i).get("rating");
                        String direction_btn = (String) allData.get(i).get("direction_btn");

                        double latitude = 0.0;
                        double longitude = 0.0;
                        double rating = 0.0;
                        int price = 0;

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

                        if (ratingObj instanceof Long) {
                            rating = ((Long) ratingObj).doubleValue();
                        } else if (ratingObj instanceof Double) {
                            rating = (Double) ratingObj;
                        }

                        if (priceObj instanceof Long) {
                            price = ((Long) priceObj).intValue();
                        } else if (priceObj instanceof Integer) {
                            price = (Integer) priceObj;
                        } else if (priceObj instanceof String) {
                            try {
                                price = Integer.parseInt((String) priceObj);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }

                        popularList.add(new PopularDomain(title,
                                location, description, guide,
                                rating, pic, wifi,
                                price,
                                collection_name, document_name, latitude, longitude, direction_btn));
                    }

                    recyclerViewPopular = findViewById(R.id.view_popular_cat);
                    recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

                    adapterPopular = new PopularAdapter(popularList);
                    recyclerViewPopular.setAdapter(adapterPopular);

                } else {
                    Log.d("Rating", "No such document");
                }
            } else {
                Log.d("Rating", "get failed with ", task.getException());
            }
        });


//        ArrayList<PopularDomain> popularList = new ArrayList<>();
//
//        //Add Data Into FoodCat Related
//
//        popularList.add(new PopularDomain("Faisal Mosque",
//                "Islamabad, Pakistan", "A beautiful mosque with a unique architecture", true,
//                4.8, "pop2", true,
//                0,
//                "popular", "faisalMasjid", 33.743643, 72.937898, "https://maps.app.goo.gl/ct9uZpAERzdde6PDA"));
//
//        popularList.add(new PopularDomain("Pakistan Monument",
//                "Islamabad, Pakistan", "A national monument representing the country's four provinces", true,
//                4.7, "pop1", true,
//                0,
//                "popular", "monument", 33.693557, 72.935701, "https://maps.app.goo.gl/iJiJvsBFZ39igS2CA"));
//
//        popularList.add(new PopularDomain("Daman-e-Koh",
//                "Islamabad, Pakistan", "A viewpoint with a stunning view of the city", true,
//                4.6, "pop3", true,
//                0,
//                "popular", "damnekoh", 33.702379, 72.947449, "https://maps.app.goo.gl/uHJGUgF19adJEqRb8"));
//
//
//        recyclerViewPopular = findViewById(R.id.view_popular_cat);
//        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//
//        adapterPopular = new PopularAdapter(popularList);
//        recyclerViewPopular.setAdapter(adapterPopular);
    }
}
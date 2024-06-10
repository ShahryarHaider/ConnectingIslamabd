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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.connectingislamabad.Activities.Setting.SettingActivity;
import com.example.connectingislamabad.Activities.Transport.TransportActivity;
import com.example.connectingislamabad.Adapters.PopularAdapter;
import com.example.connectingislamabad.Domains.PopularDomain;
import com.example.connectingislamabad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PopularCatActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular;
    private RecyclerView recyclerViewPopular;
    private ImageView backBtn;

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

        fetchPopularDomains();
    }
    private void initRecyclerView() {
        recyclerViewPopular = findViewById(R.id.view_popular_cat);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void fetchPopularDomains() {
        String url = "http://localhost:8080/api/v2/popular"; // Update with your endpoint

        //RequestQueue queue = Volley.newRequestQueue(PopularCatActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response ->
        {
            try {
                System.out.println(response);
                JSONArray jsonArray = new JSONArray(response);

                ArrayList<PopularDomain> popularDomains = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    long id = obj.getInt("id");
                    String title = obj.getString("title");
                    String location = obj.getString("location");
                    String description = obj.getString("description");
                    boolean guide = obj.getBoolean("guide");
                    double rating = obj.getDouble("rating");
                    String pic = obj.getString("pic");
                    boolean wifi = obj.getBoolean("wifi");
                    int price = obj.getInt("price");

                    // Log the data for debugging purposes
                    Log.d("PopularCatActivity", "Title: " + title);
                    Log.d("PopularCatActivity", "Location: " + location);
                    Log.d("PopularCatActivity", "Description: " + description);
                    Log.d("PopularCatActivity", "Guide: " + guide);
                    Log.d("PopularCatActivity", "Rating: " + rating);
                    Log.d("PopularCatActivity", "Pic: " + pic);
                    Log.d("PopularCatActivity", "Wifi: " + wifi);
                    Log.d("PopularCatActivity", "Price: " + price);

                    PopularDomain popularDomain = new PopularDomain(
                            id,
                            title,
                            location,
                            description,
                            guide,
                            rating,
                            pic,
                            wifi,
                            price
                    );
                    popularDomains.add(popularDomain);
                    System.out.println(popularDomain);
                }

                adapterPopular = new PopularAdapter(popularDomains);
                recyclerViewPopular.setAdapter(adapterPopular);
            } catch (JSONException e) {
                Log.e("PopularCatActivity", "JSON parsing error", e);
                Toast.makeText(PopularCatActivity.this, "Error parsing data", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            Log.e("PopularCatActivity", "Error fetching popular domains", error);
            Toast.makeText(PopularCatActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
        });

        //queue.add(stringRequest);
    }
}
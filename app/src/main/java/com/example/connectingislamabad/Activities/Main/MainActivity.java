package com.example.connectingislamabad.Activities.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connectingislamabad.Activities.Authentication.SignIn.SigninActivity;
import com.example.connectingislamabad.Activities.Category.CategoryActivity;
import com.example.connectingislamabad.Activities.Category.PopularCatActivity;
import com.example.connectingislamabad.Activities.Setting.SettingActivity;
import com.example.connectingislamabad.Activities.Transport.TransportActivity;
import com.example.connectingislamabad.Adapters.FoodCatAdapter;
import com.example.connectingislamabad.Adapters.PopularAdapter;
import com.example.connectingislamabad.Domains.FoodCatDomain;
import com.example.connectingislamabad.Domains.PopularDomain;
import com.example.connectingislamabad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private FirebaseFirestore db;
    private PopularAdapter adapterPopular;
    private RecyclerView recyclerViewPopular, recyclerViewCategory;
    private TextView textView_SeeAll_Popular, o_name;

    @Override
    protected void onResume() {

        FirebaseApp.initializeApp(this);



        super.onResume();
//        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
//        boolean isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false);

//        if (!isLoggedIn) {
//            Intent intent = new Intent(this, SigninActivity.class);
//            startActivity(intent);
//            finish();
//            return;
//        }
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            String user = "hello";
            Intent userIntent = getIntent();
            user = userIntent.getStringExtra("val");
            if(user.equals("skip")){


            } else {
                Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(intent);
                finish();
            }
             // Prevent the user from returning to this activity
        } else {
            // User is signed in, proceed to display main activity
            Log.d("MainActivity", "User is already signed in.");
            // You can add any additional setup code here if needed
        }




        //Activity
        setContentView(R.layout.activity_main);

        // initialize
        textView_SeeAll_Popular = findViewById(R.id.textView_SeeAll_Popular);
        o_name = findViewById(R.id.name);

        String userId = currentUser.getUid();
        db.collection("users").document(userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    String userName = document.getString("name");

                    if (userName != null) {
                        o_name.setText(userName);
                    }
                } else {
                    Log.d("MainActivity", "No such document");
                }
            } else {
                Log.d("MainActivity", "get failed with ", task.getException());
            }
        });

        textView_SeeAll_Popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PopularCatActivity.class);
                startActivity(intent);
            }
        });

        initRecylerView();

        // Navigation Bar Controller
        BottomNavigationView bottomNavigationView = findViewById(R.id.buttomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home_bottom);

        // Navigation bottom Bar
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

    private void initRecylerView() {
        ArrayList<PopularDomain> popularList = new ArrayList<>();

        //Add Data Into FoodCat Related

        popularList.add(new PopularDomain("Faisal Mosque",
                "Islamabad, Pakistan", "A beautiful mosque with a unique architecture", true,
                4.8, "pop2", true,
                0,
                "popular", "faisalMasjid", 33.743643, 72.937898, "https://maps.app.goo.gl/ct9uZpAERzdde6PDA"));

        popularList.add(new PopularDomain("Pakistan Monument",
                "Islamabad, Pakistan", "A national monument representing the country's four provinces", true,
                4.7, "pop1", true,
                0,
                "popular", "monument", 33.693557, 72.935701, "https://maps.app.goo.gl/iJiJvsBFZ39igS2CA"));

        popularList.add(new PopularDomain("Daman-e-Koh",
                "Islamabad, Pakistan", "A viewpoint with a stunning view of the city", true,
                4.6, "pop3", true,
                0,
                "popular", "damnekoh", 33.702379, 72.947449, "https://maps.app.goo.gl/uHJGUgF19adJEqRb8"));


        recyclerViewPopular = findViewById(R.id.view_pop);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterPopular = new PopularAdapter(popularList);
        recyclerViewPopular.setAdapter(adapterPopular);
    }
}

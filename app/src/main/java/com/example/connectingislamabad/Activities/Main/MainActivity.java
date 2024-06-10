package com.example.connectingislamabad.Activities.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.example.connectingislamabad.Adapters.PopularAdapter;
import com.example.connectingislamabad.Domains.PopularDomain;
import com.example.connectingislamabad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PopularAdapter adapterPopular;
    private RecyclerView recyclerViewPopular, recyclerViewCategory;
    private TextView textView_SeeAll_Popular, o_name;

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false);

        if (!isLoggedIn) {
            Intent intent = new Intent(this, SigninActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        //Activity
        setContentView(R.layout.activity_main);

        // initialize
        textView_SeeAll_Popular = findViewById(R.id.textView_SeeAll_Popular);
        o_name = findViewById(R.id.name);

        String name = sharedPreferences.getString("name", "User");
        o_name.setText(name);

        textView_SeeAll_Popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PopularCatActivity.class);
                startActivity(intent);
            }
        });

//        initRecylerView();

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

//    private void initRecylerView() {
//        ArrayList<PopularDomain> items = new ArrayList<>();
//        items.add(new PopularDomain("Faisal Mosque", "Islamabad", " introduction to Islamabad, Pakistan’s capital city:\n" +
//                "\n" +
//                "Shah Faisal Mosque: This magnificent mosque, named after King Faisal of Saudi Arabia, stands as one of the largest mosques in the world. Its unique design, inspired by a Bedouin tent, is a testament to modern Islamic architecture.\n" +
//                "Margalla Hills National Park: Just outside the city, the Margalla Hills offer breathtaking hiking trails and panoramic views of Islamabad. Daman-e-Koh, a popular viewpoint, provides a stunning vista of the cityscape.\n" +
//                "Lok Virsa Museum: Immerse yourself in Pakistan’s rich cultural heritage at this museum. From traditional crafts to historical artifacts, it’s a treasure trove of knowledge.\n" +
//                "Rawal Lake: A serene escape within the city, Rawal Lake is perfect for picnics, boating, and birdwatching. The surrounding hills add to its natural beauty.",
//                true, 4.9, "pop2", false, 0));
//        items.add(new PopularDomain("Pakistan Monument Museum", "Western Shakarparian", "The Pakistan Monument ...", true, 4.7, "pop1", true, 0));
//        items.add(new PopularDomain("Daman e Koh", "Margala Hills", "The Daman-e-Koh ...", false, 4.9, "pop3", false, 0));
//
//        recyclerViewPopular = findViewById(R.id.view_pop);
//        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        adapterPopular = new PopularAdapter(items);
//        recyclerViewPopular.setAdapter(adapterPopular);
//    }
}

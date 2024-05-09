package com.example.connectingislamabad.Activities.Transport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Adapter;


import com.example.connectingislamabad.Activities.Category.CategoryActivity;
import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.Activities.Setting.SettingActivity;
import com.example.connectingislamabad.Adapters.TransportAdapter;
import com.example.connectingislamabad.Domains.TransportDomain;
import com.example.connectingislamabad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class TransportActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterTransport ;
    private RecyclerView recyclerViewTransport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        //Calling Recycler View
        initRecyclerView();

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
        // For Trasport Data
        ArrayList<TransportDomain> transportList = new ArrayList<>();

        transportList.add(new TransportDomain("Red Line Bus",
                "Red Line Metro\n" +
                        "1. Secretariat \n" +
                        "2. Shaheed-e-Millat\n" +
                        "3. 7th Avenue \n" +
                        "4. Stock Exchange \n" +
                        "5. Pims\n" +
                        "6. Katchery \n" +
                        "7. Ibn-e-Sina\n" +
                        "8. Chaman\n" +
                        "9. Kashmir Highway\n" +
                        "10. Faiz Ahmad Faiz\n" +
                        "11. Khayaban-e-Johar\n" +
                        "12. Potohar\n" +
                        "13. IJP\n" +
                        "14. FAIZABAD\n" +
                        "15. Khatam-e-Nabuwat\n" +
                        "16. 6th Road\n" +
                        "17. Rehmanabad\n" +
                        "18. Chandni Chowk\n" +
                        "19. Waris Khan\n" +
                        "20. Commitee Chowk\n" +
                        "21. Liaqat Bagh\n" +
                        "22. Marrir Chowk\n" +
                        "23. Sadar",
                "bus_metro",
                "route_red"));
        transportList.add(new TransportDomain("Blue Line Bus",
                "Blue Line\n" +
                        "1. Pims/Centaurus\n" +
                        "2. Pims Gate\n" +
                        "3. G7/G8\n" +
                        "4. H-8/Shakarparian\n" +
                        "5. I-8/Parade Ground\n" +
                        "6. Faizabad \n" +
                        "7. Sohan\n" +
                        "8. Iqbal Town\n" +
                        "9. Kuri Road\n" +
                        "10. Zia Masjid\n" +
                        "11. Khanna Pul\n" +
                        "12. Fazaia\n" +
                        "13. Gangal\n" +
                        "14. Karal Chowk\n" +
                        "15. Gulberg Green",
                "bus_blue",
                "route_1"));
        transportList.add(new TransportDomain("Orange Line Bus",
                "Orange Line\n" +
                        "1. Faiz Ahmad Faiz(H-8/H-9)\n" +
                        "2. NHA\n" +
                        "3. G-10\n" +
                        "4. Police Foundation\n" +
                        "5. Nust\n" +
                        "6. G-13\n" +
                        "7. Golra Morr\n" +
                        "8. N-5/26 No.\n" +
                        "9. Islamabad International Airport" +
                        "\n",
                "bus_orange",
                "route_orange"));
        transportList.add(new TransportDomain("Green Line Bus",
                "Green line\n" +
                        "1. Pims/Centaurus\n" +
                        "2. Pims Gate\n" +
                        "3. G7/G8\n" +
                        "4. CDA\n" +
                        "5. Aabpara\n" +
                        "6. Foreign Office\n" +
                        "7. Lake View Park\n" +
                        "8. Malpur\n" +
                        "9. Shahdara\n" +
                        "10. Bharakahu",
                "bus_metro",
                "route_1"));
        transportList.add(new TransportDomain("Booking Application",
                "Hello :) ",
                "bus_metro",
                "route_1"));

        recyclerViewTransport = findViewById(R.id.view_transport);

        recyclerViewTransport.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterTransport = new TransportAdapter(transportList);
        recyclerViewTransport.setAdapter(adapterTransport);
    }
}
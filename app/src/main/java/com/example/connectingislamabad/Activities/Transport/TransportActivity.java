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

        transportList.add(new TransportDomain("Green Line",
                "Route 1: Islamabad Stock Exchange to Islamabad Airport\n" +
                        "1.\tIslamabad Stock Exchange: Starting point of the route, located near the financial district.\n" +
                        "2.\tBlue Area: Commercial and business hub of Islamabad.\n" +
                        "3.\tZero Point Interchange: Major junction connecting different parts of Islamabad.\n" +
                        "4.\tKhayaban-e-Suhrawardy: Road leading to the Islamabad Airport.\n" +
                        "5.\tPak-China Friendship Center: Landmark near the airport.\n" +
                        "6.\tIslamabad Airport: Terminus station located near the Benazir Bhutto International Airport.\n" +
                        "Route 2: Pakistan Institute of Medical Sciences (PIMS) to Golra Mor\n" +
                        "1.\tPakistan Institute of Medical Sciences (PIMS): Starting point situated near the premier medical institution.\n" +
                        "2.\tG-8 Markaz: Commercial and residential area in Islamabad.\n" +
                        "3.\tG-9 Markaz: Commercial area in Islamabad.\n" +
                        "4.\tG-10 Markaz: Commercial and residential area in Islamabad.\n" +
                        "5.\tGolra Mor: Terminus station located at the Golra Mor Interchange.\n" +
                        "Route 3: Golra Mor to H-12\n" +
                        "1.\tGolra Mor: Starting point located at the Golra Mor Interchange.\n" +
                        "2.\tG-11 Markaz: Commercial and residential area in Islamabad.\n" +
                        "3.\tG-12 Markaz: Commercial area in Islamabad.\n" +
                        "4.\tH-11 Markaz: Commercial area in Islamabad.\n" +
                        "5.\tH-12 Markaz: Terminus station located in the H-12 sector of Islamabad.\n" +
                        "Route 4: H-12 to Islamabad Stock Exchange\n" +
                        "1.\tH-12 Markaz: Starting point located in the H-12 sector of Islamabad.\n" +
                        "2.\tH-11 Markaz: Commercial area in Islamabad.\n" +
                        "3.\tG-12 Markaz: Commercial area in Islamabad.\n" +
                        "4.\tG-11 Markaz: Commercial and residential area in Islamabad.\n" +
                        "5.\tIslamabad Stock Exchange: Terminus station located near the financial district.\n",
                "bus_metro",
                "route_1"));
        transportList.add(new TransportDomain("Blue Line",
                "Route 1: Tarnol to Islamabad Stock Exchange\n" +
                        "1.\tTarnol: Starting point of the route, located in the Tarnol area.\n" +
                        "2.\tG-10 Markaz: Commercial and residential area in Islamabad.\n" +
                        "3.\tG-9 Markaz: Commercial area in Islamabad.\n" +
                        "4.\tG-8 Markaz: Commercial and residential area in Islamabad.\n" +
                        "5.\tI.J.P. Road: Named after the founder of Pakistan, Quaid-e-Azam Muhammad Ali Jinnah.\n" +
                        "6.\tZero Point Interchange: Major junction connecting different parts of Islamabad.\n" +
                        "7.\tBlue Area: Commercial and business hub of Islamabad.\n" +
                        "8.\tIslamabad Stock Exchange: Terminus station located near the financial district.\n" +
                        "Route 2: Islamabad Stock Exchange to PWD Colony\n" +
                        "1.\tIslamabad Stock Exchange: Starting point located near the financial district.\n" +
                        "2.\tBlue Area: Commercial and business hub of Islamabad.\n" +
                        "3.\tZero Point Interchange: Major junction connecting different parts of Islamabad.\n" +
                        "4.\tI.J.P. Road: Named after the founder of Pakistan, Quaid-e-Azam Muhammad Ali Jinnah.\n" +
                        "5.\tG-7 Markaz: Commercial and residential area in Islamabad.\n" +
                        "6.\tG-8 Markaz: Commercial and residential area in Islamabad.\n" +
                        "7.\tPWD Colony: Terminus station located in the PWD Housing Society.\n" +
                        "Route 3: PWD Colony to H-13\n" +
                        "1.\tPWD Colony: Starting point located in the PWD Housing Society.\n" +
                        "2.\tG-8 Markaz: Commercial and residential area in Islamabad.\n" +
                        "3.\tG-9 Markaz: Commercial area in Islamabad.\n" +
                        "4.\tG-10 Markaz: Commercial and residential area in Islamabad.\n" +
                        "5.\tG-11 Markaz: Commercial and residential area in Islamabad.\n" +
                        "6.\tH-13 Markaz: Terminus station located in the H-13 sector of Islamabad.\n" +
                        "Route 4: H-13 to Tarnol\n" +
                        "1.\tH-13 Markaz: Starting point located in the H-13 sector of Islamabad.\n" +
                        "2.\tG-11 Markaz: Commercial and residential area in Islamabad.\n" +
                        "3.\tG-10 Markaz: Commercial and residential area in Islamabad.\n" +
                        "4.\tG-9 Markaz: Commercial area in Islamabad.\n" +
                        "5.\tG-8 Markaz: Commercial and residential area in Islamabad.\n" +
                        "6.\tTarnol: Terminus station located in the Tarnol area.\n",
                "bus_metro",
                "route_1"));
        transportList.add(new TransportDomain("Local Transport",
                "\n" +
                        "\n" +
                        "Route 1: F-7 Markaz to G-11 Markaz\n" +
                        "1.\tF-7 Markaz: Starting point located in the F-7 sector, a commercial and shopping area.\n" +
                        "2.\tBlue Area: Commercial and business hub of Islamabad.\n" +
                        "3.\tZero Point Interchange: Major junction connecting different parts of Islamabad.\n" +
                        "4.\tI.J.P. Road: Named after the founder of Pakistan, Quaid-e-Azam Muhammad Ali Jinnah.\n" +
                        "5.\tG-7 Markaz: Commercial and residential area in Islamabad.\n" +
                        "6.\tG-8 Markaz: Commercial and residential area in Islamabad.\n" +
                        "7.\tG-9 Markaz: Commercial area in Islamabad.\n" +
                        "8.\tG-10 Markaz: Commercial and residential area in Islamabad.\n" +
                        "9.\tG-11 Markaz: Terminus station located in the G-11 sector of Islamabad.\n" +
                        "Route 2: I-8 Markaz to Pakistan Institute of Medical Sciences (PIMS)\n" +
                        "1.\tI-8 Markaz: Starting point located in the I-8 sector, a commercial area.\n" +
                        "2.\tI-9 Markaz: Commercial and residential area in Islamabad.\n" +
                        "3.\tI-10 Markaz: Commercial and residential area in Islamabad.\n" +
                        "4.\tI-11 Markaz: Commercial and residential area in Islamabad.\n" +
                        "5.\tPIMS: Terminus station located near the Pakistan Institute of Medical Sciences.\n" +
                        "Route 3: Sohan to Rawalpindi Railway Station\n" +
                        "1.\tSohan: Starting point located in Sohan, a residential area.\n" +
                        "2.\tKhanna Pul: Bridge over Korang River.\n" +
                        "3.\tKorang Town: Residential area in Islamabad.\n" +
                        "4.\tKoral Chowk: Intersection connecting various parts of Islamabad.\n" +
                        "5.\tFaizabad: Major junction connecting Rawalpindi and Islamabad.\n" +
                        "6.\tCommittee Chowk: Busy intersection in Rawalpindi.\n" +
                        "7.\tRawalpindi Railway Station: Terminus station serving the railway transportation network.\n" +
                        "Route 4: G-10 Markaz to Bara Kahu\n" +
                        "1.\tG-10 Markaz: Starting point located in the G-10 sector, a commercial and residential area.\n" +
                        "2.\tG-11 Markaz: Commercial and residential area in Islamabad.\n" +
                        "3.\tG-12 Markaz: Commercial area in Islamabad.\n" +
                        "4.\tG-13 Markaz: Commercial and residential area in Islamabad.\n" +
                        "5.\tKorang Bridge: Bridge over Korang River.\n" +
                        "6.\tSimly Dam Road: Route leading towards Bara Kahu.\n" +
                        "7.\tBara Kahu: Terminus station located in the Bara Kahu area, a suburban locality.\n" +
                        "\n",
                "bus_metro",
                "route_1"));
        transportList.add(new TransportDomain("Metro",
                "Route 1: Peshawar Mor to Secretariat\n" +
                        "Peshawar Mor: Starting point of the route, located near the Peshawar Mor Interchange.\n" +
                        "7th Avenue: Major junction providing access to various parts of Islamabad.\n" +
                        "I.J.P. Road: Named after the founder of Pakistan, Quaid-e-Azam Muhammad Ali Jinnah.\n" +
                        "Zero Point: A significant landmark and junction in Islamabad.\n" +
                        "Secretariat: Terminus station located near the government offices and ministries.\n" +
                        "Route 2: Secretariat to Pak Secretariat\n" +
                        "Secretariat: Starting point of the route, located near the government offices.\n" +
                        "Zero Point: Important junction connecting various parts of Islamabad.\n" +
                        "I.J.P. Road: Named after the founder of Pakistan, Quaid-e-Azam Muhammad Ali Jinnah.\n" +
                        "7th Avenue: Major avenue providing access to different areas of Islamabad.\n" +
                        "Pak Secretariat: Terminus station situated near the Pakistan Secretariat buildings.\n" +
                        "Route 3: Pak Secretariat to G-10 Markaz\n" +
                        "Pak Secretariat: Starting point of the route, near the government offices.\n" +
                        "7th Avenue: Major avenue with access to various parts of the city.\n" +
                        "I-8 Markaz: Commercial area in Islamabad.\n" +
                        "I-9 Markaz: Commercial and residential area in Islamabad.\n" +
                        "G-10 Markaz: Terminus station located in the G-10 sector of Islamabad.\n" +
                        "Route 4: G-10 Markaz to Peshawar Mor\n" +
                        "G-10 Markaz: Starting point located in the commercial hub of G-10 sector.\n" +
                        "I-9 Markaz: Commercial and residential area in Islamabad.\n" +
                        "I-8 Markaz: Commercial area in Islamabad.\n" +
                        "7th Avenue: Major avenue connecting various parts of Islamabad.\n" +
                        "Peshawar Mor: Terminus station located near the Peshawar Mor Interchange.",
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
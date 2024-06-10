package com.example.connectingislamabad.Activities.Category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MuseumCatActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterMuseumCat ;
    private RecyclerView recyclerViewMuseumCat;
    private ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_cat);

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

        ArrayList<MuseumCatDomain> museumcatList = new ArrayList<>();

        museumcatList.add(new MuseumCatDomain("The Islamabad Museum","museum_isb","ISB","4.5","Top Rated","Shopping","Top Place","F9",
                "Description: The Islamabad Museum, also known as the Pakistan Monument Museum, is a window into the soul of the city. It captures the essence of Islamabad’s development, its people, and its vision for the future. The museum features models, photographs, and exhibits that showcase the capital’s master plan and its role in the modern world1.\n" +
                        "Famous For: Depicting the history of Islamabad, from ancient times to the present day",
                "https://maps.app.goo.gl/X7SeBZUW9XG2W62M8\n"));

        museumcatList.add(new MuseumCatDomain("Supreme Court Museum","museum_supreme","ISB","4.5","Top Rated","Shopping","Top Place","F9",
                "Description: The Supreme Court Museum serves as an invaluable repository, preserving the judicial history of both pre- and post-Independence eras. It houses fine arts, oral histories, photographs, personal belongings of Honorable Judges and Chief Justices, and an archival collection of rare documents2.\n" +
                        "Famous For: Showcasing the evolution of the judiciary in Pakistan.",
                "https://maps.app.goo.gl/H3f2DD5KQir7vDUK6"));
        museumcatList.add(new MuseumCatDomain("Pakistan Museum of Natural Histroy","museum_natual","ISB","4.5","Top Rated","Shopping","Top Place","F9",
                "Description: Established in 1976, PMNH is a public natural history museum situated in Islamabad. Its exhibits and galleries provide information about the ecology, geology, and paleontology of Pakistan. The museum houses over 35 million specimens, making it one of the largest entomological collections globally34.\n" +
                        "Famous For: Diverse insect specimens and research on taxonomy, life history, and evolutionary history.",
                "https://maps.app.goo.gl/8JS2BVnXUhD7r8Xy6"));
        museumcatList.add(new MuseumCatDomain("Pakistan Monument Museum","museum_monument","ISB","4.5","Top Rated","Shopping","Top Place","F9",
                "Description: The Pakistan Monument, located on the western Shakarparian Hills, symbolizes unity among the Pakistani people. The monument’s four large petals represent the main cultures (Punjabi, Baloch, Sindhi, and Pakhtun), while the smaller petals represent minorities, Azad Kashmir, and Gilgit-Baltistan. The museum adjacent to the monument depicts ancient civilization, freedom struggle, and major achievements of Pakistan56.\n" +
                        "Famous For: Commemorating sacrifices for a better tomorrow.",
                "https://maps.app.goo.gl/qysFGZPWkRfzfeyg7"));
        museumcatList.add(new MuseumCatDomain("National Insect Museum","museum_insect","ISB","4.5","Top Rated","Shopping","Top Place","F9",
                "Description: While not specifically located in Islamabad, the Smithsonian National Museum of Natural History’s entomology collection is one of the largest globally, with over 35 million specimens. It contributes to research on insects, arachnids, and myriapods, emphasizing taxonomy, life history, and evolutionary relationships4.\n" +
                        "Famous For: Vast insect diversity and scientific research.",
                "https://maps.app.goo.gl/s9BtdT1zVV6KtKiU7"));
        museumcatList.add(new MuseumCatDomain("Lok Virsa Heritage Museum","museum_lokvirsa","ISB","4.5","Top Rated","Shopping","Top Place","F9",
                "Description: The Lok Virsa Museum, also known as the Heritage Museum, showcases living cultures of Pakistan. Located on the Shakarparian Hills, it features artifacts, traditional clothes, woodwork, and paintings. The museum celebrates Pakistan’s rich heritage and cultural diversity7.\n" +
                        "Famous For: Representing Pakistan’s diverse cultural traditions.",
                "https://maps.app.goo.gl/ywEP8vKpbG1HdMk17"));


        recyclerViewMuseumCat = findViewById(R.id.view_museum_cat);
        recyclerViewMuseumCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterMuseumCat = new MuseumCatAdapter(museumcatList);
        recyclerViewMuseumCat.setAdapter(adapterMuseumCat);
    }
}
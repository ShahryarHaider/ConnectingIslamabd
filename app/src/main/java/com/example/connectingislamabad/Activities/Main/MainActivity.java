package com.example.connectingislamabad.Activities.Main;

import static androidx.recyclerview.widget.RecyclerView.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connectingislamabad.Activities.Category.CategoryActivity;
import com.example.connectingislamabad.Activities.Setting.SettingActivity;
import com.example.connectingislamabad.Activities.Transport.TransportActivity;
import com.example.connectingislamabad.Adapters.CategoryAdapter;
import com.example.connectingislamabad.Adapters.PopularAdapter;
import com.example.connectingislamabad.Domains.CategoryDomain;
import com.example.connectingislamabad.Domains.PopularDomain;
import com.example.connectingislamabad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Adapter adapterPopular ;
    private RecyclerView recyclerViewPopular, recyclerViewCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Calling Recycler View
        initRecylerView();

        // Navigation Bar Controller
        BottomNavigationView bottomNavigationView = findViewById(R.id.buttomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home_bottom);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home_bottom) {
                    // Handle navigation to home
                    // You can start the HomeActivity or perform any other action
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
                    startActivity(new Intent(getApplicationContext(), TransportActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
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

    //RecyclerView of Poupular Category
    private void initRecylerView() {
        //Array List
        ArrayList <PopularDomain> items = new ArrayList<>();
        //Information

        //Item1
        items.add(new PopularDomain("Faisal Mosuque","Islamabad",
                "The Faisal Mosque (Urdu: فیصل مسجد, romanized: faisal masjid) is the national mosque of Pakistan," +
                " located in the capital city, Islamabad." +
                " It is the fifth-largest mosque in the world, the largest mosque outside the Middle East," +
                " and the largest within South Asia, located on the foothills of Margalla Hills in Islamabad",
                true,4.9,"pop2",false,0));

        //Item2
        items.add(new PopularDomain("Pakistan Monument Museum","Western Shakarparian ",
                " The Pakistan Monument is a national monument and heritage museum located on" +
                        " the western Shakarparian Hills in Islamabad, Pakistan. Constructed to symbolize the unity of" +
                        " the Pakistani people, it is dedicated to those who sacrificed their “today” for a better" +
                        " “tomorrow.” The monument features four large petals representing each of the four main"+
                        " cultures of Pakistan: Punjabi, Baloch, Sindhi, and Pakhtun. Additionally, three smaller petals"+
                        " represent minorities, Azad Kashmir, and Gilgit-Baltistan",
                true,4.7,"pop1",true,0));

        //Item3
        items.add(new PopularDomain("Daman e Koh","Margala Hills",
                "The Daman-e-Koh is a renowned hilltop picnic spot situated in Islamabad, Pakistan. It is" +
                        " in the Margalla Hills, providing visitors with awe-inspiring views of the capital city. The name" +
                        " originates from Persian, where “daman” translates to “skirt” and “koh” signifies “hill” 1. This" +
                        " picturesque location is approximately 2400 feet above sea level and about 500 feet above"+
                        " Islamabad. From Daman-e-Koh, you can have a bird’s-eye view of Islamabad, including"+
                        " landmarks like Faisal Mosque, Seventh Avenue, and Rawal Lake.",
                false,4.9,"pop3",false,0));

        //Calling Popular View
        recyclerViewPopular=findViewById(R.id.view_pop);

        //Representing the layout style of Popular Categories
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapterPopular=new PopularAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);

    }
}

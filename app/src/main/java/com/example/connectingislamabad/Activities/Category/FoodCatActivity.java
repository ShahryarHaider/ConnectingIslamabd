package com.example.connectingislamabad.Activities.Category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.connectingislamabad.Adapters.FoodCatAdapter;
import com.example.connectingislamabad.Domains.FoodCatDomain;
import com.example.connectingislamabad.Domains.TransportDomain;
import com.example.connectingislamabad.R;

import java.util.ArrayList;

public class FoodCatActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterFoodCat ;
    private RecyclerView recyclerViewFoodCat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_cat);

        initRecyclerView();

    }

    private void initRecyclerView() {

        ArrayList<FoodCatDomain> foodcatList = new ArrayList<>();

        foodcatList.add(new FoodCatDomain("Test","route_1","Uganda","6.9","Dine In","Hot Food","No","Nahi pta"));

        recyclerViewFoodCat = findViewById(R.id.view_food_cat);
        recyclerViewFoodCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterFoodCat = new FoodCatAdapter(foodcatList);
        recyclerViewFoodCat.setAdapter(adapterFoodCat);
    }
}
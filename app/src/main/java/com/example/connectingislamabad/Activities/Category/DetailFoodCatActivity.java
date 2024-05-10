package com.example.connectingislamabad.Activities.Category;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connectingislamabad.Domains.FoodCatDomain;
import com.example.connectingislamabad.Domains.TransportDomain;
import com.example.connectingislamabad.R;

public class DetailFoodCatActivity extends AppCompatActivity {

    private TextView titleTxt, descTxt;
    private FoodCatDomain item;

    private ImageView picImg, backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food_cat);

        initView(); // Initialize views first
        setVariable(); // Set text and other variables afterwards

    }

    private void setVariable() {
        titleTxt = findViewById(R.id.titleTxt);

    }

    private void initView() {
    }
}
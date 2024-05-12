package com.example.connectingislamabad.Activities.Category.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.connectingislamabad.Domains.FoodCatDomain;
import com.example.connectingislamabad.Domains.TransportDomain;
import com.example.connectingislamabad.R;

public class DetailFoodCatActivity extends AppCompatActivity {

    private TextView titleTxt, descriptionTxt,locationTxt,ratingTxt,dineTxt,typeTxt,wifiTxt;
    private FoodCatDomain item;

    private Button directionBtn;

    private ImageView picImg, backBtn,food_pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food_cat);

        initView(); // Initialize views first
        setVariable(); // Set text and other variables afterwards
    }

    private void setVariable() {
        item = (FoodCatDomain) getIntent().getSerializableExtra("object");

        titleTxt.setText(item.getTitleTxt());
        descriptionTxt.setText(item.getDescriptionTxt());
        locationTxt.setText(item.getLocationTxt());
        ratingTxt.setText(item.getRatingTxt());
        wifiTxt.setText(item.getWifiTxt());
        typeTxt.setText(item.getTypeTxt());
        directionBtn.setOnClickListener(v -> {

            // Open the link here
            String link = item.getDirection_btn(); // assume this is the link to open
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Log.e("Error", "No activity found to open the URL: " + link);
            }
        });

        int drawableResId = getResources().getIdentifier(item.getPicImg(), "drawable", getPackageName());

        Glide.with(this)
                .load(drawableResId)
                .into(picImg);

        //Backbutton Click
        backBtn.setOnClickListener(v -> finish());
    }

    private void initView() {
        titleTxt        = findViewById(R.id.titleTxt);
        descriptionTxt  = findViewById(R.id.descriptionTxt);
        locationTxt     = findViewById(R.id.locationTxt);
        ratingTxt       = findViewById(R.id.ratingTxt);
        dineTxt         = findViewById(R.id.dineTxt);
        typeTxt         = findViewById(R.id.typeTxt);
        wifiTxt         = findViewById(R.id.wifiTxt);

        backBtn         = findViewById(R.id.backBtn);
        picImg          = findViewById(R.id.picImg);
        food_pic        = findViewById(R.id.food_pic);

        directionBtn    = findViewById(R.id.directionBtn);
    }
}
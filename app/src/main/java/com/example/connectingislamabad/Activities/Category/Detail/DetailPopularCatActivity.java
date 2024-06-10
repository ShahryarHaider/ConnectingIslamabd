package com.example.connectingislamabad.Activities.Category.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.connectingislamabad.Domains.PopularDomain;
import com.example.connectingislamabad.R;

public class DetailPopularCatActivity extends AppCompatActivity {
    private TextView titleTxt, locationTxt, guideTxt, wifiTxt, descriptionTxt, ratingTxt, priceTxt;
    private ImageView picImg, backBtn;
    private PopularDomain item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_popular_cat);
        initView();
        setVariable();
    }

    private void initView() {
        titleTxt = findViewById(R.id.titleTxt);
        locationTxt = findViewById(R.id.locationTxt);
        guideTxt = findViewById(R.id.guideTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        ratingTxt = findViewById(R.id.ratingTxt);
        picImg = findViewById(R.id.picImg);
        backBtn = findViewById(R.id.backBtn);
        wifiTxt = findViewById(R.id.wifiTxt);
        priceTxt = findViewById(R.id.priceTxt);
    }

    private void setVariable() {
        item = (PopularDomain) getIntent().getSerializableExtra("object");

        titleTxt.setText(item.getTitle());
        ratingTxt.setText(String.valueOf(item.getRating()));
        locationTxt.setText(item.getLocation());
        descriptionTxt.setText(item.getDescription());

        guideTxt.setText(item.isGuide() ? "Guide" : "No-Guide");
        wifiTxt.setText(item.isWifi() ? "Wifi" : "No-Wifi");
        priceTxt.setText(String.valueOf(item.getPrice()));

        int drawableResId = getResources().getIdentifier(item.getPic(), "drawable", getPackageName());
        Glide.with(this).load(drawableResId).into(picImg);

        backBtn.setOnClickListener(v -> finish());
    }
}

package com.example.connectingislamabad.Activities.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.connectingislamabad.Domains.PopularDomain;
import com.example.connectingislamabad.R;

public class DetailActivity extends AppCompatActivity {
    //intializing textview
    private TextView titleTxt, locationTxt, bedTxt, guideTxt, wifiTxt, descriptionTxt, ratingTxt;

    //calling popular domain
    private PopularDomain item;

    //intilaitzing ImageView
    private ImageView picImg, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        setVariable();
    }

    private void initView() {
        titleTxt = findViewById(R.id.titleTxt);
        locationTxt = findViewById(R.id.locationTxt);
        bedTxt = findViewById(R.id.bedTxt);
        guideTxt = findViewById(R.id.guideTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        ratingTxt = findViewById(R.id.ratingTxt);
        picImg = findViewById(R.id.picImg);
        backBtn = findViewById(R.id.backBtn);
        wifiTxt = findViewById(R.id.wifiTxt);
    }


    // adding data to the xml design
    private void setVariable() {
        item = (PopularDomain) getIntent().getSerializableExtra("object");

        titleTxt.setText(item.getTitle());
        ratingTxt.setText(""+(float) item.getRating());
        locationTxt.setText(item.getLocation());
        //BedTxt is not initialized
        descriptionTxt.setText(item.getDescription());

        if (item.isGuide()) {
            guideTxt.setText("Guide");
        } else {
            guideTxt.setText("No-Guide");
        }

        if (item.isWifi()) {
            wifiTxt.setText("Wifi");
        } else {
            wifiTxt.setText("No-Wifi");
        }
        int drawableResId = getResources().getIdentifier(item.getPic(), "drawable", getPackageName());

        Glide.with(this)
                .load(drawableResId)
                .into(picImg);

        //Backbutton Click
        backBtn.setOnClickListener(v -> finish());

        //Finding View By Id Items

    }
}
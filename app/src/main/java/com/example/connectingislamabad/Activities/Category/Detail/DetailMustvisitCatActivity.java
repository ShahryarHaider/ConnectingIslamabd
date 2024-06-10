package com.example.connectingislamabad.Activities.Category.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.connectingislamabad.Activities.Profile.EditProfileActivity;
import com.example.connectingislamabad.Activities.Setting.SettingActivity;
import com.example.connectingislamabad.Domains.MuseumCatDomain;
import com.example.connectingislamabad.R;
import com.example.connectingislamabad.RatingActivity;

public class DetailMustvisitCatActivity extends AppCompatActivity {

    private TextView titleTxt,ratingTxt, locationTxt, firstTxt, secondTxt, thirdTxt, fourthTxt, descriptionTxt;
    private ImageView picImg, backBtn;

    private Button directionBtn, ratingBtn;
    private MuseumCatDomain item;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mustvisit_cat);
        initView();
        setVariable();


        ratingBtn = findViewById(R.id.ratingBtn);

        ratingBtn.setOnClickListener(v -> {
            // Start RatingActivity
            startActivity(new Intent(DetailMustvisitCatActivity.this, RatingActivity.class));
        });

    }

    private void setVariable() {

        item = (MuseumCatDomain)  getIntent().getSerializableExtra("object");

        titleTxt.setText(item.getTitleTxt());
        descriptionTxt.setText(item.getDescriptionTxt());
        locationTxt.setText(item.getLocationTxt());
        ratingTxt.setText(item.getRatingTxt());
        firstTxt.setText(item.getFirstTxt());
        secondTxt.setText(item.getSecondTxt());
        thirdTxt.setText(item.getThirdTxt());
        fourthTxt.setText(item.getFourthTxt());

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

        backBtn.setOnClickListener(v -> finish());

    }

    private void initView() {
        titleTxt = findViewById(R.id.titleTxt);
        locationTxt = findViewById(R.id.locationTxt);
        ratingTxt = findViewById(R.id.ratingTxt);

        firstTxt = findViewById(R.id.firstTxt);
        secondTxt = findViewById(R.id.secondTxt);
        thirdTxt = findViewById(R.id.thirdTxt);
        fourthTxt = findViewById(R.id.fourthTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);

        picImg = findViewById(R.id.picImg);
        backBtn = findViewById(R.id.backBtn);

        directionBtn = findViewById(R.id.directionBtn);

    }
}
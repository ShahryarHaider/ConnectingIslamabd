package com.example.connectingislamabad.Activities.Category.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.connectingislamabad.Activities.Authentication.Api.RestClient;
import com.example.connectingislamabad.Adapters.Review;
import com.example.connectingislamabad.Domains.MuseumCatDomain;
import com.example.connectingislamabad.MapFragment;
import com.example.connectingislamabad.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class DetailMuseumCatActivity extends AppCompatActivity implements OnMapReadyCallback {

    private TextView titleTxt, ratingTxt, locationTxt, firstTxt, secondTxt, thirdTxt, fourthTxt, descriptionTxt;
    private ImageView picImg, backBtn;
    private Button directionBtn;
    private MuseumCatDomain item;
    private TextView reviewTextView;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_museum_cat);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        initView();
        setVariable();

        reviewTextView = findViewById(R.id.reviewTextView);

        // Make the API call and display the review information
        RestClient restClient = new RestClient(this);
        restClient.fetchReviews(new RestClient.Callback() {
            @Override
            public void onResponse(List<Review> reviews) {
                System.out.println(reviews);
                // Ensure UI updates are done on the main thread
                new Handler(Looper.getMainLooper()).post(() -> {
                    if (reviews != null && !reviews.isEmpty()) {
                        StringBuilder reviewText = new StringBuilder();
                        for (Review review : reviews) {
                            reviewText.append(review.toString()).append("\n\n");
                        }
                        reviewTextView.setText(reviewText.toString());
                    } else {
                        reviewTextView.setText("No reviews available.");
                    }
                });
            }

            @Override
            public void onFailure() {
                // Handle failure case
                new Handler(Looper.getMainLooper()).post(() -> {
                    reviewTextView.setText("Failed to load reviews.");
                });
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng location = new LatLng(33.7749, 72.4194);
        mMap.addMarker(new MarkerOptions().position(location).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }

    private void setVariable() {
        item = (MuseumCatDomain) getIntent().getSerializableExtra("object");

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

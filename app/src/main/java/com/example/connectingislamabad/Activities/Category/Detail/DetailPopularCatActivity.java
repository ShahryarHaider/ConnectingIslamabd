package com.example.connectingislamabad.Activities.Category.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.connectingislamabad.Activities.Authentication.Api.RestClient;
import com.example.connectingislamabad.Adapters.Review;
import com.example.connectingislamabad.Adapters.ReviewAdapter;
import com.example.connectingislamabad.Domains.MuseumCatDomain;
import com.example.connectingislamabad.Domains.PopularDomain;
import com.example.connectingislamabad.R;
import com.example.connectingislamabad.RatingActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailPopularCatActivity extends AppCompatActivity implements OnMapReadyCallback {
    private FirebaseFirestore db;

    private TextView titleTxt, locationTxt, guideTxt, wifiTxt, descriptionTxt, ratingTxt, priceTxt,reviewTextView;
    private ImageView picImg, backBtn;
    private PopularDomain item;

    private Button ratingBtn,directionBtn;
    private GoogleMap mMap;

    private ReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_popular_cat);

        initView();
        setVariable();

        String collection = item.getCollection_name();
        String documentN = item.getDocument_name();

        db = FirebaseFirestore.getInstance();

        db.collection(collection).document(documentN).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.contains("counter")) {
                    double currentRating = document.getDouble("currentRating");
                    String formattedRating = String.format("%.1f", currentRating);
                    ratingTxt.setText(formattedRating);

                    // Fetch total reviews count from counter field
                    Map<String, Object> reviewData = document.getData();
                    List<Map<String, Object>> allReviews = new ArrayList<>();

                    long counter = document.getLong("counter");

                    for (int i = 1; i <= counter; i++) {
                        Map<String, Object> review = (Map<String, Object>) reviewData.get("Review" + i);
                        allReviews.add(review);
                    }

                    adapter = new ReviewAdapter(allReviews);

                    RecyclerView recyclerView = findViewById(R.id.view_rating_popular);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this)); // 'this' is the Context
                    recyclerView.setAdapter(adapter);



                } else {
                    Log.d("Rating", "No such document");
                }
            } else {
                Log.d("Rating", "get failed with ", task.getException());
            }
        });



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync( this);



//        reviewTextView = findViewById(R.id.reviewTextView);
//
//        // Make the API call and display the review information
//        RestClient restClient = new RestClient(this);
//        restClient.fetchReviews(new RestClient.Callback() {
//            @Override
//            public void onResponse(List<Review> reviews) {
//                System.out.println(reviews);
//                // Ensure UI updates are done on the main thread
//                new Handler(Looper.getMainLooper()).post(() -> {
//                    if (reviews != null && !reviews.isEmpty()) {
//                        StringBuilder reviewText = new StringBuilder();
//                        for (Review review : reviews) {
//                            reviewText.append(review.toString()).append("\n\n");
//                        }
//                        reviewTextView.setText(reviewText.toString());
//                    } else {
//                        reviewTextView.setText("No reviews available.");
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure() {
//                // Handle failure case
//                new Handler(Looper.getMainLooper()).post(() -> {
//                    reviewTextView.setText("Failed to load reviews.");
//                });
//            }
//        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        item = (PopularDomain)  getIntent().getSerializableExtra("object");
        mMap = googleMap;
        LatLng location = new LatLng(item.getLatitude(), item.getLongitude());
        mMap.addMarker(new MarkerOptions().position(location).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
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
        ratingBtn = findViewById(R.id.ratingBtn);
        directionBtn = findViewById(R.id.directionBtn);
    }

    private void setVariable() {
        item = (PopularDomain) getIntent().getSerializableExtra("object");

        titleTxt.setText(item.getTitle());
        //ratingTxt.setText(String.valueOf(item.getRating()));
        locationTxt.setText(item.getLocation());
        descriptionTxt.setText(item.getDescription());

        guideTxt.setText(item.isGuide() ? "Guide" : "No-Guide");
        wifiTxt.setText(item.isWifi() ? "Wifi" : "No-Wifi");
        priceTxt.setText(String.valueOf(item.getPrice()));

        int drawableResId = getResources().getIdentifier(item.getPic(), "drawable", getPackageName());
        Glide.with(this).load(drawableResId).into(picImg);

        backBtn.setOnClickListener(v -> finish());


        // SHOWS DATA OF REVIEWS AND RATING
        ratingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailPopularCatActivity.this, RatingActivity.class);
                intent.putExtra("collection_name", item.getCollection_name());
                intent.putExtra("document_name", item.getDocument_name());
                intent.putExtra("action", "set");
                startActivity(intent);
            }
        });
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
    }
}

package com.example.connectingislamabad.Activities.Transport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.example.connectingislamabad.Activities.Category.Detail.DetailMuseumCatActivity;
import com.example.connectingislamabad.Adapters.Review;
import com.example.connectingislamabad.Adapters.ReviewAdapter;
import com.example.connectingislamabad.Domains.PopularDomain;
import com.example.connectingislamabad.Domains.TransportDomain;
import com.example.connectingislamabad.R;
import com.example.connectingislamabad.RatingActivity;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DetailActivityTransport extends AppCompatActivity {

    private FirebaseFirestore db;
    //TextView
    private TextView titleTxt, descTxt,ratingTxt,reviewTextView;

    //Calling Transport Domain
    private TransportDomain item;

    //ImageView
    private ImageView picImg, backBtn, routeImg;

    private Button ratingBtn;
    private ReviewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transport);


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

                    RecyclerView recyclerView = findViewById(R.id.view_rating_transport);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this)); // 'this' is the Context
                    recyclerView.setAdapter(adapter);



                } else {
                    Log.d("Rating", "No such document");
                }
            } else {
                Log.d("Rating", "get failed with ", task.getException());
            }
        });




        reviewTextView = findViewById(R.id.reviewTextView);

        // Make the API call and display the review information
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
            private void initView() {
                titleTxt = findViewById(R.id.titleTxt);
                descTxt = findViewById(R.id.descTxt);
                backBtn = findViewById(R.id.backBtn);
                picImg = findViewById(R.id.picImg);
                routeImg = findViewById(R.id.routeImg);
                ratingBtn = findViewById(R.id.ratingBtn);
                ratingTxt = findViewById(R.id.ratingTxt);
                //Contains Only Text Data
            }

            private void setVariable() {
                item = (TransportDomain) getIntent().getSerializableExtra("object");
                titleTxt.setText(item.getTitleTxt());
                descTxt.setText(item.getDescTxt());



                int drawableResId = getResources().getIdentifier(item.getPicImg(), "drawable", getPackageName());

                Glide.with(this)
                        .load(drawableResId)
                        .into(picImg);

                int drawableRoutId = getResources().getIdentifier(item.getRouteImg(), "drawable", getPackageName());

                Glide.with(this)
                        .load(drawableRoutId)
                        .into(routeImg);

                //Backbutton Click
                backBtn.setOnClickListener(v -> finish());

                ratingBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DetailActivityTransport.this, RatingActivity.class);
                        intent.putExtra("collection_name", item.getCollection_name());
                        intent.putExtra("document_name", item.getDocument_name());
                        intent.putExtra("action", "set");
                        startActivity(intent);
                    }
                });

            }
        }

package com.example.connectingislamabad.ReviewTest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connectingislamabad.Adapters.ReviewAdapter;
import com.example.connectingislamabad.R;

import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private List<Review> reviewList;
    private EditText reviewInput;
    private RatingBar ratingBar;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        recyclerView = findViewById(R.id.recycler_view);
        reviewInput = findViewById(R.id.review_input);
        ratingBar = findViewById(R.id.rating_bar);
        submitButton = findViewById(R.id.submit_review);

//        reviewList = new ArrayList<>();
//        adapter = new ReviewAdapter(reviewList, this);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reviewText = reviewInput.getText().toString();
                int rating = (int) ratingBar.getRating();
                String username = "John Doe";

                Review review = new Review(rating, reviewText, username);
                reviewList.add(review);


                reviewInput.setText("");
                ratingBar.setRating(0);
                adapter.notifyDataSetChanged();
                Toast.makeText(ReviewActivity.this, "Review submitted!", Toast.LENGTH_SHORT).show();
            }
        });
//        adapter = new ReviewAdapter(reviewList);
//
//        recyclerView.setAdapter(adapter);
    }
}
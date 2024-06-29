package com.example.connectingislamabad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.Activities.Profile.EditProfileActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class RatingActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private FirebaseFirestore db;
    TextView rating_value, showRating;
    EditText review_text;

    private ImageView toolbar_image;
    double rateValue;
    String temp;
    RatingBar ratingBar;
    Button submit;

    String userName;

    String collection_name;

    String document_name;

    long reviewCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);



        rating_value = findViewById(R.id.rating_value);
        ratingBar = findViewById(R.id.rating_bar);
        review_text = findViewById(R.id.review_text);
        submit = findViewById(R.id.submit_btn);
        showRating = findViewById(R.id.showRating);
        toolbar_image = findViewById(R.id.toolbar_image);

        toolbar_image.setOnClickListener(v -> finish());

        Intent intent = getIntent();
        collection_name = intent.getStringExtra("collection_name");
        document_name = intent.getStringExtra("document_name");
        String action = intent.getStringExtra("action");
        String review = "";
        String rating = "";
        review = intent.getStringExtra("review");
        rating = intent.getStringExtra("rating");
        reviewCounter = intent.getLongExtra("reviewCount", 0);
        if(action.equals("update")) {
            ratingBar.setRating(Float.parseFloat(rating));
        }



        review_text.setText(review);


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        String userId = currentUser.getUid();
        db.collection("users").document(userId).get().addOnCompleteListener(task -> {
            System.out.println(collection_name);
            System.out.println(document_name);
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    userName = document.getString("name");

                } else {
                    Log.d("Rating", "No such document");
                }
            } else {
                Log.d("Rating", "get failed with ", task.getException());
            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue = ratingBar.getRating();

                if (rateValue<=1 && +rateValue>=0)
                    rating_value.setText("Bad" +rateValue+ " /5");
                else if (rateValue<=2 && +rateValue>=1)
                    rating_value.setText("Average" +rateValue+ " /5");
                else if (rateValue<=3 && +rateValue>=2)
                    rating_value.setText("Good" +rateValue+ " /5");
                else if (rateValue<=4 && +rateValue>=3)
                    rating_value.setText("Very Good" +rateValue+ " /5");
                else if (rateValue<=5 && +rateValue>=4)
                    rating_value.setText("Best" +rateValue+ " /5");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = rating_value.getText().toString();
                showRating.setText(" Your Rating : \n" +temp +"\n" + review_text.getText());
                String newReview = review_text.getText().toString();
                if(action.equals("set")){
                    addFoodReview(userName, rateValue, newReview);
                }
                else {
                    upadteFoodReview(userName,rateValue, newReview);
                }
                review_text.setText("");
                ratingBar.setRating(0);
                rating_value.setText("");



            }
        });

    }

    private void addFoodReview(String userName, double rating, String review) {
        DocumentReference foodDocRef = db.collection(collection_name).document(document_name);

        // Run a transaction to update the review and rating
        db.runTransaction(transaction -> {

            FirebaseUser currentUser = mAuth.getCurrentUser();
            String userId = currentUser.getUid();

            DocumentSnapshot snapshot = transaction.get(foodDocRef);

            double currentSumOfRatings = 0.0;
            long currentNumberOfRatings = 0;
            long counter = 0;

            if (snapshot.exists()) {
                currentSumOfRatings = snapshot.getDouble("sumOfRatings") != null ? snapshot.getDouble("sumOfRatings") : 0.0;
                currentNumberOfRatings = snapshot.getLong("numberOfRatings") != null ? snapshot.getLong("numberOfRatings") : 0;
                counter = snapshot.getLong("counter") != null ? snapshot.getLong("counter") : 0;
            }
            System.out.println(currentSumOfRatings);
            System.out.println(rating);

            counter++;
            long newNumberOfRatings = currentNumberOfRatings + 1;
            float newSumOfRatings = (float) (currentSumOfRatings + rating);
            float newCurrentRating = (float) (newSumOfRatings / newNumberOfRatings);
            String userRating = String.format("%.1f", rating);


            Map<String, Object> reviewData = new HashMap<>();
            reviewData.put("userId", userId);
            reviewData.put("userName", userName);
            reviewData.put("review", review);
            reviewData.put("timestamp", FieldValue.serverTimestamp());
            reviewData.put("userRating", userRating);
            reviewData.put("reviewCount", counter);
            reviewData.put("collection_name", collection_name);
            reviewData.put("document_name", document_name);



            Map<String, Object> reviewRating = new HashMap<>();
            reviewRating.put("counter", counter);
            reviewRating.put("sumOfRatings", (0.0+newSumOfRatings));
            reviewRating.put("numberOfRatings", newNumberOfRatings);
            reviewRating.put("currentRating", (0.0+newCurrentRating));
            reviewRating.put("Review" + counter, reviewData);

            // Update or set the document with new values
            transaction.set(foodDocRef, reviewRating, SetOptions.merge());

            return null;
        }).addOnSuccessListener(aVoid -> {
            Log.d("MainActivity", "Review and rating added successfully.");
            Toast.makeText(RatingActivity.this, "Review added successfully!", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Log.w("MainActivity", "Error adding review and rating", e);
            Toast.makeText(RatingActivity.this, "Failed to add review: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void upadteFoodReview(String userName, double rating, String review) {
        DocumentReference foodDocRef = db.collection(collection_name).document(document_name);

        // Run a transaction to update the review and rating
        db.runTransaction(transaction -> {

            FirebaseUser currentUser = mAuth.getCurrentUser();
            String userId = currentUser.getUid();

            DocumentSnapshot snapshot = transaction.get(foodDocRef);

            double currentSumOfRatings = 0.0;
            long currentNumberOfRatings = 0;

            if (snapshot.exists()) {
                currentSumOfRatings = snapshot.getDouble("sumOfRatings") != null ? snapshot.getDouble("sumOfRatings") : 0.0;
                currentNumberOfRatings = snapshot.getLong("numberOfRatings") != null ? snapshot.getLong("numberOfRatings") : 0;
            }
            System.out.println(currentSumOfRatings);
            System.out.println(rating);

            long newNumberOfRatings = currentNumberOfRatings + 1;
            float newSumOfRatings = (float) (currentSumOfRatings + rating);
            float newCurrentRating = (float) (newSumOfRatings / newNumberOfRatings);
            String userRating = String.format("%.1f", rating);


            Map<String, Object> reviewData = new HashMap<>();
            reviewData.put("userId", userId);
            reviewData.put("userName", userName);
            reviewData.put("review", review);
            reviewData.put("timestamp", FieldValue.serverTimestamp());
            reviewData.put("userRating", userRating);
            reviewData.put("reviewCount", reviewCounter);
            reviewData.put("collection_name", collection_name);
            reviewData.put("document_name", document_name);



            Map<String, Object> reviewRating = new HashMap<>();
            reviewRating.put("counter", reviewCounter);
            reviewRating.put("sumOfRatings", (0.0+newSumOfRatings));
            reviewRating.put("numberOfRatings", newNumberOfRatings);
            reviewRating.put("currentRating", (0.0+newCurrentRating));
            reviewRating.put("Review" + reviewCounter, reviewData);

            // Update or set the document with new values
            transaction.set(foodDocRef, reviewRating, SetOptions.merge());

            return null;
        }).addOnSuccessListener(aVoid -> {
            Log.d("MainActivity", "Review and rating added successfully.");
            Toast.makeText(RatingActivity.this, "Review added successfully!", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Log.w("MainActivity", "Error adding review and rating", e);
            Toast.makeText(RatingActivity.this, "Failed to add review: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }


}
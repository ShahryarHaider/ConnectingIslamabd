package com.example.connectingislamabad.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.connectingislamabad.R;
import com.example.connectingislamabad.RatingActivity;
import com.example.connectingislamabad.ReviewTest.Review;
import com.example.connectingislamabad.ReviewTest.ReviewActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.Map;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {



    private List<Map<String, Object>> reviews;

    public FirebaseAuth mAuth;

    public ReviewAdapter(List<Map<String, Object>> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        String userId = currentUser.getUid();

        Map<String, Object> reviewData = reviews.get(position);
        String reviewText = (String) reviewData.get("review");
        String username = (String) reviewData.get("userName");
        String rating = (String) reviewData.get("userRating");
        String reviewUserId = (String) reviewData.get("userId");
        String collectionName = (String) reviewData.get("collection_name");
        String documentName = (String) reviewData.get("document_name");
        long reviewCounter = (long) reviewData.get("reviewCount");
        holder.textViewReview.setText(reviewText);
        holder.reviewTxt.setText(username);
        holder.ratingTxt.setText(rating);

        holder.review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reviewUserId.equals(userId)) {

                    Intent intent = new Intent(holder.itemView.getContext(), RatingActivity.class);
                    intent.putExtra("collection_name", collectionName);
                    intent.putExtra("document_name", documentName);
                    intent.putExtra("rating", rating);
                    intent.putExtra("review", reviewText);
                    intent.putExtra("action", "update");
                    intent.putExtra("reviewCount", reviewCounter);

                    // Optionally, you can pass data to RatingActivity using intent extras
                    // intent.putExtra("key", value);

                    // Start the activity
                    holder.itemView.getContext().startActivity(intent);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewReview;
        public TextView reviewTxt;

        public TextView ratingTxt;

        public LinearLayout review;
        public ReviewViewHolder(View itemView) {
            super(itemView);
            textViewReview = itemView.findViewById(R.id.reviewTxt);
            reviewTxt = itemView.findViewById(R.id.nameTxt);
            ratingTxt = itemView.findViewById(R.id.ratingTxt);
            review = itemView.findViewById(R.id.review);
        }
    }
}

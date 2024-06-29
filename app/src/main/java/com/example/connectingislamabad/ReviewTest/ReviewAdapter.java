//package com.example.connectingislamabad.ReviewTest;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RatingBar;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.connectingislamabad.R;
//import com.example.connectingislamabad.ReviewTest.Review;
//
//import java.util.List;
//
//public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
//    List<Review> reviewList;
//    private Context context;
//
//    public ReviewAdapter(List<Review> reviewList, Context context) {
//        this.reviewList = reviewList;
//        this.context = context;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        Review review = reviewList.get(position);
//        holder.reviewText.setText(review.getReviewText());
//        holder.ratingBar.setRating(review.getRating());
//        holder.username.setText(review.getUsername());
//    }
//
//    @Override
//    public int getItemCount() {
//        return reviewList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView reviewText, username;
//        public RatingBar ratingBar;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            reviewText = itemView.findViewById(R.id.review_text);
//            ratingBar = itemView.findViewById(R.id.rating_bar);
//            username = itemView.findViewById(R.id.username);
//        }
//    }
//}
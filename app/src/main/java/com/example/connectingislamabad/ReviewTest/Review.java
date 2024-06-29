package com.example.connectingislamabad.ReviewTest;

public class Review {
    private int rating;
    private String reviewText;
    private String username;

    public Review(int rating, String reviewText, String username) {
    }

    // getters and setters

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package com.example.connectingislamabad.Adapters;

public class Review {
    private String userId;
    private double rating;
    private String comment;

    public Review(String userId, double rating, String comment) {
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    // Getters and setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

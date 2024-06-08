package com.example.connectingislamabad.Domains;

import com.example.connectingislamabad.Adapters.Review;

import java.util.ArrayList;
import java.util.List;

public class PopularDomain {
    private String title;
    private String location;
    private String description;
    private boolean guide;
    private double rating;
    private List<Review> reviews; // List of reviews
    private String pic;
    private boolean wifi;
    private int price;

    public PopularDomain(String title, String location, String description, boolean guide, double rating, String pic, boolean wifi, int price) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.guide = guide;
        this.rating = rating;
        this.reviews = new ArrayList<>(); // Initialize reviews list
        this.pic = pic;
        this.wifi = wifi;
        this.price = price;
    }

    // Getters and setters for existing fields


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isGuide() {
        return guide;
    }

    public void setGuide(boolean guide) {
        this.guide = guide;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
        // Recalculate overall rating when a new review is added
        double totalRating = 0;
        for (Review r : reviews) {
            totalRating += r.getRating();
        }
        this.rating = totalRating / reviews.size();
    }


}

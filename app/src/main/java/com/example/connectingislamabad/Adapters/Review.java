package com.example.connectingislamabad.Adapters;

public class Review {

    private Long id;

    private Long placeId;

    private Float currentRating;

    private Float sumOfRatings;

    private Integer numberOfRatings;

    // Change userReview to array type
    private String[] reviews;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Float getCurrentRating() {
        return currentRating;
    }

    public void setCurrentRating(Float currentRating) {
        this.currentRating = currentRating;
    }

    public Float getSumOfRatings() {
        return sumOfRatings;
    }

    public void setSumOfRatings(Float sumOfRatings) {
        this.sumOfRatings = sumOfRatings;
    }

    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(Integer numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public String[] getReview() {
        return reviews;
    }

    public void setReview(String[] Review) {
        this.reviews = Review;
    }
}
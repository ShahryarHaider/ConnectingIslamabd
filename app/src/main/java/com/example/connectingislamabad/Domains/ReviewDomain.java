package com.example.connectingislamabad.Domains;

public class ReviewDomain {

    private String reviewText;

    private String userName;

    private String dateTime;

    public ReviewDomain(String reviewText, String userName, String dateTime) {
        this.reviewText = reviewText;
        this.userName = userName;
        this.dateTime = dateTime;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}

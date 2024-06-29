package com.example.connectingislamabad.Domains;

import java.io.Serializable;

public class MuseumCatDomain implements Serializable {
    private String titleTxt;
    private String picImg;
    private String locationTxt;
    private String ratingTxt;
    private String firstTxt;
    private String secondTxt;
    private String thirdTxt;
    private String fourthTxt;
    private String descriptionTxt;
    private String direction_btn;

    private String collection_name;
    private String document_name;

    // For Maps
    private double latitude;
    private double longitude;

    public MuseumCatDomain(String titleTxt, String picImg, String locationTxt, String ratingTxt, String firstTxt, String secondTxt, String thirdTxt, String fourthTxt, String descriptionTxt, String direction_btn  ,double latitude, double longitude, String document_name, String collection_name) {
        this.titleTxt = titleTxt;
        this.picImg = picImg;
        this.locationTxt = locationTxt;
        this.ratingTxt = ratingTxt;
        this.firstTxt = firstTxt;
        this.secondTxt = secondTxt;
        this.thirdTxt = thirdTxt;
        this.fourthTxt = fourthTxt;
        this.descriptionTxt = descriptionTxt;
        this.direction_btn = direction_btn;
        this.document_name = document_name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.collection_name = collection_name;
    }

    public String getCollection_name() {
        return collection_name;
    }

    public void setCollection_name(String collection_name) {
        this.collection_name = collection_name;
    }

    public String getDocument_name() {
        return document_name;
    }

    public void setDocument_name(String document_name) {
        this.document_name = document_name;
    }

    public String getTitleTxt() {
        return titleTxt;
    }

    public void setTitleTxt(String titleTxt) {
        this.titleTxt = titleTxt;
    }

    public String getPicImg() {
        return picImg;
    }

    public void setPicImg(String picImg) {
        this.picImg = picImg;
    }

    public String getLocationTxt() {
        return locationTxt;
    }

    public void setLocationTxt(String locationTxt) {
        this.locationTxt = locationTxt;
    }

    public String getRatingTxt() {
        return ratingTxt;
    }

    public void setRatingTxt(String ratingTxt) {
        this.ratingTxt = ratingTxt;
    }

    public String getFirstTxt() {
        return firstTxt;
    }

    public void setFirstTxt(String firstTxt) {
        this.firstTxt = firstTxt;
    }

    public String getSecondTxt() {
        return secondTxt;
    }

    public void setSecondTxt(String secondTxt) {
        this.secondTxt = secondTxt;
    }

    public String getThirdTxt() {
        return thirdTxt;
    }

    public void setThirdTxt(String thirdTxt) {
        this.thirdTxt = thirdTxt;
    }

    public String getFourthTxt() {
        return fourthTxt;
    }

    public void setFourthTxt(String fourthTxt) {
        this.fourthTxt = fourthTxt;
    }

    public String getDescriptionTxt() {
        return descriptionTxt;
    }

    public void setDescriptionTxt(String descriptionTxt) {
        this.descriptionTxt = descriptionTxt;
    }

    public String getDirection_btn() {
        return direction_btn;
    }

    public void setDirection_btn(String direction_btn) {
        this.direction_btn = direction_btn;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

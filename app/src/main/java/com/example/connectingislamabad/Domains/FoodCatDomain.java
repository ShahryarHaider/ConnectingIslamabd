package com.example.connectingislamabad.Domains;

import java.io.Serializable;
import java.util.List;

public class FoodCatDomain implements Serializable {

    private String titleTxt;
    private String picImg;
    private String locationTxt;
    private String ratingTxt;
    private String dineTxt;
    private String typeTxt;
    private String wifiTxt;
    private String descriptionTxt;
    private String food_pic;

    private String collection_name;
    private String document_name;

    private double latitude;

    private double longitude;

    private String direction_btn;

    private List<String> type;


    public FoodCatDomain(String titleTxt, String picImg, String locationTxt, String ratingTxt, String dineTxt, String typeTxt, String wifiTxt, String descriptionTxt, String food_pic, String direction_btn, String document_name, double latitude, double longitude, String collection_name, List<String> type) {
        this.titleTxt = titleTxt;
        this.picImg = picImg;
        this.locationTxt = locationTxt;
        this.ratingTxt = ratingTxt;
        this.dineTxt = dineTxt;
        this.typeTxt = typeTxt;
        this.wifiTxt = wifiTxt;
        this.descriptionTxt = descriptionTxt;
        this.food_pic = food_pic;
        this.document_name = document_name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.direction_btn = direction_btn;
        this.collection_name = collection_name;
        this.type = type;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getDocument_name() {
        return document_name;
    }

    public void setDocument_name(String document_name) {
        this.document_name = document_name;
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

    public String getDineTxt() {
        return dineTxt;
    }

    public void setDineTxt(String dineTxt) {
        this.dineTxt = dineTxt;
    }

    public String getTypeTxt() {
        return typeTxt;
    }

    public void setTypeTxt(String typeTxt) {
        this.typeTxt = typeTxt;
    }

    public String getWifiTxt() {
        return wifiTxt;
    }

    public void setWifiTxt(String wifiTxt) {
        this.wifiTxt = wifiTxt;
    }

    public String getDescriptionTxt() {
        return descriptionTxt;
    }

    public void setDescriptionTxt(String descriptionTxt) {
        this.descriptionTxt = descriptionTxt;
    }

    public String getFood_pic() {
        return food_pic;
    }

    public void setFood_pic(String food_pic) {
        this.food_pic = food_pic;
    }

    public String getDirection_btn() {
        return direction_btn;
    }

    public void setDirection_btn(String direction_btn) {
        this.direction_btn = direction_btn;
    }

    public String getCollection_name() {
        return collection_name;
    }

    public void setCollection_name(String collection_name) {
        this.collection_name = collection_name;
    }
}

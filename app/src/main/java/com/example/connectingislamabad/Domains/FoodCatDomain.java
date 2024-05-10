package com.example.connectingislamabad.Domains;

import java.io.Serializable;

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

    public FoodCatDomain(String titleTxt, String picImg, String locationTxt, String ratingTxt, String dineTxt, String typeTxt, String wifiTxt, String descriptionTxt, String food_pic) {
        this.titleTxt = titleTxt;
        this.picImg = picImg;
        this.locationTxt = locationTxt;
        this.ratingTxt = ratingTxt;
        this.dineTxt = dineTxt;
        this.typeTxt = typeTxt;
        this.wifiTxt = wifiTxt;
        this.descriptionTxt = descriptionTxt;
        this.food_pic = food_pic;
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
}

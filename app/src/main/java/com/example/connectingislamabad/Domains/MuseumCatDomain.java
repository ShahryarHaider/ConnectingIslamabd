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

    public MuseumCatDomain(String titleTxt, String picImg, String locationTxt, String ratingTxt, String firstTxt, String secondTxt, String thirdTxt, String fourthTxt, String descriptionTxt, String direction_btn) {
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
}

package com.example.connectingislamabad.Domains;

import java.io.Serializable;

public class TransportDomain implements Serializable {

    private String titleTxt;
    private String descTxt;
    private String picImg;
    private String routeImg;

    public TransportDomain(String titleTxt, String descTxt, String picImg, String routeImg) {
        this.titleTxt = titleTxt;
        this.descTxt =  descTxt;
        this.picImg =   picImg;
        this.routeImg = routeImg;
    }

    public String getTitleTxt() {
        return titleTxt;
    }

    public void setTitleTxt(String titleTxt) {
        this.titleTxt = titleTxt;
    }

    public String getDescTxt() {
        return descTxt;
    }

    public void setDescTxt(String descTxt) {
        this.descTxt = descTxt;
    }

    public String getPicImg() {
        return picImg;
    }

    public void setPicImg(String picImg) {
        this.picImg = picImg;
    }

    public String getRouteImg() {
        return routeImg;
    }

    public void setRouteImg(String routeImg) {
        this.routeImg = routeImg;
    }
}

package com.example.connectingislamabad.Domains;

import java.io.Serializable;

public class TransportDomain implements Serializable {

    private String titleTxt;
    private String descTxt;
    private String picImg;
    private String routeImg;

    private String collection_name;
    private String document_name;

    public TransportDomain(String titleTxt, String descTxt, String picImg, String routeImg, String document_name, String collection_name) {
        this.titleTxt = titleTxt;
        this.descTxt =  descTxt;
        this.picImg =   picImg;
        this.routeImg = routeImg;
        this.document_name = document_name;
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

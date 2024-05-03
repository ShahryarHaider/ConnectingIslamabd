package com.example.connectingislamabad.Domains;

public class TransportDomain {

    private String titleTxt;
    private String picImg;
    private String picBg;

    public TransportDomain(String titleTxt, String picImg, String picBg) {
        this.titleTxt = titleTxt;
        this.picImg = picImg;
        this.picBg = picBg;
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

    public String getPicBg() {
        return picBg;
    }

    public void setPicBg(String picBg) {
        this.picBg = picBg;
    }
}

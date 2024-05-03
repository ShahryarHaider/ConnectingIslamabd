package com.example.connectingislamabad.Domains;

import java.io.Serializable;

public class PopularDomain implements Serializable {

    private String title;
    private String location;
    private String description;
    //Bed not Required
    private boolean guide;
    private double rating;
    private String pic;
    private boolean wifi;
    private int price;

    //Constructor
    public PopularDomain(String title, String location, String description, boolean guide, double rating, String pic, boolean wifi, int price) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.guide = guide;
        this.rating = rating;
        this.pic = pic;
        this.wifi = wifi;
        this.price = price;
    }

    //Getter And Setter
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
}
package com.example.connectingislamabad.Domains;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CategoryDomain implements Parcelable {

    private String title;
    private String picPath;

    public CategoryDomain(String title, String picPath) {
        this.title = title;
        this.picPath = picPath;
    }

    protected CategoryDomain(Parcel in) {
        title = in.readString();
        picPath = in.readString();
    }

    public static final Creator<CategoryDomain> CREATOR = new Creator<CategoryDomain>() {
        @Override
        public CategoryDomain createFromParcel(Parcel in) {
            return new CategoryDomain(in);
        }

        @Override
        public CategoryDomain[] newArray(int size) {
            return new CategoryDomain[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(picPath);
    }
}

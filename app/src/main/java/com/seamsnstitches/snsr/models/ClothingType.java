package com.seamsnstitches.snsr.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ClothingType extends DefaultEntity implements Parcelable {

    public static final Creator<ClothingType> CREATOR = new Creator<ClothingType>() {
        @Override
        public ClothingType createFromParcel(Parcel in) {
            return new ClothingType(in);
        }

        @Override
        public ClothingType[] newArray(int size) {
            return new ClothingType[size];
        }
    };
    @SerializedName("name")
    private String name;
    @SerializedName("gender")
    private  Gender gender;
    @SerializedName("imageURL")
    private String imageURL;

    public enum Gender {Male, Female, Unisex}

    @SerializedName("webURL")
    private String webURL;

    protected ClothingType(Parcel in) {
        name = in.readString();
        imageURL = in.readString();
        webURL = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(imageURL);
        parcel.writeString(webURL);
    }

    public String getName() {
        return name;
    }

    public ClothingType setName(String name) {
        this.name = name;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public ClothingType setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public ClothingType setImageURL(String imageURL) {

        this.imageURL = imageURL;

        return this;
    }

    public String getWebURL() {
        return webURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }
}

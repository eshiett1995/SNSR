package com.seamsnstitches.snsr.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TrouserMeasurement extends DefaultEntity implements Parcelable {

    //@OneToOne(mappedBy = "trouserMeasurement")
    //private User customer;

    public static final Creator<TrouserMeasurement> CREATOR = new Creator<TrouserMeasurement>() {
        @Override
        public TrouserMeasurement createFromParcel(Parcel in) {
            return new TrouserMeasurement(in);
        }

        @Override
        public TrouserMeasurement[] newArray(int size) {
            return new TrouserMeasurement[size];
        }
    };
    @SerializedName("fullLength")
    private double fullLength;
    @SerializedName("waist")
    private double waist;
    @SerializedName("knee")
    private double knee;
    @SerializedName("hip")
    private double hip;
    @SerializedName("thigh")
    private double thigh;
    @SerializedName("ankle")
    private double ankle;
    @SerializedName("shortsLength")
    private double shortsLength;

    protected TrouserMeasurement(Parcel in) {
        fullLength = in.readDouble();
        waist = in.readDouble();
        knee = in.readDouble();
        hip = in.readDouble();
        thigh = in.readDouble();
        ankle = in.readDouble();
        shortsLength = in.readDouble();
    }

    /**   public User getUser() {
     return customer;
     }

     public void setUser(User user) {
     this.customer = user;
     }

     **/
    public double getFullLength() {
        return fullLength;
    }

    public void setFullLength(double fullLength) {
        this.fullLength = fullLength;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getKnee() {
        return knee;
    }

    public void setKnee(double knee) {
        this.knee = knee;
    }

    public double getHip() {
        return hip;
    }

    public void setHip(double hip) {
        this.hip = hip;
    }

    public double getThigh() {
        return thigh;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public double getAnkle() {
        return ankle;
    }

    public void setAnkle(double ankle) {
        this.ankle = ankle;
    }

    public double getShortsLength() {
        return shortsLength;
    }

    public void setShortsLength(double shortsLength) {
        this.shortsLength = shortsLength;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(fullLength);
        parcel.writeDouble(waist);
        parcel.writeDouble(knee);
        parcel.writeDouble(hip);
        parcel.writeDouble(thigh);
        parcel.writeDouble(ankle);
        parcel.writeDouble(shortsLength);
    }
}


package com.seamsnstitches.snsr.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ShirtMeasurement extends DefaultEntity implements Parcelable {

    //@OneToOne(mappedBy = "shirtMeasurement")
    //private User customer;

    public static final Creator<ShirtMeasurement> CREATOR = new Creator<ShirtMeasurement>() {
        @Override
        public ShirtMeasurement createFromParcel(Parcel in) {
            return new ShirtMeasurement(in);
        }

        @Override
        public ShirtMeasurement[] newArray(int size) {
            return new ShirtMeasurement[size];
        }
    };
    @SerializedName("topLength")
    private double topLength;
    @SerializedName("chest")
    private double chest;
    @SerializedName("shoulder")
    private double shoulder;
    @SerializedName("sleeveLength")
    private double sleeveLength;
    @SerializedName("shortSleeveLength")
    private double shortSleeveLength;
    @SerializedName("neck")
    private double neck;
    @SerializedName("collar")
    private double collar;
    @SerializedName("roundWrist")
    private double roundWrist;

    protected ShirtMeasurement(Parcel in) {
        topLength = in.readDouble();
        chest = in.readDouble();
        shoulder = in.readDouble();
        sleeveLength = in.readDouble();
        shortSleeveLength = in.readDouble();
        neck = in.readDouble();
        collar = in.readDouble();
        roundWrist = in.readDouble();
    }

    /**public User getCustomer() {
     return customer;
     }

     public void setCustomer(User customer) {
     this.customer = customer;
     } **/

    public double getTopLength() {
        return topLength;
    }

    public void setTopLength(double topLength) {
        this.topLength = topLength;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getShoulder() {
        return shoulder;
    }

    public void setShoulder(double shoulder) {
        this.shoulder = shoulder;
    }

    public double getShortSleeveLength() {
        return shortSleeveLength;
    }

    public void setShortSleeveLength(double shortSleeveLength) {
        this.shortSleeveLength = shortSleeveLength;
    }

    public double getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(double sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    public double getNeck() {
        return neck;
    }

    public void setNeck(double neck) {
        this.neck = neck;
    }

    public double getCollar() {
        return collar;
    }

    public void setCollar(double collar) {
        this.collar = collar;
    }

    public double getRoundWrist() {
        return roundWrist;
    }

    public void setRoundWrist(double roundWrist) {
        this.roundWrist = roundWrist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(topLength);
        parcel.writeDouble(chest);
        parcel.writeDouble(shoulder);
        parcel.writeDouble(sleeveLength);
        parcel.writeDouble(shortSleeveLength);
        parcel.writeDouble(neck);
        parcel.writeDouble(collar);
        parcel.writeDouble(roundWrist);
    }
}

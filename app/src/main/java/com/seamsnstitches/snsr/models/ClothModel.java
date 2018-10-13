package com.seamsnstitches.snsr.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ClothModel extends DefaultEntity implements Parcelable {


    public static final Creator<ClothModel> CREATOR = new Creator<ClothModel>() {
        @Override
        public ClothModel createFromParcel(Parcel in) {
            return new ClothModel(in);
        }

        @Override
        public ClothModel[] newArray(int size) {
            return new ClothModel[size];
        }
    };
    @SerializedName("clothingType")
    private ClothingType clothingType;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("amount")
    private double amount;
    @SerializedName("modelStructure")
    private String modelStructure;
    @SerializedName("savedUrl")
    private String savedUrl;

    protected ClothModel(Parcel in) {
        clothingType = in.readParcelable(ClothingType.class.getClassLoader());
        quantity = in.readInt();
        amount = in.readDouble();
        modelStructure = in.readString();
        savedUrl = in.readString();
    }

    public ClothingType getClothingType() {

        return clothingType;

    }

    public ClothModel setClothingType(ClothingType clothingType) {

        this.clothingType = clothingType;

        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public ClothModel setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public ClothModel setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public String getModelStructure() {
        return modelStructure;
    }

    public ClothModel setModelStructure(String modelStructure) {

        this.modelStructure = modelStructure;

        return this;
    }

    public String getSavedUrl() {
        return savedUrl;
    }

    public void setSavedUrl(String savedUrl) {
        this.savedUrl = savedUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(clothingType, i);
        parcel.writeInt(quantity);
        parcel.writeDouble(amount);
        parcel.writeString(modelStructure);
        parcel.writeString(savedUrl);
    }
}


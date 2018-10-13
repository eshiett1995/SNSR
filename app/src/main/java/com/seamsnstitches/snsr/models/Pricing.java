package com.seamsnstitches.snsr.models;

import com.google.gson.annotations.SerializedName;

public class Pricing extends  DefaultEntity{

    @SerializedName("clothingType")
    private ClothingType clothingType;

    @SerializedName("price")
    private  double price;

    @SerializedName("fashionBrand")
    private FashionBrand fashionBrand;

    public ClothingType getClothingType() {
        return clothingType;
    }

    public Pricing setClothingType(ClothingType clothingType) {
        this.clothingType = clothingType;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Pricing setPrice(double price) {
        this.price = price;
        return this;
    }

    public FashionBrand getFashionBrand() {
        return fashionBrand;
    }

    public Pricing setFashionBrand(FashionBrand fashionBrand) {
        this.fashionBrand = fashionBrand;
        return this;
    }
}

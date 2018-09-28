package com.seamsnstitches.snsr.models;

public class Pricing extends  DefaultEntity{

    private ClothingType clothingType;

    private  double price;


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

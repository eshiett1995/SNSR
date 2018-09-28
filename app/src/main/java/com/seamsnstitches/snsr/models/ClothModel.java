package com.seamsnstitches.snsr.models;



public class ClothModel extends DefaultEntity {



    private ClothingType clothingType;

    private int quantity;

    private double amount;

    private String modelStructure;

    private String savedUrl;


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
}


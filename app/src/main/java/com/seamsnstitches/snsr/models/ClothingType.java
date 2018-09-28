package com.seamsnstitches.snsr.models;

public class ClothingType extends DefaultEntity {

    public enum Gender {Male, Female, Unisex}

    private String name;

    private  Gender gender;

    private String imageURL;

    private String webURL;

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

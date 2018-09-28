package com.seamsnstitches.snsr.models;


public class Employee extends DefaultEntity {


    public enum POSITION {ADMIN, NON_ADMIN}

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean Activated = false;

    private POSITION position;

    private FashionBrand fashionBrand;

    private double rating;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FashionBrand getFashionBrand() {
        return fashionBrand;
    }

    public void setFashionBrand(FashionBrand fashionBrand) {
        this.fashionBrand = fashionBrand;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public POSITION getPosition() {
        return position;
    }

    public void setPosition(POSITION position) {
        this.position = position;
    }

    public boolean isActivated() {
        return Activated;
    }

    public Employee setActivated(boolean activated) {

        Activated = activated;

        return this;
    }
}


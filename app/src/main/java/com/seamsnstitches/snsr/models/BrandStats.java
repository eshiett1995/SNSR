package com.seamsnstitches.snsr.models;



public class BrandStats {

    private int totalOrders;

    private double rating;

    private int noEmployees;

    public int getTotalOrders() {

        return totalOrders;

    }

    public BrandStats setTotalOrders(int totalOrders) {

        this.totalOrders = totalOrders;

        return this;
    }

    public double getRating() {

        return rating;

    }

    public BrandStats setRating(double rating) {

        this.rating = rating;

        return this;
    }

    public int getNoEmployees() {

        return noEmployees;

    }

    public BrandStats setNoEmployees(int noEmployees) {

        this.noEmployees = noEmployees;

        return this;
    }
}

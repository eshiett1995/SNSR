package com.seamsnstitches.snsr.models;


import java.util.ArrayList;
import java.util.List;

public class FashionBrand extends DefaultEntity {

    private String brandName;

    private String image;

    private String website;

    private String email;

    private String password;

    private String phoneNumber;

    private String brandPhrase;

    private FashionBrandBankDetail bankDetail;

    private GpsCoordinate gpsCoordinate;

    private BrandStats stats;

    private List<Pricing> pricing;

    private Double rating;

    private String location;

    private List<Orders> orders = new ArrayList<>();

    private List<Employee> employees = new ArrayList<>();

    public FashionBrandBankDetail getBankDetail() {

        return bankDetail;
    }

    public FashionBrand setBankDetail(FashionBrandBankDetail bankDetail) {

        this.bankDetail = bankDetail;

        return this;
    }

    public GpsCoordinate getGpsCoordinate() {

        return gpsCoordinate;
    }

    public FashionBrand setGpsCoordinate(GpsCoordinate gpsCoordinate) {

        this.gpsCoordinate = gpsCoordinate;

        return this;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public FashionBrand setEmployees(Employee employees) {

        this.employees.add(employees);

        return this;
    }

    public String getBrandName() {
        return brandName;
    }

    public FashionBrand setBrandName(String brandName) {

        this.brandName = brandName;

        return this;
    }

    public String getImage() {
        return image;
    }

    public FashionBrand setImage(String image) {

        this.image = image;

        return this;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public FashionBrand setLocation(String location) {

        this.location = location;

        return this;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public FashionBrand setOrders(List<Orders> orders) {
        this.orders = orders;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public FashionBrand setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public FashionBrand setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getBrandPhrase() {
        return brandPhrase;
    }

    public FashionBrand setBrandPhrase(String brandPhrase) {
        this.brandPhrase = brandPhrase;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public FashionBrand setWebsite(String website) {
        this.website = website;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public FashionBrand setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;

        return this;
    }

    public BrandStats getStats() {

        return stats;
    }

    public FashionBrand setStats(BrandStats stats) {

        this.stats = stats;

        return this;
    }

    public List<Pricing> getPricing() {
        return pricing;
    }

    public FashionBrand setPricing(List<Pricing> pricing) {
        this.pricing = pricing;
        return this;
    }
}

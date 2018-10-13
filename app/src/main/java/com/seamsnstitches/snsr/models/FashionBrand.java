package com.seamsnstitches.snsr.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FashionBrand implements Parcelable {

    public static final Creator<FashionBrand> CREATOR = new Creator<FashionBrand>() {
        @Override
        public FashionBrand createFromParcel(Parcel in) {
            return new FashionBrand(in);
        }

        @Override
        public FashionBrand[] newArray(int size) {
            return new FashionBrand[size];
        }
    };
    @SerializedName("brandName")
    private String brandName;
    @SerializedName("image")
    private String image;
    @SerializedName("website")
    private String website;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("brandPhrase")
    private String brandPhrase;
    @SerializedName("bankDetail")
    private FashionBrandBankDetail bankDetail;
    @SerializedName("gpsCoordinate")
    private GpsCoordinate gpsCoordinate;
    @SerializedName("stats")
    private BrandStats stats;
    @SerializedName("pricing")
    private List<Pricing> pricing;
    @SerializedName("rating")
    private Double rating;
    @SerializedName("location")
    private String location;
    @SerializedName("orders")
    private List<Orders> orders = new ArrayList<>();
    @SerializedName("employees")
    private List<Employee> employees = new ArrayList<>();
    @SerializedName("id")
    private long id;

    protected FashionBrand(Parcel in) {
        brandName = in.readString();
        image = in.readString();
        website = in.readString();
        email = in.readString();
        password = in.readString();
        phoneNumber = in.readString();
        brandPhrase = in.readString();
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readDouble();
        }
        location = in.readString();
        orders = in.createTypedArrayList(Orders.CREATOR);
        employees = in.createTypedArrayList(Employee.CREATOR);
        id = in.readLong();
    }

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

    public FashionBrand() {
        super();
    }

    public long getId() {
        return this.id;
        //return super.getId();
    }


    public void setId(long id) {
        this.id = id;
        //super.setId(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(brandName);
        parcel.writeString(image);
        parcel.writeString(website);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(phoneNumber);
        parcel.writeString(brandPhrase);
        if (rating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(rating);
        }
        parcel.writeString(location);
        parcel.writeTypedList(orders);
        parcel.writeTypedList(employees);
        parcel.writeLong(id);
    }
}

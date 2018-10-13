package com.seamsnstitches.snsr.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Orders extends DefaultEntity implements Parcelable {

    public static final Creator<Orders> CREATOR = new Creator<Orders>() {
        @Override
        public Orders createFromParcel(Parcel in) {
            return new Orders(in);
        }

        @Override
        public Orders[] newArray(int size) {
            return new Orders[size];
        }
    };
    @SerializedName("fashionBrand")
    private FashionBrand fashionBrand;
    @SerializedName("amount")
    private double amount;
    @SerializedName("clothModels")
    private List<ClothModel> clothModels = new ArrayList<>();
    @SerializedName("status")
    private Enum.Status status;
    @SerializedName("user")
    private User user;
    @SerializedName("tasks")
    private List<Task> tasks = new ArrayList<>();

    protected Orders(Parcel in) {
        amount = in.readDouble();
        user = in.readParcelable(User.class.getClassLoader());
    }

    public List<Task> getTasks() {

        return tasks;
    }

    public Orders setTasks(List<Task> tasks) {

        this.tasks = tasks;

        return this;
    }

    public List<ClothModel> getClothModels() {
        return clothModels;
    }

    public Orders setClothModels(List<ClothModel> clothModels) {

        this.clothModels = clothModels;
        return this;
    }

    public Orders setClothModels(ClothModel clothModel) {

        this.clothModels.add(clothModel);

        return this;
    }


    public FashionBrand getFashionBrand() {
        return fashionBrand;
    }

    public Orders setFashionBrand(FashionBrand fashionBrand) {
        this.fashionBrand = fashionBrand;
        return this;
    }

    public Enum.Status getStatus() {

        return status;

    }

    public Orders setStatus(Enum.Status status) {

        this.status = status;

        return this;
    }

    public double getAmount() {

        return amount;

    }

    public Orders setAmount(double amount) {

        this.amount = amount;

        return this;

    }


    public User getUser() {
        return user;
    }

    public Orders setUser(User user) {

        this.user = user;

        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(amount);
        parcel.writeParcelable(user, i);
    }
}

package com.seamsnstitches.snsr.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Transaction extends DefaultEntity implements Parcelable {

    public Transaction() {

    }

    public Transaction(User user, double amount, boolean isSuccessful) {
        this.user = user;
        this.amount = amount;
        this.isSuccessful = isSuccessful;
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };
    @SerializedName("user")
    private User user;
    @SerializedName("amount")
    private double amount;
    @SerializedName("isSuccessful")
    private boolean isSuccessful;
    @SerializedName("orders")
    private Orders orders;

    protected Transaction(Parcel in) {
        user = in.readParcelable(User.class.getClassLoader());
        amount = in.readDouble();
        isSuccessful = in.readByte() != 0;
    }

    public User getUser() {

        return user;

    }

    public Transaction setUser(User user) {

        this.user = user;

        return this;

    }

    public double getAmount() {

        return amount;
    }

    public Transaction setAmount(double amount) {

        this.amount = amount;

        return this;

    }

    public boolean isSuccessful() {

        return isSuccessful;

    }

    public Transaction setSuccessful(boolean successful) {

        isSuccessful = successful;

        return this;
    }

    public Orders getOrders() {
        return orders;
    }

    public Transaction setOrders(Orders orders) {

        this.orders = orders;

        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(user, i);
        parcel.writeDouble(amount);
        parcel.writeByte((byte) (isSuccessful ? 1 : 0));
    }
}


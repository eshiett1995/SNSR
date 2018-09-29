package com.seamsnstitches.snsr.models;

public class Transaction extends DefaultEntity {

    public Transaction() {

    }

    public Transaction(User user, double amount, boolean isSuccessful) {
        this.user = user;
        this.amount = amount;
        this.isSuccessful = isSuccessful;
    }

    private User user;

    private double amount;

    private boolean isSuccessful;

    private Orders orders;

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
}


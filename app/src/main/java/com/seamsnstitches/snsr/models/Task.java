package com.seamsnstitches.snsr.models;

import com.google.gson.annotations.SerializedName;

public class Task extends DefaultEntity{

    @SerializedName("orders")
    private Orders orders;

    @SerializedName("details")
    private String details;

    @SerializedName("completed")
    private boolean completed;

    @SerializedName("employee")
    private Employee employee;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

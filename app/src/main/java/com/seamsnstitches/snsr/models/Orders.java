package com.seamsnstitches.snsr.models;

import java.util.ArrayList;
import java.util.List;

public class Orders extends DefaultEntity {

    private FashionBrand fashionBrand;

    private double amount;

    private List<ClothModel> clothModels = new ArrayList<>();

    private Enum.Status status;

    private User user;

    private List<Task> tasks = new ArrayList<>();

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
}

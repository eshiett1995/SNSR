package com.seamsnstitches.snsr.models;

public class TrouserMeasurement extends DefaultEntity {

    //@OneToOne(mappedBy = "trouserMeasurement")
    //private User customer;

    private double fullLength;

    private double waist;

    private double knee;

    private double hip;

    private double thigh;

    private double ankle;

    private double shortsLength;


    /**   public User getUser() {
     return customer;
     }

     public void setUser(User user) {
     this.customer = user;
     }

     **/
    public double getFullLength() {
        return fullLength;
    }

    public void setFullLength(double fullLength) {
        this.fullLength = fullLength;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getKnee() {
        return knee;
    }

    public void setKnee(double knee) {
        this.knee = knee;
    }

    public double getHip() {
        return hip;
    }

    public void setHip(double hip) {
        this.hip = hip;
    }

    public double getThigh() {
        return thigh;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public double getAnkle() {
        return ankle;
    }

    public void setAnkle(double ankle) {
        this.ankle = ankle;
    }

    public double getShortsLength() {
        return shortsLength;
    }

    public void setShortsLength(double shortsLength) {
        this.shortsLength = shortsLength;
    }

}


package com.seamsnstitches.snsr.models;


public class ShirtMeasurement extends DefaultEntity {

    //@OneToOne(mappedBy = "shirtMeasurement")
    //private User customer;

    private double topLength;

    private double chest;

    private double shoulder;

    private double sleeveLength;

    private double shortSleeveLength;

    private double neck;

    private double collar;

    private double roundWrist;

    /**public User getCustomer() {
     return customer;
     }

     public void setCustomer(User customer) {
     this.customer = customer;
     } **/

    public double getTopLength() {
        return topLength;
    }

    public void setTopLength(double topLength) {
        this.topLength = topLength;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getShoulder() {
        return shoulder;
    }

    public void setShoulder(double shoulder) {
        this.shoulder = shoulder;
    }

    public double getShortSleeveLength() {
        return shortSleeveLength;
    }

    public void setShortSleeveLength(double shortSleeveLength) {
        this.shortSleeveLength = shortSleeveLength;
    }

    public double getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(double sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    public double getNeck() {
        return neck;
    }

    public void setNeck(double neck) {
        this.neck = neck;
    }

    public double getCollar() {
        return collar;
    }

    public void setCollar(double collar) {
        this.collar = collar;
    }

    public double getRoundWrist() {
        return roundWrist;
    }

    public void setRoundWrist(double roundWrist) {
        this.roundWrist = roundWrist;
    }
}

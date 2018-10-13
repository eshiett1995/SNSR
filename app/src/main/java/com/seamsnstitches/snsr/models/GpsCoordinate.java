package com.seamsnstitches.snsr.models;


import com.google.gson.annotations.SerializedName;

public class GpsCoordinate extends DefaultEntity {

    @SerializedName("Latitude")
    private double Latitude;

    @SerializedName("Longitude")
    private double Longitude;

    public double getLatitude() {

        return Latitude;

    }

    public GpsCoordinate setLatitude(double latitude) {

        Latitude = latitude;

        return this;

    }

    public double getLongitude() {

        return Longitude;

    }

    public GpsCoordinate setLongitude(double longitude) {

        Longitude = longitude;

        return this;

    }
}

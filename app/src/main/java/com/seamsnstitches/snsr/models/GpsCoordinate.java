package com.seamsnstitches.snsr.models;



public class GpsCoordinate extends DefaultEntity {

    private double Latitude;

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

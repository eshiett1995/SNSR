package com.seamsnstitches.snsr.models;

public class UserGpsCoordinate extends DefaultEntity {

    private double Latitude;

    private double Longitude;

    public double getLatitude() {

        return Latitude;

    }

    public UserGpsCoordinate setLatitude(double latitude) {

        Latitude = latitude;

        return this;

    }

    public double getLongitude() {

        return Longitude;

    }

    public UserGpsCoordinate setLongitude(double longitude) {

        Longitude = longitude;

        return this;

    }

}


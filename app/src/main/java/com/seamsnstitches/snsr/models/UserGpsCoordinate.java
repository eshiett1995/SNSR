package com.seamsnstitches.snsr.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserGpsCoordinate extends DefaultEntity implements Parcelable {

    public static final Creator<UserGpsCoordinate> CREATOR = new Creator<UserGpsCoordinate>() {
        @Override
        public UserGpsCoordinate createFromParcel(Parcel in) {
            return new UserGpsCoordinate(in);
        }

        @Override
        public UserGpsCoordinate[] newArray(int size) {
            return new UserGpsCoordinate[size];
        }
    };
    @SerializedName("Latitude")
    private double Latitude;
    @SerializedName("Longitude")
    private double Longitude;

    protected UserGpsCoordinate(Parcel in) {
        Latitude = in.readDouble();
        Longitude = in.readDouble();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(Latitude);
        parcel.writeDouble(Longitude);
    }
}


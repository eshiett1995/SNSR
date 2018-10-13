package com.seamsnstitches.snsr.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User extends DefaultEntity implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String email;
    @SerializedName("address")
    private String address;
    @SerializedName("isActivated")
    private boolean isActivated = false;
    @SerializedName("userGpsCoordinate")
    private UserGpsCoordinate userGpsCoordinate;
    @SerializedName("trouserMeasurement")
    private TrouserMeasurement trouserMeasurement;
    @SerializedName("shirtMeasurement")
    private ShirtMeasurement shirtMeasurement;

    protected User(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        phoneNumber = in.readString();
        password = in.readString();
        email = in.readString();
        address = in.readString();
        isActivated = in.readByte() != 0;
        userGpsCoordinate = in.readParcelable(UserGpsCoordinate.class.getClassLoader());
        trouserMeasurement = in.readParcelable(TrouserMeasurement.class.getClassLoader());
        shirtMeasurement = in.readParcelable(ShirtMeasurement.class.getClassLoader());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getPassword() {

        return password;

    }

    public void setPassword(String password) {

        this.password = password;

    }

    public String getEmail() {

        return email;

    }

    public void setEmail(String email) {

        this.email = email;

    }

    public String getAddress() {

        return address;

    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ShirtMeasurement getShirtMeasurement() {
        return shirtMeasurement;
    }

    public void setShirtMeasurement(ShirtMeasurement shirtMeasurement) {
        this.shirtMeasurement = shirtMeasurement;
    }

    public TrouserMeasurement getTrouserMeasurement() {
        return trouserMeasurement;
    }

    public void setTrouserMeasurement(TrouserMeasurement trouserMeasurement) {
        this.trouserMeasurement = trouserMeasurement;
    }

    public boolean isActivated() {

        return isActivated;

    }

    public User setActivated(boolean activated) {

        isActivated = activated;

        return this;

    }

    public UserGpsCoordinate getUserGpsCoordinate() {
        return userGpsCoordinate;
    }

    public User setUserGpsCoordinate(UserGpsCoordinate userGpsCoordinate) {
        this.userGpsCoordinate = userGpsCoordinate;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(phoneNumber);
        parcel.writeString(password);
        parcel.writeString(email);
        parcel.writeString(address);
        parcel.writeByte((byte) (isActivated ? 1 : 0));
        parcel.writeParcelable(userGpsCoordinate, i);
        parcel.writeParcelable(trouserMeasurement, i);
        parcel.writeParcelable(shirtMeasurement, i);
    }
}

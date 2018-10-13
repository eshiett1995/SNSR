package com.seamsnstitches.snsr.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Employee extends DefaultEntity implements Parcelable {


    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean Activated = false;

    private POSITION position;

    private FashionBrand fashionBrand;

    private double rating;

    public Employee() {

    }

    protected Employee(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        password = in.readString();
        Activated = in.readByte() != 0;
        rating = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeByte((byte) (Activated ? 1 : 0));
        parcel.writeDouble(rating);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FashionBrand getFashionBrand() {
        return fashionBrand;
    }

    public void setFashionBrand(FashionBrand fashionBrand) {
        this.fashionBrand = fashionBrand;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public POSITION getPosition() {
        return position;
    }

    public void setPosition(POSITION position) {
        this.position = position;
    }

    public boolean isActivated() {
        return Activated;
    }

    public Employee setActivated(boolean activated) {

        Activated = activated;

        return this;
    }

    public enum POSITION {ADMIN, NON_ADMIN}
}


package com.seamsnstitches.snsr.models.api.request;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginModel implements Parcelable {

    private String email;

    private String password;

    public static final Creator<LoginModel> CREATOR = new Creator<LoginModel>() {
        @Override
        public LoginModel createFromParcel(Parcel in) {
            return new LoginModel(in);
        }

        @Override
        public LoginModel[] newArray(int size) {
            return new LoginModel[size];
        }
    };

    public LoginModel() {

    }

    protected LoginModel(Parcel in) {
        email = in.readString();
        password = in.readString();
    }

    public String getEmail() {
        return email;
    }

    public LoginModel setEmail(String email) {

        this.email = email;

        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginModel setPassword(String password) {

        this.password = password;

        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(password);
    }
}

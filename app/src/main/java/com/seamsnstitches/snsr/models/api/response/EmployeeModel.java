package com.seamsnstitches.snsr.models.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.seamsnstitches.snsr.models.Employee;

public class EmployeeModel implements Parcelable {

    public EmployeeModel(Employee employee, ResponseModel responseModel) {

        this.employee = employee;

        this.responseModel = responseModel;

    }

    public static final Creator<EmployeeModel> CREATOR = new Creator<EmployeeModel>() {
        @Override
        public EmployeeModel createFromParcel(Parcel in) {
            return new EmployeeModel(in);
        }

        @Override
        public EmployeeModel[] newArray(int size) {
            return new EmployeeModel[size];
        }
    };
    @SerializedName("employee")
    private Employee employee;
    @SerializedName("responseModel")
    private ResponseModel responseModel;

    protected EmployeeModel(Parcel in) {
        employee = in.readParcelable(Employee.class.getClassLoader());
    }

    public Employee getEmployee() {

        return employee;
    }

    public void setEmployee(Employee employee) {

        this.employee = employee;
    }

    public ResponseModel getResponseModel() {
        return responseModel;
    }

    public void setResponseModel(ResponseModel responseModel) {
        this.responseModel = responseModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(employee, i);
    }
}

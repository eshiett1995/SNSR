package com.seamsnstitches.snsr.models.api.response;

import com.seamsnstitches.snsr.models.Employee;

public class EmployeeModel {

    public EmployeeModel(Employee employee, ResponseModel responseModel) {

        this.employee = employee;

        this.responseModel = responseModel;

    }

    private Employee employee;

    private ResponseModel responseModel;

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
}

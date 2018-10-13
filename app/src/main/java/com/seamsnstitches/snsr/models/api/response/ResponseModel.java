package com.seamsnstitches.snsr.models.api.response;


import com.google.gson.annotations.SerializedName;

public class ResponseModel {


    public ResponseModel(){}

    public ResponseModel(Boolean isSuccessful, String responseMessage){

        this.isSuccessful = isSuccessful;

        this.responseMessage = responseMessage;

    }


    @SerializedName("isSuccessful")
    public Boolean isSuccessful;

    @SerializedName("responseMessage")
    public String responseMessage;

    public Boolean getIsSuccessful() {
        return isSuccessful;
    }

    public ResponseModel setIsSuccessful(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
        return this;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public ResponseModel setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
        return this;
    }
}

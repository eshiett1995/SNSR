package com.seamsnstitches.snsr.models.api.response;


import com.google.gson.annotations.SerializedName;

public class GenericPageResponseModel {


    public GenericPageResponseModel(GenericPageModel genericPageModel, ResponseModel responseModel) {

        this.genericPageModel = genericPageModel;

        this.responseModel = responseModel;

    }

    @SerializedName("genericPageModel")
    private GenericPageModel genericPageModel;

    @SerializedName("responseModel")
    private ResponseModel responseModel;


    public GenericPageModel getGenericPageModel() {

        return genericPageModel;

    }

    public void setGenericPageModel(GenericPageModel genericPageModel) {

        this.genericPageModel = genericPageModel;

    }

    public ResponseModel getResponseModel() {

        return responseModel;

    }

    public void setResponseModel(ResponseModel responseModel) {

        this.responseModel = responseModel;

    }

}

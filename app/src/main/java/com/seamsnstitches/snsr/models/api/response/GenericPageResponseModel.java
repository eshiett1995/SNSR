package com.seamsnstitches.snsr.models.api.response;


public class GenericPageResponseModel {


    public GenericPageResponseModel(GenericPageModel genericPageModel, ResponseModel responseModel) {

        this.genericPageModel = genericPageModel;

        this.responseModel = responseModel;

    }

    private GenericPageModel genericPageModel;

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

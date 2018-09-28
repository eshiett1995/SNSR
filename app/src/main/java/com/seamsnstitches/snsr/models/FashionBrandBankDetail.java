package com.seamsnstitches.snsr.models;


public class FashionBrandBankDetail extends DefaultEntity{

    private String bankName;

    private String accountName;

    private String accountNumber;

    public String getBankName() {

        return bankName;
    }

    public FashionBrandBankDetail setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getAccountName() {

        return accountName;

    }

    public FashionBrandBankDetail setAccountName(String accountName) {

        this.accountName = accountName;

        return this;
    }

    public String getAccountNumber() {

        return accountNumber;

    }


    public FashionBrandBankDetail setAccountNumber(String accountNumber) {

        this.accountNumber = accountNumber;

        return this;
    }


}

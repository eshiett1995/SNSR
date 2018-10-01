package com.seamsnstitches.snsr.models.api.request;

import com.seamsnstitches.snsr.models.FashionBrand;

import java.util.Date;

public class TransactionsSearchParams {

    private int pageNumber;

    private String email;

    private Date  date;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {

        this.date = date;
    }

}


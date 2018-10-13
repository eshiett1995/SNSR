package com.seamsnstitches.snsr.models.api.response;

import com.google.gson.annotations.SerializedName;
import com.seamsnstitches.snsr.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class GenericPageModel<T> {

    @SerializedName("currentPageNumber")
    private int currentPageNumber;

    @SerializedName("getCurrentPageSize")
    private int getCurrentPageSize;

    @SerializedName("totalNumberOfElements")
    private int totalNumberOfElements;

    @SerializedName("totalNumberOfPages")
    private int totalNumberOfPages;

    @SerializedName("content")
    private List<Transaction> content = new ArrayList<>();

    @SerializedName("isFirst")
    private boolean isFirst;

    @SerializedName("isLast")
    private boolean isLast;

    @SerializedName("hasNext")
    private boolean hasNext;


    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public GenericPageModel setCurrentPageNumber(int currentPageNumber) {

        this.currentPageNumber = currentPageNumber;

        return this;
    }

    public int getGetCurrentPageSize() {
        return getCurrentPageSize;
    }

    public GenericPageModel setGetCurrentPageSize(int getCurrentPageSize) {

        this.getCurrentPageSize = getCurrentPageSize;

        return this;
    }

    public int getTotalNumberOfElements() {
        return totalNumberOfElements;
    }

    public GenericPageModel setTotalNumberOfElements(int totalNumberOfElements) {

        this.totalNumberOfElements = totalNumberOfElements;

        return this;
    }

    public int getTotalNumberOfPages() {
        return totalNumberOfPages;
    }

    public GenericPageModel setTotalNumberOfPages(int totalNumberOfPages) {

        this.totalNumberOfPages = totalNumberOfPages;

        return this;
    }

    public List<Transaction> getContent() {

        return content;

    }

    public GenericPageModel setContent(List<Transaction> content) {

        this.content = content;

        return this;
    }

    public boolean isFirst() {

        return isFirst;

    }

    public GenericPageModel setFirst(boolean first) {

        isFirst = first;

        return this;
    }

    public boolean isLast() {
        return isLast;
    }

    public GenericPageModel setLast(boolean last) {

        isLast = last;

        return this;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public GenericPageModel setHasNext(boolean hasNext) {

        this.hasNext = hasNext;

        return this;
    }
}


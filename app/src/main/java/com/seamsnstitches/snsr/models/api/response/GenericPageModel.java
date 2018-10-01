package com.seamsnstitches.snsr.models.api.response;

import java.util.ArrayList;
import java.util.List;

public class GenericPageModel<T> {

    private int currentPageNumber;

    private int getCurrentPageSize;

    private int totalNumberOfElements;

    private int totalNumberOfPages;

    private List<T> content = new ArrayList<>();

    private boolean isFirst;

    private boolean isLast;

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

    public List<T> getContent() {

        return content;

    }

    public GenericPageModel setContent(List<T> content) {

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


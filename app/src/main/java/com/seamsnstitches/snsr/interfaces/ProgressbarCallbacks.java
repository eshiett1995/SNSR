package com.seamsnstitches.snsr.interfaces;

/**
 * This interface is implemented in OrderFragment and its methods are called in TransactionViewModel
 */
public interface ProgressbarCallbacks {
    /**
     * call back method when method to load first set of data is called
     */
    void showFirstItemsLoadingProgressbar();

    /**
     * callBack method to be called when the call to get data from Api is made. This call back
     * stops the mainProgress bar
     */
    void stopFirstItemsLoadingProgressbar();

    /**
     * callBack method to be called when a request is made to load subsequent data from the Api,
     * This method shows the footer progressBar
     */
    void showNextItemsLoadingProgressbar();

    /**
     * callBack method to be called when request to get subsequent data from APi is made, this callBack
     * method, stops the footer progressBar
     */
    void stopNextItemsLoadingProgressbar();

    void displayToast(String toastMsg);
}
package com.seamsnstitches.snsr.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.seamsnstitches.snsr.Rest.ApiClient;
import com.seamsnstitches.snsr.Rest.ApiInterface;
import com.seamsnstitches.snsr.interfaces.ProgressbarCallbacks;
import com.seamsnstitches.snsr.models.api.request.TransactionsSearchParams;
import com.seamsnstitches.snsr.models.api.response.GenericPageModel;
import com.seamsnstitches.snsr.models.api.response.GenericPageResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionViewModels extends ViewModel {

    private static final int PAGE_START = 0;
    private static final String TAG = TransactionViewModels.class.getSimpleName();
    private static List<Transaction> transactionList = new ArrayList<>();
    private static ProgressbarCallbacks progressBarCallbacks;
    private LiveData<List<Transaction>> transactionListLiveData;
    private MutableLiveData<List<Transaction>> mutableLiveData;
    private int pageNumber = 1;
    private int totalPageNumber;
    private boolean isLastPage = false;
    private FashionBrand fashionBrand;

    public TransactionViewModels() {
        super();
    }

    public static void setProgressBarCallbacks(ProgressbarCallbacks progressBarCallbacks) {
        TransactionViewModels.progressBarCallbacks = progressBarCallbacks;
    }

    public void setFashionBrand(FashionBrand fashionBrand) {
        this.fashionBrand = fashionBrand;
    }

    public void getTransactionListBySearch() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
//        TransactionsSearchParams transactionsSearchParams = new TransactionsSearchParams();
//        transactionsSearchParams.setPageNumber(0);

        Call<GenericPageResponseModel> call = apiService
                .searchTransactions(fashionBrand.getId(), PAGE_START, new TransactionsSearchParams());

        if (call == null) return;
        call.enqueue(new Callback<GenericPageResponseModel>() {
            @Override
            public void onResponse(Call<GenericPageResponseModel> call, Response<GenericPageResponseModel> response) {
                Log.e(TAG, call.request().url().toString());
                progressBarCallbacks.stopFirstItemsLoadingProgressbar();
                GenericPageResponseModel responseModel = response.body();

                if (responseModel == null) {
                    progressBarCallbacks.stopFirstItemsLoadingProgressbar();
                    progressBarCallbacks.displayToast("ResponseModel IS NULL");
                    return;
                }
                GenericPageModel<Transaction> pageModel = responseModel.getGenericPageModel();
                Log.e(TAG, "pageNumber -- " + pageModel.getCurrentPageNumber() + "\n isFirst -- " +
                        pageModel.isFirst() + "\n ---- totalPageSize " + pageModel.getGetCurrentPageSize() +
                        "\n -- ListSize " + pageModel.getContent().size());
                List<Transaction> transactions = pageModel.getContent();
                if (transactions.isEmpty()) {
                    progressBarCallbacks.stopFirstItemsLoadingProgressbar();
                    progressBarCallbacks.displayToast("List is Empty");
                }
                transactionList = transactions;
                totalPageNumber = pageModel.getTotalNumberOfPages();
                mutableLiveData.setValue(transactions);
                if (pageNumber <= totalPageNumber) ;//mMovieAdapter.updateIsLoadingTrue();
                else isLastPage = pageModel.isLast();
            }

            @Override
            public void onFailure(Call<GenericPageResponseModel> call, Throwable t) {
                progressBarCallbacks.stopFirstItemsLoadingProgressbar();
                progressBarCallbacks.displayToast(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    /**
     * This gets the first items from the Api
     * @param fashionBrand fashionBrand
     */
    public void getFirstTransactionItem(FashionBrand fashionBrand) {
        progressBarCallbacks.showFirstItemsLoadingProgressbar();

        //TODO: When Api Call finishes whether successful or not.. call the callback method to
        //stop the progressbar
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
//        TransactionsSearchParams transactionsSearchParams = new TransactionsSearchParams();
//        transactionsSearchParams.setPageNumber(0);

        Call<GenericPageResponseModel> call = apiService
                .searchTransactions(1, PAGE_START, new TransactionsSearchParams());

        if (call == null) return;
        call.enqueue(new Callback<GenericPageResponseModel>() {
            @Override
            public void onResponse(Call<GenericPageResponseModel> call, Response<GenericPageResponseModel> response) {
                Log.e(TAG, call.request().url().toString());
                progressBarCallbacks.stopFirstItemsLoadingProgressbar();
                GenericPageResponseModel responseModel = response.body();

                if (responseModel == null) {
                    progressBarCallbacks.stopFirstItemsLoadingProgressbar();
                    progressBarCallbacks.displayToast("ResponseModel IS NULL");
                    return;
                }
                GenericPageModel<Transaction> pageModel = responseModel.getGenericPageModel();
                Log.e(TAG, "pageNumber -- " + pageModel.getCurrentPageNumber() + "\n isFirst -- " +
                        pageModel.isFirst() + "\n ---- totalPageSize " + pageModel.getGetCurrentPageSize() +
                        "\n -- ListSize " + pageModel.getContent().size());
                List<Transaction> transactions = pageModel.getContent();
                if (transactions.isEmpty()) {
                    progressBarCallbacks.stopFirstItemsLoadingProgressbar();
                    progressBarCallbacks.displayToast("List is Empty");
                }
                transactionList = transactions;
                totalPageNumber = pageModel.getTotalNumberOfPages();
                mutableLiveData.setValue(transactions);
                if (pageNumber <= totalPageNumber) ;//mMovieAdapter.updateIsLoadingTrue();
                else isLastPage = pageModel.isLast();
            }

            @Override
            public void onFailure(Call<GenericPageResponseModel> call, Throwable t) {
                progressBarCallbacks.stopFirstItemsLoadingProgressbar();
                progressBarCallbacks.displayToast(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void getNextTransactionItem(int pageNumber, FashionBrand fashionBrand) {
        progressBarCallbacks.showNextItemsLoadingProgressbar();
        this.pageNumber = pageNumber;

        //TODO: When Api Call finishes whether successful or not.. call the callback method to
        //stop the progressbar

        this.pageNumber = pageNumber;
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<GenericPageResponseModel> call = apiService
                .searchTransactions(fashionBrand.getId(), pageNumber, new TransactionsSearchParams());
        if (call == null) return;
        call.enqueue(new Callback<GenericPageResponseModel>() {
            @Override
            public void onResponse(Call<GenericPageResponseModel> call, Response<GenericPageResponseModel> response) {
                progressBarCallbacks.stopNextItemsLoadingProgressbar();
                GenericPageResponseModel responseModel = response.body();

                if (responseModel == null) return;

                GenericPageModel pageModel = responseModel.getGenericPageModel();
                List<Transaction> transactions = pageModel.getContent();
                if (transactions.isEmpty()) {
                    progressBarCallbacks.stopNextItemsLoadingProgressbar();
                    //progressBarCallbacks.displayToast("List is Empty");
                }
                totalPageNumber = pageModel.getTotalNumberOfPages();
                transactionList.addAll(transactions);
                mutableLiveData.setValue(transactionList);
//                if (TransactionViewModels.this.pageNumber <= totalPageNumber) ;//mMovieAdapter.updateIsLoadingTrue();
//                else isLastPage = pageModel.isLast();
                isLastPage = pageModel.isLast();
            }

            @Override
            public void onFailure(Call<GenericPageResponseModel> call, Throwable t) {
                progressBarCallbacks.stopNextItemsLoadingProgressbar();
            }
        });
    }

    public LiveData<List<Transaction>> getTransactionListLiveData(FashionBrand fashionBrand) {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            getFirstTransactionItem(fashionBrand);
        }
        return mutableLiveData;
    }

    public int getTotalPageNumber() {
        return totalPageNumber;
    }
}

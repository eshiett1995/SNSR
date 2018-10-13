package com.seamsnstitches.snsr.fragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.seamsnstitches.snsr.Adapter.TransactionAdapter;
import com.seamsnstitches.snsr.HomeActivity;
import com.seamsnstitches.snsr.R;
import com.seamsnstitches.snsr.databinding.FragmentOrdersBinding;
import com.seamsnstitches.snsr.interfaces.ProgressbarCallbacks;
import com.seamsnstitches.snsr.models.FashionBrand;
import com.seamsnstitches.snsr.models.Transaction;
import com.seamsnstitches.snsr.models.TransactionViewModels;
import com.seamsnstitches.snsr.utility.AppConstants;
import com.seamsnstitches.snsr.utility.CustomScrollListener;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment implements ProgressbarCallbacks {

    private static final String TAG = OrdersFragment.class.getSimpleName();
    private static final String CONNECTED = "connected!!",
            CONNECTING = "connecting...", NO_CONNECTION = "No Internet connection!";
    private static final int PAGE_START = 1;
    private FragmentOrdersBinding mFragmentOrdersBinding;
    private RecyclerView mRecyclerView;
    private TransactionAdapter mAdapter;
    private TransactionViewModels mTransactionViewModel;
    private BroadcastReceiver networkReceiver;
    private Snackbar mNetworkStatusSnackbar;
    private int disconnectedCount = 0;
    private LiveData<List<Transaction>> transactionLivedata;
    private Observer<List<Transaction>> livedataObsever;
    private int pageNumber = 1;
    private int totalPageNumber;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    private List<Transaction> transactionList = new ArrayList<>();
    private FashionBrand fashionBrand;

    public static OrdersFragment newInstance(Bundle args) {
        OrdersFragment ordersFragment = new OrdersFragment();
        ordersFragment.setArguments(args);
        return ordersFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //((AppCompatActivity)getActivity()).getSupportActionBar()..getMenu().findItem(R.id.action_search).getActionView().setVisibility(View.INVISIBLE);
        if (getArguments() != null && getArguments().containsKey(AppConstants.ORDERS_FRAGMENT_FASHION_BRAND)) {
            fashionBrand = getArguments().getParcelable(AppConstants.ORDERS_FRAGMENT_FASHION_BRAND);
        }
        ((HomeActivity) getActivity()).showSearchMenuItem();
        mTransactionViewModel = ViewModelProviders
                .of(this)
                .get(TransactionViewModels.class);
        TransactionViewModels.setProgressBarCallbacks(this);
        mFragmentOrdersBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_orders, container, false);
        mNetworkStatusSnackbar = Snackbar.make(mFragmentOrdersBinding.ordersFrameLayout, "", Snackbar.LENGTH_INDEFINITE);
        setUpNetworkReceiver();
        setUpRecyclerView();
        observeTransactionLiveData(fashionBrand);
        setUpSwipeRefreshAction();

        if (getActivity() != null)
            ((HomeActivity) getActivity()).showSearchMenuItem();
        return mFragmentOrdersBinding.getRoot();
    }

    /**
     * This method helps setting up the networkReceivers to receive network status
     */
    private void setUpNetworkReceiver() {
        networkReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //super.onReceive(context, intent);
                if (intent.getExtras() != null) {
                    ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getBaseContext()
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (connectivityManager == null) return;
                    NetworkInfo networkInfo =
                            connectivityManager.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        //Connected
                        if (disconnectedCount > 0) { //This if statement makes sure its only when a disconnection have occurred
                            //and its connected back that onConnected() is called to avoid calling Api multiple times
                            if (transactionList.isEmpty()) //Only If the list isEmpty should reload
                                onConnected();      //and disconnection occurs and its connected back
                            //should onConnected() be called to avoid reloading
                            //items if there network fluctuation occurs

                            Log.e(TAG, " ++++++ IS_CONNECTED ++++++++++  ");
                        }
                    } else if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTING) {
                        //Connecting
                        onConnecting();
                    } else {
                        //No connection
                        disconnectedCount++;
                        onDisconnected();
                    }
                }
            }
        };
    }

    private void setUpSwipeRefreshAction() {
        mFragmentOrdersBinding.rowOrderSwipeRefresh
                .setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        loadFirstTransactionItems();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        getActivity().registerReceiver(networkReceiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(networkReceiver);
    }

    /**
     * If network connected indicate that its connected using a snack bar
     */
    private void onConnected() {
        mFragmentOrdersBinding.mainProgress.setVisibility(View.VISIBLE);
        mNetworkStatusSnackbar.setText(CONNECTED);
        mNetworkStatusSnackbar.dismiss();
        //TODO: Remember to pass in correct parameter
        loadFirstTransactionItems();
    }

    /**
     * If network connecting indicate that its connecting using a snack bar
     */
    private void onConnecting() {

        mFragmentOrdersBinding.mainProgress.setVisibility(View.VISIBLE);
        mNetworkStatusSnackbar.setText(CONNECTING);
        mNetworkStatusSnackbar.show();
    }

    /**
     * If network is disconnected indicate that its disconnected using a snack bar
     */
    private void onDisconnected() {
        mFragmentOrdersBinding.mainProgress.setVisibility(View.GONE);
        mNetworkStatusSnackbar.setText(NO_CONNECTION);
        mNetworkStatusSnackbar.show();
    }

    /**
     * This method helps setup recyclerViews
     */
    private void setUpRecyclerView() {
        mRecyclerView = mFragmentOrdersBinding.mainRecycler;
        mAdapter = new TransactionAdapter(((AppCompatActivity) getActivity()), mFragmentOrdersBinding);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new CustomScrollListener(mLayoutManager) {
            @Override
            public void loadMoreItems() {
                pageNumber++;
                loadNextTransactionItems();
            }

            @Override
            public int getTotalPageCount() {
                return totalPageNumber;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    /**
     * This method is called to load the first item from Api
     */
    private void loadFirstTransactionItems() {
        mTransactionViewModel.getFirstTransactionItem(fashionBrand);
    }

    /**
     * This method is called to load subsequent items from Api
     */
    private void loadNextTransactionItems() {
        mTransactionViewModel.getNextTransactionItem(pageNumber, fashionBrand);
    }

    /**
     * This method helps observe changes made to the list, and update UI and
     * list accordingly
     */
    private void observeTransactionLiveData(FashionBrand fashionBrand) {
        //mTransactionViewModel.setFashionBrand(fashionBrand);
        Log.e(TAG, "\n\n********************************************************************");
        //Log.e(TAG, "-------------ObserveTransactionLiveData ooooooo -- Fashion - Id =" + fashionBrand.getId() + " ------- ");
        Log.e(TAG, "*********************************************************************\n\n");
        if (fashionBrand == null) {
            Log.e(TAG, "Fashion Brand is NULL OOOOOOH");
            return;
        }
        transactionLivedata =
                mTransactionViewModel.getTransactionListLiveData(fashionBrand);
        livedataObsever = new Observer<List<Transaction>>() {
            @Override
            public void onChanged(@Nullable List<Transaction> transactions) {
                if (transactions == null) return; //TODO show empty state

                if (transactions.isEmpty()) return; //TODO show empty state
                transactionList = transactions;
                totalPageNumber = mTransactionViewModel.getTotalPageNumber();
                if (pageNumber == PAGE_START) {
                    mAdapter.clearList();
                    mAdapter.addItemList(transactions);
                } else {
                    isLoading = false;
                }

                if (pageNumber <= totalPageNumber) ; //mMovieAdapter.updateIsLoadingTrue();
                else isLastPage = true;
            }
        };
        transactionLivedata.observe(this, livedataObsever);
    }

    @Override
    public void showFirstItemsLoadingProgressbar() {
        mFragmentOrdersBinding.mainProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopFirstItemsLoadingProgressbar() {
        mFragmentOrdersBinding.mainProgress.setVisibility(View.INVISIBLE);
        mFragmentOrdersBinding.rowOrderSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void showNextItemsLoadingProgressbar() {
        mFragmentOrdersBinding.mainFooterProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopNextItemsLoadingProgressbar() {
        mFragmentOrdersBinding.mainFooterProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void displayToast(String toastMsg) {
        Toast.makeText(getActivity(), toastMsg, Toast.LENGTH_SHORT).show();
    }
}

package com.seamsnstitches.snsr.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seamsnstitches.snsr.Adapter.TransactionAdapter;
import com.seamsnstitches.snsr.HomeActivity;
import com.seamsnstitches.snsr.R;
import com.seamsnstitches.snsr.databinding.FragmentOrdersBinding;
import com.seamsnstitches.snsr.models.ClothModel;

import java.util.ArrayList;

public class FinalTransactionFragment extends Fragment {

    private FragmentOrdersBinding mFragmentOrdersBinding;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private TransactionAdapter.ClotheModelAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentOrdersBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_orders, container, false);
        setUpRecyclerView();
        if (getActivity() != null)
            ((HomeActivity) getActivity()).hideSearchMenuItem();
        return mFragmentOrdersBinding.getRoot();
    }

    private void setUpRecyclerView() {
        mRecyclerView = mFragmentOrdersBinding.mainRecycler;
        mAdapter = new TransactionAdapter.ClotheModelAdapter(new ArrayList<ClothModel>(),
                getActivity().getSupportFragmentManager());
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }
}
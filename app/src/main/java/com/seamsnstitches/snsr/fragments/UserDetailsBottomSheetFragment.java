package com.seamsnstitches.snsr.fragments;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.seamsnstitches.snsr.R;
import com.seamsnstitches.snsr.databinding.UsersDetailBottomSheetBinding;
import com.seamsnstitches.snsr.models.Transaction;
import com.seamsnstitches.snsr.models.User;
import com.seamsnstitches.snsr.utility.AppConstants;

public class UserDetailsBottomSheetFragment extends BottomSheetDialogFragment implements
        View.OnClickListener {
    private User mUser;
    private UsersDetailBottomSheetBinding mUsersDetailBottomSheetBinding;
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };
    private Transaction transaction = null;

    public UserDetailsBottomSheetFragment() {
        super();
    }

    public static UserDetailsBottomSheetFragment newInstance(Bundle bundle) {
        UserDetailsBottomSheetFragment fragment = new UserDetailsBottomSheetFragment();
        fragment.setStyle(BottomSheetDialogFragment.STYLE_NO_TITLE, R.style.AppTheme_Translucent);
        fragment.setStyle(BottomSheetDialogFragment.STYLE_NO_FRAME, R.style.AppTheme_Translucent);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mUsersDetailBottomSheetBinding = DataBindingUtil
                .inflate(inflater, R.layout.users_detail_bottom_sheet,
                        container, false);
        mUsersDetailBottomSheetBinding.detailAcceptanceFab.bringToFront();
        if (getArguments() != null) bindDataWithViews(getArguments());
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                mUsersDetailBottomSheetBinding.coordinatorLayout.getLayoutParams();
//        CoordinatorLayout.Behavior behavior = params.getBehavior();
//
//        if (behavior != null && behavior instanceof BottomSheetBehavior) {
//            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetCallback);
//            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                //If device is in landscape mode.. get the height of device, dive the height by 2 and
//                // set the peek height.
//                DisplayMetrics displayMetrics = new DisplayMetrics();
//                getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//                int height = displayMetrics.heightPixels;
//                ((BottomSheetBehavior) behavior).setPeekHeight(height / 2);
//            }
//
//        }

        return mUsersDetailBottomSheetBinding.getRoot();
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        //super.setupDialog(dialog, style);

    }

    /**
     * This method is responsible for binding the data received with the appopriate views
     *
     * @param arguments
     */
    private void bindDataWithViews(Bundle arguments) {
        if (arguments.containsKey(AppConstants.TRANSACTION_DETAIL_TAG)) {
            transaction = arguments.getParcelable(AppConstants.TRANSACTION_DETAIL_TAG);
            if (transaction == null) return;
            mUser = transaction.getUser();
            bindBasicDetails(transaction.getUser());
            //transaction.getOrders().getClothModels().get(0).getClothingType().
        }
        if (arguments.containsKey(AppConstants.IS_ORDER_LIST)) {
            if (arguments.getBoolean(AppConstants.IS_ORDER_LIST)) {
                isOrder();
                if (transaction == null) return;
                String clothesNum = String.valueOf(transaction.getOrders().getClothModels().size());
                mUsersDetailBottomSheetBinding.userDetailClothesNumber.setText(clothesNum);
                mUsersDetailBottomSheetBinding.userDetailViewOrder.setOnClickListener(this);
            }
            //TODO: Show confirmation button, hide shirt/trousers measurement button
            else {
                isSearch();//TODO: Hide confirmation button, show shirt/trousers measurement button
                if (arguments.containsKey(AppConstants.SEARCH_USER_DETAIL_TAG)) {
                    User user = arguments.getParcelable(AppConstants.SEARCH_USER_DETAIL_TAG);
                    mUser = user;
                    if (user == null) return;
                    bindBasicDetails(user);
                }
                mUsersDetailBottomSheetBinding.usersDetailShirtMeasurementButton.setOnClickListener(this);
                mUsersDetailBottomSheetBinding.usersDetailTrouserMeasurementButton.setOnClickListener(this);
            }
        }
        //boolean isOrder = arguments.ge
    }

    /**
     * Binds the basic data common for both cases (user details open from an order list nd when a
     * particular user is searched for)
     *
     * @param user the user object
     */
    private void bindBasicDetails(User user) {
        String name = user.getFirstName() + " " +
                user.getLastName();
        mUsersDetailBottomSheetBinding.toolbar.setTitle(name);
        mUsersDetailBottomSheetBinding.usersDetailLocationAddress.setText(
                user.getAddress());
        mUsersDetailBottomSheetBinding.usersDetailEmailAddress.setText(
                user.getEmail());
        mUsersDetailBottomSheetBinding.userDetailPhoneNumber.setText(
                user.getPhoneNumber());
    }

    /**
     * This methods takes care of showing the view order button nd hiding the measurements button
     */
    private void isOrder() {
        setVisibilityVisible(mUsersDetailBottomSheetBinding.userDetailClothesNumber);
        setVisibilityVisible(mUsersDetailBottomSheetBinding.userDetailTotlaalAmount);
        setVisibilityVisible(mUsersDetailBottomSheetBinding.userDetailViewOrder);
        setVisibilityGone(mUsersDetailBottomSheetBinding.userDetailMeasurementHeader);
        setVisibilityGone(mUsersDetailBottomSheetBinding.usersDetailShirtMeasurementButton);
        setVisibilityGone(mUsersDetailBottomSheetBinding.usersDetailTrouserMeasurementButton);
    }

    /**
     * This method takes care of hiding the view order button nd showing the measurements button
     */
    private void isSearch() {
        setVisibilityGone(mUsersDetailBottomSheetBinding.userDetailClothesNumber);
        setVisibilityGone(mUsersDetailBottomSheetBinding.userDetailTotlaalAmount);
        setVisibilityGone(mUsersDetailBottomSheetBinding.userDetailViewOrder);
        setVisibilityVisible(mUsersDetailBottomSheetBinding.userDetailMeasurementHeader);
        setVisibilityVisible(mUsersDetailBottomSheetBinding.usersDetailShirtMeasurementButton);
        setVisibilityVisible(mUsersDetailBottomSheetBinding.usersDetailTrouserMeasurementButton);
    }

    /**
     * Sets any view passed into it visibility to gone
     *
     * @param v the View to set
     */
    private void setVisibilityGone(View v) {
        v.setVisibility(View.GONE);
    }

    /**
     * Sets any view passed into it visibility to visible
     *
     * @param v the View to set
     */
    private void setVisibilityVisible(View v) {
        v.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.user_detail_view_order:
                //TODO: Open clotheModel List Fragment
                if (getActivity().getSupportFragmentManager()
                        .findFragmentByTag(AppConstants.FINAL_TRANSACTION_FRAG_TAG) == null) {
                    FragmentTransaction fragmentTransaction =
                            getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction
                            .add(R.id.fragment_container,
                                    new FinalTransactionFragment(),
                                    AppConstants.FINAL_TRANSACTION_FRAG_TAG).commit();
                    fragmentTransaction.addToBackStack(null);
                }
                break;
            case R.id.users_detail_shirt_measurement_button:
                //TODO: Open Shirt Measurement DialogFragment
                MeasurementDialogFragment fragment = MeasurementDialogFragment
                        .newInstance(getShirtMeasurementFragBundle());
                fragment.show(getActivity().getSupportFragmentManager(), AppConstants.MEASUREMENT_DIALOG_FRAGMENT);
                break;
            case R.id.users_detail_trouser_measurement_button:
                //TODO: Open Trousers Measurement DialogFragment
                MeasurementDialogFragment fragment2 = MeasurementDialogFragment
                        .newInstance(getTrouserMeasurementFragBundle());
                fragment2.show(getActivity().getSupportFragmentManager(), AppConstants.MEASUREMENT_DIALOG_FRAGMENT);
                break;
        }
    }

    /**
     * Initializes a bundle instance and then puts ShirtMeasurement object to be used by
     * MeasurementFragment
     *
     * @return Bundle object
     */
    private Bundle getShirtMeasurementFragBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(AppConstants.MEASUREMENT_DIALOG_FRAGMENT, AppConstants.SHIRT_MEASUREMENT_FRAG);
        bundle.putParcelable(AppConstants.SHIRT_MEASUREMENT_TAG,
                mUser.getShirtMeasurement());
        return bundle;
    }

    /**
     * Initializes a bundle instance and then puts STrouserhirtMeasurement object to be used by
     * MeasurementFragment
     *
     * @return Bundle object
     */
    private Bundle getTrouserMeasurementFragBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(AppConstants.MEASUREMENT_DIALOG_FRAGMENT, AppConstants.TROOUSER_MEASUREMENT_FRAG);
        bundle.putParcelable(AppConstants.TROUSER_MEASUREMENT_TAG,
                mUser.getTrouserMeasurement());
        return bundle;
    }
}

package com.seamsnstitches.snsr.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.seamsnstitches.snsr.R;
import com.seamsnstitches.snsr.databinding.FragmentPurchaseConfirmationBinding;
import com.seamsnstitches.snsr.utility.AppConstants;

public class PurchaseConfirmationFragment extends DialogFragment {
    private FragmentPurchaseConfirmationBinding mPurchaseConfirmationBinding;

    /**
     * Creates an instance of the fragment and passes bundle arguments to get data required
     * to initialize view
     *
     * @param args
     * @return
     */
    public static PurchaseConfirmationFragment newInstance(Bundle args) {
        PurchaseConfirmationFragment purchaseConfirmationFragment = new PurchaseConfirmationFragment();
        purchaseConfirmationFragment.setArguments(args);
        return purchaseConfirmationFragment;
    }

//    @Override
//    public void setupDialog(Dialog dialog, int style) {
//        super.setupDialog(dialog, style);
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPurchaseConfirmationBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_purchase_confirmation, container, false);
        getDialog().setCanceledOnTouchOutside(true);
        bindDataWithView();
        return mPurchaseConfirmationBinding.getRoot();
    }

    /**
     * This method helps in binding the data received from bundle with the views
     */
    private void bindDataWithView() {
        Bundle arguments = getArguments();
        mPurchaseConfirmationBinding.purchaseConfirmationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onConfirmButtonClicked();
            }
        });
        setUpRating();
        if (arguments != null && arguments.containsKey(AppConstants.PURCHASE_CONFIRMATION_FRAG_QUANTITY)) {
            String quantity = arguments.getParcelable(AppConstants.PURCHASE_CONFIRMATION_FRAG_QUANTITY);
            mPurchaseConfirmationBinding.purchaseConfirmationQuantityTv.setText(quantity);
        }
    }

    /**
     * This method helps in setting up the rating bar, getting touch feedback on the rating bar and
     * updating the rating bar to show the user's rating
     */
    private void setUpRating() {
        mPurchaseConfirmationBinding.purchaseConfirmationRating
                .setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                        ratingBar.setRating(v);
                    }
                });
    }

    private void onConfirmButtonClicked() {
        float rating = mPurchaseConfirmationBinding.purchaseConfirmationRating.getRating();
        String selectedItem =
                ((String) mPurchaseConfirmationBinding.purchaseConfirmationChoiceSpinner.getSelectedItem());
        if (checkSelectedChoice(selectedItem)) { //This is to make sure the user selected an option
            Toast.makeText(getActivity(), "Please Select either Accept or Reject", Toast.LENGTH_SHORT).show();
            return; // if no option is selected return and let no action occur
        }
        //TODO: Call the Api passing in the required values to make the payments
//        mPurchaseConfirmationBinding.purchaseConfirmationChoiceSpinner.g
    }

    /**
     * This method checks to confirm if the user has actually selected a choice (i.e either accept
     * or reject) and that it is not in its default selection (choice)
     *
     * @param selectedItem the value selected
     * @return true if not selected, false if user selected an option
     */
    private boolean checkSelectedChoice(String selectedItem) {
        return selectedItem.equalsIgnoreCase("choice");
    }
}
